package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class CoconutBlock extends CocoaBlock {
    private static final List<VoxelShape> SHAPES = List.of(
            Block.box(6, 8, 11, 10, 14, 15),
            Block.box(1, 8, 6, 5, 14, 10),
            Block.box(11, 8, 6, 15, 14, 10),
            Block.box(6, 8, 1, 10, 14, 5),

            Block.box(4, 3, 8, 12, 14, 16),
            Block.box(0, 3, 4, 8, 14, 12),
            Block.box(8, 3, 4, 16, 14, 12),
            Block.box(4, 3, 0, 12, 14, 8),

            Block.box(2, 0, 4, 14, 14, 16),
            Block.box(0, 0, 2, 12, 14, 14),
            Block.box(4, 0, 2, 16, 14, 14),
            Block.box(2, 0, 0, 14, 14, 12)
    );

    public CoconutBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Item asItem() {
        return ModItems.COCONUT;
    }

    @Override protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int ageAdjusted = state.getValue(CocoaBlock.AGE) * 4;
        return switch (state.getValue(CocoaBlock.FACING)) {
            case NORTH -> SHAPES.get(ageAdjusted + 3);
            case SOUTH -> SHAPES.get(ageAdjusted + 0);
            case WEST -> SHAPES.get(ageAdjusted + 1);
            case EAST -> SHAPES.get(ageAdjusted + 2);
            default -> SHAPES.get(ageAdjusted);
        };
    }
}
