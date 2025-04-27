package com.trongthang.pedestals.blockrenderer;

import com.trongthang.pedestals.Pedestals;
import com.trongthang.pedestals.blockentities.PedestalBlockEntity;
import com.trongthang.pedestals.blocks.PedestalBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(PedestalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemStack stack = entity.getDisplayedItem();

        if (stack.isEmpty()) return;

        matrices.push();
        matrices.translate(0.5, 1.2f, 0.5);

        // Rotation using the current time
        float time = (entity.getWorld().getTime() + tickDelta) / 20.0F;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation(time));

        matrices.scale(1.2f, 1.2f, 1.2f);

        // Updated render call with ModelTransformationMode
        MinecraftClient.getInstance().getItemRenderer().renderItem(
                stack,
                ModelTransformationMode.GROUND,
                light,
                overlay,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                (int) entity.getPos().asLong()
        );

        matrices.pop();
    }
}
