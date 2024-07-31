package com.plact.dei.fluids.fluid_blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class BloodFluidBlock extends LiquidBlock {
    public BloodFluidBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getFluidState().isSource()) {
            level.setBlock(pos, Blocks.RED_WOOL.defaultBlockState(), BloodFluidBlock.UPDATE_ALL);
        }
        else if (random.nextInt(1, 4) == 2) {
            level.setBlock(pos, Blocks.RED_CARPET.defaultBlockState(), BloodFluidBlock.UPDATE_ALL);
        }
        else {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), BloodFluidBlock.UPDATE_ALL);
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, 1200);
    }
}
