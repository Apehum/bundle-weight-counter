package com.github.apehum.bundlecounter;

import com.github.apehum.bundlecounter.config.Config;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BundleCounter implements ClientModInitializer {
    public static final Config CONFIG = Config.loadFromFile();
    public static final Logger LOGGER = LoggerFactory.getLogger("bundle_counter");

    @Override
    public void onInitializeClient() {
    }
}
