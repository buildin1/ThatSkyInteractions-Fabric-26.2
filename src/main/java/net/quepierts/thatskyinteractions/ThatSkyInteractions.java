package net.quepierts.thatskyinteractions;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThatSkyInteractions implements ModInitializer {
    public static final String MOD_ID = "thatskyinteractions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("ThatSkyInteractions Fabric 26.2 initialized");
    }
}
