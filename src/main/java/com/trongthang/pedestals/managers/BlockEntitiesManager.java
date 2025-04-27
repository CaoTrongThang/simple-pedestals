package com.trongthang.pedestals.managers;

import com.trongthang.pedestals.Pedestals;
import com.trongthang.pedestals.blockentities.PedestalBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntitiesManager {
    // The BlockEntityType that will hold the custom block entity
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BLOCK_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    new Identifier(Pedestals.MOD_ID, "pedestal_block_entity"),
                    BlockEntityType.Builder.create(PedestalBlockEntity::new, BlocksManager.PEDESTAL).build(null)
            );

    public static void initialize() {
        Pedestals.LOGGER.info("Registering Block Entities...");
    }
}
