package com.plact.dei.blocks;

import com.plact.dei.DeiMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeiBlocks {
    public static final DeferredRegister.Blocks DEI_BLOCKS = DeferredRegister.createBlocks(DeiMod.MODID);

    public static final DeferredHolder<Block, SeparatorBlock> SEPARATOR_BLOCK = DEI_BLOCKS.register(
            "separator",
            () -> new SeparatorBlock(BlockBehaviour.Properties.of().destroyTime(0.5f).noOcclusion().dynamicShape().isViewBlocking((a, b, c) -> false))
    );

    public static final DeferredHolder<Block, TransparentBlock> PLACEHOLDER_BLOCK = DEI_BLOCKS.register(
            "placeholder",
            () -> new TransparentBlock(BlockBehaviour.Properties.of().destroyTime(0f).noCollission().isViewBlocking((a, b, c) -> false))
    );

    public static void register(IEventBus modEventBus) {
        DEI_BLOCKS.register(modEventBus);
    }
}
