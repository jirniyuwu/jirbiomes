package net.jirniy.jirbiomes.worldgen.blockstate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

// copied from WeightedStateProvider class and just replaced the BlockState with BlockStateProvider
public record WeightedStateProviderProvider(WeightedList<BlockStateProvider> weightedList) implements BlockStateProvider {
    public static final MapCodec<WeightedStateProviderProvider> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(WeightedList
                    .codec(BlockStateProvider.DIRECT_CODEC).fieldOf("entries")
                    .forGetter(o -> o.weightedList))
                    .apply(i, WeightedStateProviderProvider::new)
    );

    public WeightedStateProviderProvider {
        if (weightedList.isEmpty()) {
            throw new IllegalArgumentException("Weighted list must have at least one entry");
        }
    }

    public WeightedStateProviderProvider(final WeightedList.Builder<BlockStateProvider> weightedList) {
        this(weightedList.build());
    }

    @Override
    public MapCodec<WeightedStateProviderProvider> codec() {
        return CODEC;
    }

    @Override
    public BlockState getState(final LevelAccessor level, final RandomSource random, final BlockPos pos) {
        return this.weightedList.getRandomOrThrow(random).getState(level, random, pos);
    }
}
