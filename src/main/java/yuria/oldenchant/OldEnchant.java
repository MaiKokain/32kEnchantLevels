package yuria.oldenchant;

import com.mojang.logging.LogUtils;
import com.teamresourceful.resourcefulconfig.api.client.ResourcefulConfigScreen;
import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(OldEnchant.MODID)
public class OldEnchant
{
    public static final String MODID = "oldenchant";

    public static final Configurator CONFIGURATOR = new Configurator(MODID);

    public static final Logger LOGGER = LogUtils.getLogger();

    public OldEnchant(IEventBus modEventBus, ModContainer modContainer)
    {
        CONFIGURATOR.register(OldEnchantConfig.class);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (container, modListScreen) -> ResourcefulConfigScreen.getFactory(MODID).apply(modListScreen));
    }

    public static int returnLevel()
    {

        LOGGER.info(String.valueOf(OldEnchantConfig.maxLevel));
        LOGGER.info("MAX LIMIT {}", OldEnchantConfig.maxLevel);
        return OldEnchantConfig.maxLevel;
    }
}
