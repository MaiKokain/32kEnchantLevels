package yuria.oldenchant.mixins;

import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.commands.EnchantCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EnchantCommand.class)
public interface EnchantCmd {
    @Accessor("ERROR_NOT_LIVING_ENTITY")
    static DynamicCommandExceptionType ERROR_NOT_LIVING_ENTITY() { throw new AssertionError(); }

    @Accessor("ERROR_NO_ITEM")
    static DynamicCommandExceptionType ERROR_NO_ITEM() { throw new AssertionError(); }

    @Accessor("ERROR_INCOMPATIBLE")
    static DynamicCommandExceptionType ERROR_INCOMPATIBLE() { throw new AssertionError(); }

    @Accessor("ERROR_LEVEL_TOO_HIGH")
    static Dynamic2CommandExceptionType ERROR_LEVEL_TOO_HIGH() { throw new AssertionError(); }

    @Accessor("ERROR_NOTHING_HAPPENED")
    static SimpleCommandExceptionType ERROR_NOTHING_HAPPENED() { throw new AssertionError(); }

}
