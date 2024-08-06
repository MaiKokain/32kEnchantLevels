package yuria.oldenchant.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.commands.EnchantCommand;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import yuria.oldenchant.OldEnchantConfig;

@Mixin(EnchantCommand.class)
public class EnchantCmd {
    @WrapOperation(
            method = "enchant",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I")
    )
    private static int setFakeLevel(Enchantment instance, Operation<Integer> original)
    {
        return OldEnchantConfig.maxLevel;
    }
}
