package net.jirniy.jirbiomes.worldgen.blockstate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FossilFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapStateProvider extends BlockStateProvider {
    public static final MapCodec<MapStateProvider> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            BlockState.CODEC.listOf().fieldOf("input").forGetter(p -> p.input),
                            BlockStateProvider.CODEC.listOf().fieldOf("output").forGetter(p -> p.output)
                    )
                    .apply(i, MapStateProvider::new)
    );

    private final List<BlockState> input;
    private final List<BlockStateProvider> output;

    public MapStateProvider(List<BlockState> input, List<BlockStateProvider> output) {
        super();
        if (input.isEmpty() || output.isEmpty()) {
            throw new IllegalArgumentException("Map state provider lists must have at least one entry");
        } else if (input.size() != output.size()) {
            throw new IllegalArgumentException("Map state provider lists must be equal lengths");
        }
        this.input = input;
        this.output = output;
    }

    public MapStateProvider(Block[] inputBlocks, Block[] outputBlocks) {
        super();
        if (inputBlocks.length < 1 || outputBlocks.length < 1) {
            throw new IllegalArgumentException("Map state provider lists must have at least one entry");
        } else if (inputBlocks.length != outputBlocks.length) {
            throw new IllegalArgumentException("Map state provider lists must be equal lengths");
        }

        ArrayList<BlockState> inputBlockStates = new ArrayList<BlockState>();
        for (Block block : inputBlocks) {
            inputBlockStates.add(block.defaultBlockState());
        }
        this.input = inputBlockStates;

        ArrayList<BlockStateProvider> outputProviders = new ArrayList<BlockStateProvider>();
        for (Block block : outputBlocks) {
            outputProviders.add(BlockStateProvider.simple(block));
        }
        this.output = outputProviders;
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return ModBlockStateProviderType.MAP_STATE_PROVIDER;
    }

    @Override
    public BlockState getState(WorldGenLevel level, RandomSource random, BlockPos pos) {
        for (int i = 0; i < input.size(); i++) {
            BlockState state = input.get(i);
            if (level.getBlockState(pos).getBlock() == state.getBlock()) {
                BlockStateProvider provider = output.get(i);
                return provider.getState(level, random, pos);
            }
        }
        return level.getBlockState(pos);
    }
}
