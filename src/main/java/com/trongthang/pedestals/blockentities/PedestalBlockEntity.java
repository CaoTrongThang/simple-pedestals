package com.trongthang.pedestals.blockentities;

import com.trongthang.pedestals.managers.BlockEntitiesManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class PedestalBlockEntity extends BlockEntity {

    private ItemStack displayedItem = ItemStack.EMPTY;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntitiesManager.PEDESTAL_BLOCK_ENTITY, pos, state);
    }

    public ItemStack getDisplayedItem() {
        return displayedItem;
    }

    public void sync() {
        if (world != null && !world.isClient) {
            markDirty();
            world.updateListeners(pos, getCachedState(), getCachedState(), 0);
        }
    }

    public void setDisplayedItem(ItemStack stack) {
        if (stack.isEmpty()) {
            this.displayedItem = ItemStack.EMPTY;
        } else {
            this.displayedItem = stack.copy();
            this.displayedItem.setCount(1);
        }
        markDirty();
        sync(); // Use our custom sync method
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.put("DisplayItem", displayedItem.writeNbt(new NbtCompound()));
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("DisplayItem")) {
            displayedItem = ItemStack.fromNbt(nbt.getCompound("DisplayItem"));
        } else {
            displayedItem = ItemStack.EMPTY;
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
