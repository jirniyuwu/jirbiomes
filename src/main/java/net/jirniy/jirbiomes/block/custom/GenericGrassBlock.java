package net.jirniy.jirbiomes.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GenericGrassBlock extends VegetationBlock {
    public static final MapCodec<GenericGrassBlock> CODEC = simpleCodec(GenericGrassBlock::new);
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 13.0);

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    public GenericGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPE;
    }
}
