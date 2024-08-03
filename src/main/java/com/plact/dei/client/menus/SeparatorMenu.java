package com.plact.dei.client.menus;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import static com.plact.dei.client.menus.DeiMenuTypes.SEPARATOR_MENU;

public class SeparatorMenu extends AbstractContainerMenu {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT_LEFT = 1;
    public static final int OUTPUT_SLOT_RIGHT = 2;

    protected SeparatorMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    public SeparatorMenu(int containerId, Inventory inventory) {
        super(SEPARATOR_MENU.get(), containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
