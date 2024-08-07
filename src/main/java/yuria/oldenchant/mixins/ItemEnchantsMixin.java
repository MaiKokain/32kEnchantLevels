package yuria.oldenchant.mixins;

import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import yuria.oldenchant.OldEnchant;
import yuria.oldenchant.OldEnchantConfig;

@Mixin(ItemEnchantments.class)
public class ItemEnchantsMixin {
    @ModifyArg(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Codec;intRange(II)Lcom/mojang/serialization/Codec;"),
            index = 1
    )
    private static int lol(int constant)
    {
        return OldEnchantConfig.maxLevel;
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(intValue = 255)
    )
    private int lol2(int constant)
    {
        return OldEnchantConfig.maxLevel;
    }
}
