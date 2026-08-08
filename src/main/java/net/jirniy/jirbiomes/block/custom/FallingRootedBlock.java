package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FallingRootedBlock extends SandBlock implements BonemealableBlock {
    final Block falling;

    public FallingRootedBlock(ColorRGBA dustColor, Block fallReplace, Properties properties) {
        super(dustColor, properties);
        this.falling = fallReplace;
    }

    public FallingRootedBlock(ColorRGBA dustColor, Properties properties) {
        super(dustColor, properties);
        this.falling = this;
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinY()) {
            FallingBlockEntity entity = FallingBlockEntity.fall(level, pos, falling.defaultBlockState());
            this.falling(entity);
        }
    }

    @Override
    public boolean isValidBonemealTarget(final LevelReader level, final BlockPos pos, final BlockState state) {
        return level.getBlockState(pos.below()).isAir() && level.isInsideBuildHeight(pos.below());
    }

    @Override
    public boolean isBonemealSuccess(final Level level, final RandomSource random, final BlockPos pos, final BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(final ServerLevel level, final RandomSource random, final BlockPos pos, final BlockState state) {
        level.setBlockAndUpdate(pos.below(), Blocks.HANGING_ROOTS.defaultBlockState());
    }

    @Override
    public BlockPos getParticlePos(final BlockPos blockPos) {
        return blockPos.below();
    }
}
