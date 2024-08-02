package com.plact.dei.items;

import com.plact.dei.DeiMod;
import com.plact.dei.blocks.SeparatorBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.plact.dei.blocks.DeiBlocks.SEPARATOR_BLOCK;

public class DeiItems {
    public static final DeferredRegister.Items DEI_ITEMS = DeferredRegister.createItems(DeiMod.MODID);

    public static final DeferredItem<DivineSyringeItem> DIVINE_SYRINGE_ITEM = DEI_ITEMS.registerItem("divine_syringe", DivineSyringeItem::new, new Item.Properties());
    public static final DeferredItem<IchorSyringeItem> ICHOR_SYRINGE_ITEM = DEI_ITEMS.registerItem("ichor_syringe", IchorSyringeItem::new, new Item.Properties());
    public static final DeferredHolder<Item, SeparatorBlockItem> SEPARATOR_BLOCK_ITEM = DEI_ITEMS.register("separator", () ->
            new SeparatorBlockItem(SEPARATOR_BLOCK.get(), new Item.Properties().stacksTo(64)));

    public static void register(IEventBus modEventBus) {
        DEI_ITEMS.register(modEventBus);
    }
}
