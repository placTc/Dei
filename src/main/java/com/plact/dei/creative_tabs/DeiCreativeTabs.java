package com.plact.dei.creative_tabs;

import com.plact.dei.DeiMod;
import com.plact.dei.fluids.DeiFluids;
import com.plact.dei.items.DeiItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeiCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> DEI_CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DeiMod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEI_MAIN_TAB = DEI_CREATIVE_MODE_TABS.register(
            "dei_main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.dei_main")) //The language key for the title of your CreativeModeTab
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> DeiItems.ICHOR_SYRINGE_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(DeiItems.DIVINE_SYRINGE_ITEM.get());
                        output.accept(DeiItems.ICHOR_SYRINGE_ITEM.get());
                        output.accept(DeiFluids.BLOOD_FLUID.getBucketItem());
                    }).build()
    );

    public static void register(IEventBus modEventBus) {
        DEI_CREATIVE_MODE_TABS.register(modEventBus);
    }
}
