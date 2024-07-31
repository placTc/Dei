package com.plact.dei.items;

import com.plact.dei.DeiMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeiItems {
    public static final DeferredRegister.Items DEI_ITEMS = DeferredRegister.createItems(DeiMod.MODID);

    public static final DeferredItem<DivineSyringeItem> DIVINE_SYRINGE_ITEM = DEI_ITEMS.registerItem("divine_syringe", DivineSyringeItem::new, new Item.Properties());
    public static final DeferredItem<IchorSyringeItem> ICHOR_SYRINGE_ITEM = DEI_ITEMS.registerItem("ichor_syringe", IchorSyringeItem::new, new Item.Properties());

    public static void register(IEventBus modEventBus) {
        DEI_ITEMS.register(modEventBus);
    }
}
