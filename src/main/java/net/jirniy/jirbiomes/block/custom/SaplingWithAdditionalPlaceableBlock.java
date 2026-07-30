package net.jirniy.jirbiomes.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class SaplingWithAdditionalPlaceableBlock extends SaplingBlock {
    public static final MapCodec<SaplingWithAdditionalPlaceableBlock> CODEC = RecordCodecBuilder.mapCodec((i) ->
            i.group(TreeGrower.CODEC.fieldOf("tree").forGetter((b) -> b.treeGrower),
                    TagKey.codec(Registries.BLOCK).fieldOf("placeable").forGetter(b -> b.placeableOn),
                    propertiesCodec()).apply(i, SaplingWithAdditionalPlaceableBlock::new));
    protected final TreeGrower treeGrower;
    protected final TagKey<Block> placeableOn;

    public SaplingWithAdditionalPlaceableBlock(TreeGrower treeGrower, TagKey<Block> placeOn, Properties properties) {
        super(treeGrower, properties);
        this.placeableOn = placeOn;
        this.treeGrower = treeGrower;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(this.placeableOn);
    }
}
