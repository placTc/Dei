package com.plact.dei.client.menus;

import com.plact.dei.DeiMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeiMenuTypes {
    public static final DeferredRegister<MenuType<?>> DEI_MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, DeiMod.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<SeparatorMenu>> SEPARATOR_MENU =
            DEI_MENU_TYPES.register("separator_menu", () -> new MenuType<SeparatorMenu>(SeparatorMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus eventBus) {
        DEI_MENU_TYPES.register(eventBus);
    }
}
