package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class VegetationPlaceableOnBlock extends GenericGrassBlock {
    final TagKey<Block> placeable;

    public VegetationPlaceableOnBlock(TagKey<Block> placeableOn, Properties properties) {
        super(properties);
        this.placeable = placeableOn;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos).is(placeable);
    }
}
