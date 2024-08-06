package yuria.oldenchant.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.serialization.Codec;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Mixin;
import yuria.oldenchant.OldEnchantConfig;

@Mixin(value =  ExtraCodecs.class)
public class Enchantments {
    @WrapMethod(
            method = "intRange"
    )
    private static Codec<Integer> intRange(int pMin, int pMax, Operation<Codec<Integer>> original)
    {
        return original.call(pMin, pMax == 255 ? OldEnchantConfig.maxLevel : pMax );
    }
}
