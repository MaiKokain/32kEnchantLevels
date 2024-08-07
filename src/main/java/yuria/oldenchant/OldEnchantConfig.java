package yuria.oldenchant;

import com.teamresourceful.resourcefulconfig.api.annotations.*;
import com.teamresourceful.resourcefulconfig.api.annotations.Config;
import com.teamresourceful.resourcefulconfig.api.types.options.EntryType;

@Config(OldEnchant.MODID)
public final class OldEnchantConfig {
    @ConfigEntry(type =  EntryType.INTEGER, id = "maxLevel", translation = "Max level")
    @ConfigOption.Range(min = 255, max = Constants.MAX_LIMIT)
    @Comment("The Max level for enchantments")
    public static int maxLevel = Short.MAX_VALUE;
}
