package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomFlowerbedBlock extends FlowerBedBlock {
    public MushroomFlowerbedBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return state.isSolidRender();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState below = level.getBlockState(belowPos);
        return below.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT)
                || level.getRawBrightness(pos, 0) < 13 && this.mayPlaceOn(below, level, belowPos);
    }

    public static int getLightLevel(BlockState state) {
        return state.getValue(FlowerBedBlock.AMOUNT) > 2 ? 1 : 0;
    }
}
