package hell0hd.gateway.block.custom;

import com.google.common.base.Predicates;
import com.mojang.serialization.MapCodec;
import hell0hd.gateway.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class ReinforcedDeepslateFrameBlock extends Block {
    public static final MapCodec<ReinforcedDeepslateFrameBlock> CODEC = simpleCodec(ReinforcedDeepslateFrameBlock::new);
    public static final EnumProperty<Direction> FACING;
    public static final BooleanProperty HAS_EYE;
    private static final VoxelShape SHAPE_EMPTY;
    private static final VoxelShape SHAPE_FULL;
    private static @Nullable BlockPattern portalShape;

    public MapCodec<ReinforcedDeepslateFrameBlock> codec() {
        return CODEC;
    }

    public ReinforcedDeepslateFrameBlock (final BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(HAS_EYE, false));
    }

    protected boolean useShapeForLightOcclusion(final BlockState state) {
        return true;
    }

    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return (Boolean)state.getValue(HAS_EYE) ? SHAPE_FULL : SHAPE_EMPTY;
    }

    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())).setValue(HAS_EYE, false);
    }

    protected boolean hasAnalogOutputSignal(final BlockState state) {
        return true;
    }

    protected int getAnalogOutputSignal(final BlockState state, final Level level, final BlockPos pos, final Direction direction) {
        return (Boolean)state.getValue(HAS_EYE) ? 15 : 0;
    }

    protected BlockState rotate(final BlockState state, final Rotation rotation) {
        return (BlockState)state.setValue(FACING, rotation.rotate((Direction)state.getValue(FACING)));
    }

    protected BlockState mirror(final BlockState state, final Mirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, HAS_EYE});
    }

    public static BlockPattern getOrCreatePortalShape(Direction direction) {
        if (portalShape == null) {
            if (direction == Direction.WEST) {
                portalShape = BlockPatternBuilder.start().aisle(new String[]{"?^?^?^?^?"}).where('?', BlockInWorld.hasState(BlockStatePredicate.ANY)).where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.REINFORCED_DEEPSLATE_FRAME).where(HAS_EYE, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(Direction.WEST)))).build();
            }
            if (direction == Direction.SOUTH) {
                portalShape = BlockPatternBuilder.start().aisle(new String[]{"?<?<?<?<?"}).where('?', BlockInWorld.hasState(BlockStatePredicate.ANY)).where('<', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.REINFORCED_DEEPSLATE_FRAME).where(HAS_EYE, Predicates.equalTo(true)).where(FACING, Predicates.equalTo(Direction.SOUTH)))).build();
            }
        }

        return portalShape;
    }

    protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
        return false;
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        HAS_EYE = BlockStateProperties.EYE;
        SHAPE_EMPTY = Block.column((double)16.0F, (double)0.0F, (double)13.0F);
        SHAPE_FULL = Shapes.or(SHAPE_EMPTY, Block.column((double)8.0F, (double)13.0F, (double)16.0F));
    }
}
