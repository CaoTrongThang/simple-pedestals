package com.trongthang.pedestals.managers;

import com.trongthang.pedestals.Pedestals;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemsManager {
    public static Item PEDESTAL_ITEM;

    public static void register() {
        // Register items after blocks
        PEDESTAL_ITEM = registerItem("pedestal",
                new BlockItem(BlocksManager.PEDESTAL, new FabricItemSettings()));
    }

    private static Item registerItem(String name, Item item) {
        Item registeredItem = Registry.register(Registries.ITEM,
                new Identifier(Pedestals.MOD_ID, name), item);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(registeredItem);
        });

        return registeredItem;
    }
}
