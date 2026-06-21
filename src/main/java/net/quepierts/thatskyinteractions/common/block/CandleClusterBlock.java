package net.quepierts.thatskyinteractions.common.block;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quepierts.thatskyinteractions.common.block.entity.CandleClusterBlockEntity;
import net.quepierts.thatskyinteractions.common.item.CandleClusterItem;
import net.quepierts.thatskyinteractions.common.registry.Items;

import java.util.function.ToIntFunction;

public class CandleClusterBlock extends BaseEntityBlock {
    public static final IntegerProperty LEVEL;
    public static final ToIntFunction<BlockState> LIGHT_EMISSION;
    private static final VoxelShape AABB;

    public static final MapCodec<CandleClusterBlock> CODEC = simpleCodec(CandleClusterBlock::new);

    public CandleClusterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LEVEL, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (level.getBlockEntity(pos) instanceof CandleClusterBlockEntity entity) {
            return entity.getShape();
        }
        return AABB;
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess tickAccess,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            RandomSource random
    ) {
        if (direction == Direction.DOWN) {
            if (level.getBlockEntity(pos) instanceof CandleClusterBlockEntity entity) {
                entity.setOnSlab(neighborState.hasProperty(SlabBlock.TYPE) && neighborState.getValue(SlabBlock.TYPE) == SlabType.BOTTOM);
            }
        }
        return state;
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        if (level.getBlockEntity(pos) instanceof CandleClusterBlockEntity entity) {
            if (!entity.getCandles().isEmpty()) {
                short candle = entity.getCandles().getShort(0);
                CandleType type = CandleClusterBlockEntity.getCandleType(candle);
                return new ItemStack(Items.CANDLES[type.ordinal()]);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hitResult
    ) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.FAIL;
        }

        if (level.getBlockEntity(pos) instanceof CandleClusterBlockEntity entity) {
            Vec3 location = hitResult.getLocation();
            int localX = (int) ((location.x - pos.getX()) * 16);
            int localZ = (int) ((location.z - pos.getZ()) * 16);

            if (stack.isEmpty()) {
                if (player.isShiftKeyDown() && entity.tryRemoveCandle(localX, localZ, player)) {
                    return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
                }
                if (entity.tryExtinguishCandle(localX, localZ) || entity.tryExtinguishAny()) {
                    return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
                }
            } else if (stack.is(net.minecraft.world.item.Items.FLINT_AND_STEEL) || stack.is(net.minecraft.world.item.Items.FIRE_CHARGE)) {
                if (!entity.tryLitCandle(localX, localZ)) {
                    entity.tryLitAny();
                }
                return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
            } else if (stack.is(net.minecraft.world.item.Items.HONEYCOMB) && entity.tryWax()) {
                return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
            } else if (stack.is(net.minecraft.world.item.Items.IRON_AXE) && entity.tryWaxOff()) {
                return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
            } else if (stack.getItem() instanceof CandleClusterItem candleItem) {
                CandleType type = candleItem.getType();
                if (entity.tryAddCandle(localX, localZ, type, level.getRandom().nextInt(CandleClusterBlockEntity.MAX_ROTATION))) {
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
                }
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.getBlockEntity(pos) instanceof CandleClusterBlockEntity entity) {
            ShortArrayList lighted = entity.getLightedCandles();
            double y = pos.getY() + 0.125 - (entity.isOnSlab() ? 0.5 : 0.0);
            for (int i = 0; i < lighted.size(); i++) {
                short candle = lighted.getShort(i);
                CandleType type = CandleClusterBlockEntity.getCandleType(candle);
                double half = type.getSize() / 32.0;
                this.addParticlesAndSound(
                        level,
                        pos.getX() + half + CandleClusterBlockEntity.getCandleX(candle) / 16.0,
                        y + type.getHeight() / 16.0,
                        pos.getZ() + half + CandleClusterBlockEntity.getCandleZ(candle) / 16.0,
                        random
                );
            }
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CandleClusterBlockEntity(blockPos, blockState);
    }

    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    private void addParticlesAndSound(Level level, double x, double y, double z, RandomSource random) {
        float f = random.nextFloat();
        if (f < 0.3F) {
            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
            if (f < 0.17F) {
                level.playLocalSound(
                        x + 0.5,
                        y + 0.5,
                        z + 0.5,
                        SoundEvents.CANDLE_AMBIENT,
                        SoundSource.BLOCKS,
                        1.0F + random.nextFloat(),
                        random.nextFloat() * 0.7F + 0.3F,
                        false
                );
            }
        }
        level.addParticle(ParticleTypes.SMALL_FLAME, x, y, z, 0.0, 0.0, 0.0);
    }

    static {
        LEVEL = BlockStateProperties.LEVEL;
        LIGHT_EMISSION = (state) -> state.getValue(LEVEL);
        AABB = Block.box(0, -16, 0, 16, 32, 16);
    }
}
