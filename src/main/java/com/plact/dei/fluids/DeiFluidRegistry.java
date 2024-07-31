package com.plact.dei.fluids;

import com.plact.dei.DeiMod;
import com.plact.dei.fluids.fluid_types.DeiBaseFluidType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public class DeiFluidRegistry {
    private final DeferredRegister<FluidType> deiFluidTypes;
    private final DeferredRegister<Fluid> deiFluids;
    private final DeferredRegister<Block> deiFluidBlocks;
    private final DeferredRegister<Item> deiBucketItems;

    public DeiFluidRegistry() {
        deiFluidTypes = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, DeiMod.MODID);
        deiFluids = DeferredRegister.create(Registries.FLUID, DeiMod.MODID);
        deiFluidBlocks = DeferredRegister.create(Registries.BLOCK, DeiMod.MODID);
        deiBucketItems = DeferredRegister.create(Registries.ITEM, DeiMod.MODID);
    }

    public
    <TYPE extends DeiBaseFluidType, SOURCE extends BaseFlowingFluid.Source, FLOWING extends BaseFlowingFluid.Flowing, BLOCK extends LiquidBlock, BUCKET extends BucketItem>
    DeiFluidRegistryObject<TYPE, SOURCE, FLOWING, BLOCK, BUCKET>
    register(String fluidName,
             Supplier<TYPE> fluidTypeSupplier,
             @NotNull Function<BaseFlowingFluid.Properties, FLOWING> flowingFluidConstructor,
             @NotNull Function<BaseFlowingFluid.Properties, SOURCE> sourceFluidConstructor,
             Function<BaseFlowingFluid.Properties, BaseFlowingFluid.Properties> propertiesModification,
             Function<FlowingFluid, BLOCK> fluidBlockConstructor,
             Function<FlowingFluid, BUCKET> bucketConstructor)
    {
        ResourceLocation baseKey = ResourceLocation.fromNamespaceAndPath(DeiMod.MODID, fluidName);
        BaseFlowingFluid.Properties fluidProperties = new BaseFlowingFluid.Properties(
                fluidTypeSupplier,
                DeferredHolder.create(Registries.FLUID, baseKey.withSuffix("_source")),
                DeferredHolder.create(Registries.FLUID, baseKey.withSuffix("_flowing"))
        )
                .block(DeferredHolder.create(Registries.BLOCK, baseKey.withSuffix("_block")))
                .bucket(DeferredHolder.create(Registries.ITEM, baseKey.withSuffix("_bucket")));
        BaseFlowingFluid.Properties finalFluidProperties = propertiesModification.apply(fluidProperties);

        DeferredHolder<FluidType, TYPE> fluidType = deiFluidTypes.register(fluidName, fluidTypeSupplier);
        DeferredHolder<Fluid, SOURCE> sourceFluid = deiFluids.register(fluidName + "_source", () -> sourceFluidConstructor.apply(finalFluidProperties));
        DeferredHolder<Fluid, FLOWING> flowingFluid = deiFluids.register(fluidName + "_flowing", () -> flowingFluidConstructor.apply(finalFluidProperties));
        DeferredHolder<Block, BLOCK> fluidBlock = deiFluidBlocks.register(fluidName + "_block", () -> fluidBlockConstructor.apply(sourceFluid.get()));
        DeferredHolder<Item, BUCKET> bucketItem = deiBucketItems.register(fluidName + "_bucket", () -> bucketConstructor.apply(sourceFluid.get()));

        return new DeiFluidRegistryObject<>(fluidType, sourceFluid, flowingFluid, fluidBlock, bucketItem);
    }

    public Collection<DeferredHolder<FluidType, ? extends FluidType>> getFluidTypeEntries() {
        return deiFluidTypes.getEntries();
    }

    public void register(IEventBus modEventBus) {
        deiFluidTypes.register(modEventBus);
        deiFluids.register(modEventBus);
        deiFluidBlocks.register(modEventBus);
        deiBucketItems.register(modEventBus);
    }
}
