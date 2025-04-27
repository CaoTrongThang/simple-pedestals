package com.trongthang.pedestals.blocks;

import com.trongthang.pedestals.Pedestals;
import com.trongthang.pedestals.blockentities.PedestalBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.client.util.ParticleUtil.spawnParticles;

public class PedestalBlock extends BlockWithEntity implements BlockEntityProvider {

    private VoxelShape OUTLINE_SHAPE = VoxelShapes.cuboid(0.10, 0, 0.10, 0.90, 1.0, 0.90);

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;


        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof PedestalBlockEntity pedestal)) {
            return ActionResult.PASS;
        }

        ItemStack handStack = player.getStackInHand(hand);
        ItemStack displayedItem = pedestal.getDisplayedItem();

        if (displayedItem.isEmpty()) {
            if (!handStack.isEmpty()) {
                pedestal.setDisplayedItem(handStack);
                if (!player.isCreative()) {
                    handStack.decrement(1);
                }

                // Play placement sound
                ((ServerWorld) world).playSound(
                        null, // Player - null for global sound
                        pos,
                        SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
                        SoundCategory.BLOCKS,
                        0.5F,
                        0.8F + world.random.nextFloat() * 0.3F
                );


                ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 5, 0.2, 0.5, 0.2, 0.05);

                return ActionResult.CONSUME;
            }
        } else {

            world.playSound(
                    null,
                    pos,
                    SoundEvents.ENTITY_ITEM_PICKUP,
                    SoundCategory.PLAYERS,
                    0.3F,
                    1.0F
            );

            ((ServerWorld) world).spawnParticles(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 2, 0.2, 0.5, 0.2, 0.1);


            // Remove item from pedestal
            player.giveItemStack(displayedItem.copy());
            pedestal.setDisplayedItem(ItemStack.EMPTY);
            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(OUTLINE_SHAPE == null) {
            return VoxelShapes.fullCube();
        }
        return OUTLINE_SHAPE; // Use the smaller shape for shadows/outlines
    }
}
