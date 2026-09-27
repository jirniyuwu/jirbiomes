package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.predicate.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;

public class CoconutDecorator extends TreeDecorator {
    public static final MapCodec<CoconutDecorator> CODEC = RecordCodecBuilder.mapCodec((i) ->
            i.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(d -> d.probability),
                    IntProviders.codec(0, 2).fieldOf("age").forGetter(p -> p.age)).apply(i, CoconutDecorator::new));

    private final float probability;
    private final IntProvider age;

    public CoconutDecorator(final float probability, final IntProvider age) {
        this.age = age;
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
                        context.setBlock(coconutPos, ModBlocks.COCONUT_PLANT.defaultBlockState().setValue(CocoaBlock.AGE, age.sample(random)).setValue(CocoaBlock.FACING, direction));
                    }
                }
            });
        }
    }
}
