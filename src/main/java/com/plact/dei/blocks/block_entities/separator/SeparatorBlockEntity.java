package com.plact.dei.blocks.block_entities.separator;

import com.plact.dei.blocks.block_entities.DeiBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.minecraft.world.level.block.state.BlockState;

public class SeparatorBlockEntity extends BlockEntity {

    public SeparatorBlockEntity(BlockPos pos, BlockState blockState) {
        super(DeiBlockEntityTypes.SEPARATOR_BLOCK_ENTITY_TYPE.get(), pos, blockState);
    }
}
