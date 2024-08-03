package com.plact.dei.blocks.block_entities.separator;

import com.plact.dei.blocks.block_entities.DeiBlockEntityTypes;
import com.plact.dei.infra.FluidStackHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class SeparatorBlockEntity extends BlockEntity implements MenuProvider {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT_LEFT = 1;
    public static final int OUTPUT_SLOT_RIGHT = 2;

    private ItemStackHandler items = new ItemStackHandler(3);
    private FluidStackHandler fluids = new FluidStackHandler(3,
            new HashMap<Integer, Integer>() {{
                put(INPUT_SLOT, 1000);
                put(OUTPUT_SLOT_LEFT, 500);
                put(OUTPUT_SLOT_RIGHT, 500);
    }});
    private int progress;

    public SeparatorBlockEntity(BlockPos pos, BlockState blockState) {
        super(DeiBlockEntityTypes.SEPARATOR_BLOCK_ENTITY_TYPE.get(), pos, blockState);
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return null;
    }
}
