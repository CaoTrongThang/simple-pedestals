package com.trongthang.pedestals.managers;

import com.trongthang.pedestals.Pedestals;
import com.trongthang.pedestals.blocks.PedestalBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlocksManager {
    public static final Block PEDESTAL = registerBlock("pedestal",
            new PedestalBlock(FabricBlockSettings.copyOf(Blocks.COAL_BLOCK)
                    .strength(3.0f, 6.0f)
                    .requiresTool().nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Pedestals.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        Pedestals.LOGGER.info("Registering ModBlocks for " + Pedestals.MOD_ID);
    }
}
