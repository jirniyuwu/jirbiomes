package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.predicate.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;

public class CoconutDecorator extends TreeDecorator {
    public static final MapCodec<CoconutDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(CoconutDecorator::new, (d) -> d.probability);
    private final float probability;

    public CoconutDecorator(final float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.COCONUT;
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        List<BlockPos> logs = context.logs();
        if (!logs.isEmpty()) {
            logs.forEach((pos) -> {
                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    Direction opposite = direction.getOpposite();
                    BlockPos coconutPos = pos.offset(opposite.getStepX(), 0, opposite.getStepZ());
                    if (random.nextFloat() <= this.probability && context.isAir(coconutPos) && context.checkBlock(coconutPos.above(), BlockPredicate.forBlock(ModBlocks.PALM_LEAVES))) {
                        context.setBlock(coconutPos, ModBlocks.COCONUT_PLANT.defaultBlockState().setValue(CocoaBlock.AGE, random.nextInt(1, 3)).setValue(CocoaBlock.FACING, direction));
                    }
                }
            });
        }
    }
}
