package com.plact.dei.fluids;

import com.plact.dei.fluids.fluid_blocks.BloodFluidBlock;
import com.plact.dei.fluids.fluid_types.BloodFluidType;
import com.plact.dei.fluids.fluid_types.IchorFluidType;
import com.plact.dei.fluids.fluid_types.infra.DeiBaseFluidType;
import com.plact.dei.fluids.infra.DeiFluidRegistry;
import com.plact.dei.fluids.infra.DeiFluidRegistryObject;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class DeiFluids {
    public static final DeiFluidRegistry DEI_FLUID_REGISTRY = new DeiFluidRegistry();

    public static final DeiFluidRegistryObject<BloodFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, BloodFluidBlock, BucketItem>
            BLOOD_FLUID = DEI_FLUID_REGISTRY.register(
            "blood_fluid",
                    BloodFluidType::new,
                    BaseFlowingFluid.Flowing::new,
                    BaseFlowingFluid.Source::new,
                    fluid -> new BloodFluidBlock(
                            fluid,
                            BlockBehaviour.Properties.of()
                                    .replaceable()
                                    .noCollission()
                                    .strength(100.0F)
                                    .pushReaction(PushReaction.IGNORE)
                                    .noLootTable()
                                    .liquid()
                                    .sound(SoundType.EMPTY)
                    ),
                    fluid -> new BucketItem(
                            fluid,
                            new Item.Properties()
                                    .craftRemainder(Items.BUCKET)
                                    .stacksTo(1)
                    ),
                    (BaseFlowingFluid.Properties props) -> props.tickRate(7).slopeFindDistance(6)
            );

    public static final DeiFluidRegistryObject<IchorFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem>
            ICHOR_FLUID = DEI_FLUID_REGISTRY.register(
                    "ichor_fluid",
                    IchorFluidType::new,
                    BaseFlowingFluid.Flowing::new,
                    BaseFlowingFluid.Source::new,
                    fluid -> new LiquidBlock(
                            fluid,
                            BlockBehaviour.Properties.of()
                                    .replaceable()
                                    .noCollission()
                                    .strength(100.0F)
                                    .pushReaction(PushReaction.DESTROY)
                                    .noLootTable()
                                    .liquid()
                                    .lightLevel(foo -> 8)
                                    .sound(SoundType.EMPTY)
                    ),
                    fluid -> new BucketItem(
                            fluid,
                            new Item.Properties()
                                    .craftRemainder(Items.BUCKET)
                                    .stacksTo(1)
                    ),
                    (BaseFlowingFluid.Properties props) -> props.tickRate(8).slopeFindDistance(6)
            );

    public static void register(IEventBus modEventBus) {
        DEI_FLUID_REGISTRY.register(modEventBus);
    }

    public static void registerClientFluidExtensions(RegisterClientExtensionsEvent event) {
        DEI_FLUID_REGISTRY.getFluidTypeEntries().forEach(
            (fluidTypeEntry -> {
                if (fluidTypeEntry.get() instanceof DeiBaseFluidType fluidType) {
                    fluidType.registerClientExtensions(event);
                }
            })
        );
    }

}
