package yuria.oldenchant.mixins;

import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import yuria.oldenchant.OldEnchantConfig;

@Mixin(ItemEnchantments.Mutable.class)
public class MutableItemEnchants {

    @ModifyArg(method = "upgrade", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 0), index = 1)
    private int lol(int a)
    {
        return OldEnchantConfig.maxLevel;
    }
}
