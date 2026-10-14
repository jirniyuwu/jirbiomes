package net.jirniy.jirbiomes.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class PotentMagmaBlock extends MagmaLikeBlock {
    public static final MapCodec<PotentMagmaBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance
                    .group(Block.CODEC.fieldOf("on_contact_water").forGetter(block -> block.waterTurningInto),
                           Block.CODEC.fieldOf("on_broken").forGetter(block -> block.breakTurningInto),
                           Codec.intRange(0, 65535).optionalFieldOf("fire_ticks", 0).forGetter(block -> block.fireTicks), propertiesCodec())
                    .apply(instance, PotentMagmaBlock::new)
    );
    @Override
    public MapCodec<? extends PotentMagmaBlock> codec() {
        return CODEC;
    }
    private final Block breakTurningInto;
    private final int fireTicks;

    public PotentMagmaBlock(Block onContactWater, Block onBreak, Properties properties) {
        super(onContactWater, properties);
        this.breakTurningInto = onBreak;
        this.fireTicks = 0;
    }

    public PotentMagmaBlock(Block onContactWater, Block onBreak, int fireTicks, Properties properties) {
        super(onContactWater, properties);
        this.breakTurningInto = onBreak;
        this.fireTicks = fireTicks;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.canSpreadFireAround(pos)) {
            int passes = random.nextInt(3);
            if (passes > 0) {
                BlockPos testPos = pos;

                for (int pass = 0; pass < passes; pass++) {
                    testPos = testPos.offset(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
                    if (!level.isLoaded(testPos)) {
                        return;
                    }

                    BlockState blockState = level.getBlockState(testPos);
                    if (blockState.isAir()) {
                        if (this.hasFlammableNeighbours(level, testPos)) {
                            level.setBlockAndUpdate(testPos, BaseFireBlock.getState(level, testPos));
                            return;
                        }
                    } else if (blockState.blocksMotion()) {
                        return;
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    BlockPos testPos = pos.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    if (!level.isLoaded(testPos)) {
                        return;
                    }

                    if (level.isEmptyBlock(testPos.above()) && this.isFlammable(level, testPos)) {
                        level.setBlockAndUpdate(testPos.above(), BaseFireBlock.getState(level, testPos));
                    }
                }
            }
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !entity.fireImmune()) {
            entity.igniteForTicks(this.fireTicks);
        }
        super.stepOn(level, pos, onState, entity);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
        if (!EnchantmentHelper.hasTag(destroyedWith, EnchantmentTags.PREVENTS_ICE_MELTING)) {
            BlockState belowState = level.getBlockState(pos.below());
            if (belowState.blocksMotion() || belowState.liquid()) {
                level.setBlockAndUpdate(pos, this.breakTurningInto.defaultBlockState());
            }
        }
    }

    private boolean hasFlammableNeighbours(final LevelReader level, final BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (this.isFlammable(level, pos.relative(direction))) {
                return true;
            }
        }

        return false;
    }

    private boolean isFlammable(final LevelReader level, final BlockPos pos) {
        return (!level.isInsideBuildHeight(pos.getY()) || level.hasChunkAt(pos)) && level.getBlockState(pos).ignitedByLava();
    }
}
