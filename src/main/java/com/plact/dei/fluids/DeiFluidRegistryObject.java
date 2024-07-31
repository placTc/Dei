package com.plact.dei.fluids;

import com.plact.dei.fluids.fluid_types.DeiBaseFluidType;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DeiFluidRegistryObject
        <TYPE extends DeiBaseFluidType, SOURCE extends BaseFlowingFluid.Source, FLOWING extends BaseFlowingFluid.Flowing, BLOCK extends LiquidBlock, BUCKET extends BucketItem>
{
    private final DeferredHolder<FluidType, TYPE> fluidType;
    private final DeferredHolder<Fluid, SOURCE> sourceFluid;
    private final DeferredHolder<Fluid, FLOWING> flowingFluid;
    private final DeferredHolder<Block, BLOCK> fluidBlock;
    private final DeferredHolder<Item, BUCKET> bucketItem;

    public DeiFluidRegistryObject(DeferredHolder<FluidType, TYPE> fluidType,
                                  DeferredHolder<Fluid, SOURCE> sourceFluid,
                                  DeferredHolder<Fluid, FLOWING> flowingFluid,
                                  DeferredHolder<Block, BLOCK> fluidBlock,
                                  DeferredHolder<Item, BUCKET> bucketItem) {
        this.fluidType = fluidType;
        this.sourceFluid = sourceFluid;
        this.flowingFluid = flowingFluid;
        this.fluidBlock = fluidBlock;
        this.bucketItem = bucketItem;
    }

    public TYPE getFluidType() {
        return fluidType.get();
    }

    public SOURCE getSourceFluid() {
        return sourceFluid.get();
    }

    public FLOWING getFlowingFluid() {
        return flowingFluid.get();
    }

    public BLOCK getFluidBlock() {
        return fluidBlock.get();
    }

    public BUCKET getBucketItem() {
        return bucketItem.get();
    }

}
