package com.plact.dei.infra;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Map;

public class FluidStackHandler implements IFluidHandler, INBTSerializable<CompoundTag> {
    public static final int DEFAULT_TANK_CAPACITY = 1000;
    protected NonNullList<FluidStack> stacks;
    protected NonNullList<Integer> capacities;

    public FluidStackHandler(int size) {
        this.stacks = NonNullList.withSize(size, FluidStack.EMPTY);
        this.capacities = NonNullList.withSize(size, DEFAULT_TANK_CAPACITY);
    }

    public FluidStackHandler(int size, Map<Integer, Integer> capacities) {
        this.stacks = NonNullList.withSize(size, FluidStack.EMPTY);
        this.capacities = NonNullList.withSize(size, DEFAULT_TANK_CAPACITY);
        capacities.forEach((k, v) -> {
            this.capacities.set(k, v);
        });
    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
    }

    @Override
    public int getTanks() {
        return stacks.size();
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        return stacks.get(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return capacities.get(tank);
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return false;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return 0;
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return null;
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return null;
    }
}
