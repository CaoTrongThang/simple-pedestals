package com.trongthang.pedestals;

import com.trongthang.pedestals.managers.BlockEntitiesManager;
import com.trongthang.pedestals.managers.BlocksManager;
import com.trongthang.pedestals.managers.ItemsManager;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pedestals implements ModInitializer {
	public static final String MOD_ID = "pedestals";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BlocksManager.registerModBlocks();
		BlockEntitiesManager.initialize();
		ItemsManager.register();
	}
}