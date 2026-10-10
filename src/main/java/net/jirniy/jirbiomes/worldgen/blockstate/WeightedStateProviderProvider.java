package net.jirniy.jirbiomes.worldgen.blockstate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

// copied from WeightedStateProvider class and just replaced the BlockState with BlockStateProvider
public class WeightedStateProviderProvider extends BlockStateProvider {
    public static final MapCodec<WeightedStateProviderProvider> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                    WeightedList.nonEmptyCodec(BlockStateProvider.CODEC).fieldOf("entries").forGetter(o -> o.weightedList))
                    .apply(i, WeightedStateProviderProvider::new)
    );
    private final WeightedList<BlockStateProvider> weightedList;

    public WeightedStateProviderProvider(final WeightedList<BlockStateProvider> weightedList) {
        if (weightedList.isEmpty()) {
            throw new IllegalArgumentException("Weighted list must have at least one entry");
        }

        this.weightedList = weightedList;
    }

    public WeightedStateProviderProvider(final WeightedList.Builder<BlockStateProvider> weightedList) {
        this(weightedList.build());
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return ModBlockStateProviderType.WEIGHTED_STATE_PROVIDER_PROVIDER;
    }

    @Override
    public BlockState getState(final WorldGenLevel level, final RandomSource random, final BlockPos pos) {
        return this.weightedList.getRandomOrThrow(random).getState(level, random, pos);
    }
}
