package com.plact.dei.blocks;

import com.mojang.serialization.MapCodec;
import com.plact.dei.blocks.block_entities.separator.SeparatorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import static com.plact.dei.items.DeiItems.SEPARATOR_BLOCK_ITEM;

public class SeparatorBlock extends BaseEntityBlock {
    public static final MapCodec<SeparatorBlock> CODEC = simpleCodec(SeparatorBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty BASE = DeiBlockStateProperties.BASE;

    public SeparatorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BASE, true).setValue(FACING, Direction.NORTH));
    }

    @Override
    public Item asItem() {
        return SEPARATOR_BLOCK_ITEM.get();
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(BASE)) {
            return facing("west", state) || facing("east", state) ?
                    Block.box(4, 0, 0, 12, 16, 16) :
                    Block.box(0, 0, 4, 16, 16, 12);
        }
        else {
            return Shapes.or(Block.box(1, 1, 1, 15, 15, 15), Block.box(4, 0, 4, 12, 1, 12));
        }
    }

    public static boolean facing(String direction, BlockState state) {
        return state.getValue(FACING).getName().equals(direction);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (pos.above().getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        else {
            return null;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(BASE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (state.getValue(BASE)) {
            return new SeparatorBlockEntity(pos, state);
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(), this.defaultBlockState().setValue(BASE, false), 3);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!state.getValue(BASE) && blockBelowIsSeparator(pos, level) && blockBelowIsBase(pos, level) ||
        state.getValue(BASE) && blockAboveIsSeparator(pos, level) && blockAboveIsNotBase(pos, level)) {
            return state;
        }
        else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    private boolean blockAboveIsSeparator(BlockPos pos, LevelAccessor level) {
        return level.getBlockState(pos.above()).getBlock() instanceof SeparatorBlock;
    }

    private boolean blockAboveIsNotBase(BlockPos pos, LevelAccessor level) {
        return !level.getBlockState(pos.above()).getValue(BASE);
    }

    private boolean blockBelowIsSeparator(BlockPos pos, LevelAccessor level) {
        return level.getBlockState(pos.below()).getBlock() instanceof SeparatorBlock;
    }

    private boolean blockBelowIsBase(BlockPos pos, LevelAccessor level) {
        return level.getBlockState(pos.below()).getValue(BASE);
    }


    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        return super.playerWillDestroy(level, pos, state, player);
        // prevent drop from top block
    }
}
