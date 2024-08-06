package yuria.oldenchant.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import yuria.oldenchant.OldEnchantConfig;

@Mixin(Enchantment.class)
public class Enchants {
    @WrapMethod(method = "definition(Lnet/minecraft/core/HolderSet;Lnet/minecraft/core/HolderSet;IILnet/minecraft/world/item/enchantment/Enchantment$Cost;Lnet/minecraft/world/item/enchantment/Enchantment$Cost;I[Lnet/minecraft/world/entity/EquipmentSlotGroup;)Lnet/minecraft/world/item/enchantment/Enchantment$EnchantmentDefinition;")
    private static Enchantment.EnchantmentDefinition fakee(HolderSet<Item> pSupportedItems, HolderSet<Item> pPrimaryItems, int pWeight, int pMaxLevel, Enchantment.Cost pMinCost, Enchantment.Cost pMaxCost, int pAnvilCost, EquipmentSlotGroup[] pSlots, Operation<Enchantment.EnchantmentDefinition> original)
    {
        return original.call(pSupportedItems, pPrimaryItems, pWeight, OldEnchantConfig.maxLevel, pMinCost, pMaxCost, pAnvilCost, pSlots);
    }
}
