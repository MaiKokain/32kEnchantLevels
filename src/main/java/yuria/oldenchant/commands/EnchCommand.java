package yuria.oldenchant.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import yuria.oldenchant.OldEnchant;
import yuria.oldenchant.OldEnchantConfig;
import yuria.oldenchant.mixins.EnchantCmd;

import java.util.Collection;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = OldEnchant.MODID)
public class EnchCommand {
    @SubscribeEvent
    public static void onRegisterCommand(final RegisterCommandsEvent event)
    {
        var command = Commands.literal("oldench");
        command.then(
                Commands.literal("enchant")
                        .requires(s -> s.hasPermission(2))
                        .then(
                                Commands.argument("targets", EntityArgument.entities())
                                        .then(
                                                Commands.argument("enchantment", ResourceArgument.resource(event.getBuildContext(), Registries.ENCHANTMENT))
                                                        .executes(x -> enchant(x.getSource(), EntityArgument.getEntities(x, "targets"), ResourceArgument.getEnchantment(x, "enchantment"), 1))
                                                        .then(Commands.argument("level", IntegerArgumentType.integer(0))
                                                                .executes(x -> enchant(
                                                                        x.getSource(),
                                                                        EntityArgument.getEntities(x, "targets"),
                                                                        ResourceArgument.getEnchantment(x, "enchantment"),
                                                                        IntegerArgumentType.getInteger(x, "level")
                                                                ))
                                                        )
                                        )
                        )
        );

        event.getDispatcher().register(command);
    }

    private static int enchant(CommandSourceStack pSource, Collection<? extends Entity> pTargets, Holder<Enchantment> pEnchantment, int pLevel) throws CommandSyntaxException
    {
        Enchantment enchantment = pEnchantment.value();
        if (pLevel > OldEnchantConfig.maxLevel) {
            throw EnchantCmd.ERROR_LEVEL_TOO_HIGH().create(pLevel, OldEnchantConfig.maxLevel);
        } else {
            int i = 0;

            for (Entity entity : pTargets)
            {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    ItemStack itemStack = livingEntity.getMainHandItem();
                    if (!itemStack.isEmpty()) {
                        if (enchantment.canEnchant(itemStack) && EnchantmentHelper.isEnchantmentCompatible(EnchantmentHelper.getEnchantmentsForCrafting(itemStack).keySet(), pEnchantment))
                        {
                            itemStack.enchant(pEnchantment, pLevel);
                            i++;
                        } else if (pTargets.size() == 1) {
                            throw EnchantCmd.ERROR_INCOMPATIBLE().create(itemStack.getItem().getName(itemStack).getString());

                        }
                    } else if (pTargets.size() == 1) {
                        throw EnchantCmd.ERROR_NO_ITEM().create(livingEntity.getName().getString());
                    }
                } else if (pTargets.size() == 1) {
                    throw EnchantCmd.ERROR_NOT_LIVING_ENTITY().create(entity.getName().getString());
                }
            }

            if (i == 0) {
                throw EnchantCmd.ERROR_NOTHING_HAPPENED().create();
            } else {
                if (pTargets.size() == 1) {
                    pSource.sendSuccess(() -> Component.translatable("commands.enchant.success.single", Enchantment.getFullname(pEnchantment, pLevel), pTargets.iterator().next().getDisplayName()), true);
                } else {
                    pSource.sendSuccess(() -> Component.translatable("commands.enchant.success.multiple", Enchantment.getFullname(pEnchantment, pLevel), pTargets.size()), true);
                }
            }

            return i;
        }
    }
}
