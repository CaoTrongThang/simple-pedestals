package com.trongthang.pedestals;

import com.trongthang.pedestals.blockrenderer.PedestalBlockEntityRenderer;
import com.trongthang.pedestals.managers.BlockEntitiesManager;
import com.trongthang.pedestals.managers.BlocksManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class PedestalsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksManager.PEDESTAL, RenderLayer.getCutout());

        BlockEntityRendererRegistry.register(
                BlockEntitiesManager.PEDESTAL_BLOCK_ENTITY,
                PedestalBlockEntityRenderer::new
        );

    }
}
