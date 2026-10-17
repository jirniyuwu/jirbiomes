package net.jirniy.jirbiomes.worldgen.blockstate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record MapStateProvider(boolean useBlock, List<BlockState> input, List<BlockStateProvider> output) implements BlockStateProvider {
    public static final MapCodec<MapStateProvider> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            Codec.BOOL.optionalFieldOf("use_block", true).forGetter(p -> p.useBlock),
                            BlockState.CODEC.listOf().fieldOf("input").forGetter(p -> p.input),
                            BlockStateProvider.DIRECT_CODEC.listOf().fieldOf("output").forGetter(p -> p.output)
                    )
                    .apply(i, MapStateProvider::new)
    );

    public MapStateProvider(boolean useBlock, List<BlockState> input, List<BlockStateProvider> output) {
        checkListLengths(input, output);

        this.useBlock = useBlock;
        this.input = input;
        this.output = output;

        checkDuplicatesInput(this.input);
    }

    public MapStateProvider(List<BlockState> input, List<BlockStateProvider> output) {
        this(true, input, output);
    }

    public MapStateProvider(Block[] inputBlocks, List<BlockStateProvider> output) {
        ArrayList<BlockState> inputBlockStates = new ArrayList<BlockState>();
        for (Block block : inputBlocks) {
            inputBlockStates.add(block.defaultBlockState());
        }

        this(true, inputBlockStates, output);
        checkListLengths(List.of(inputBlocks), output);

        checkDuplicatesInput(this.input);
    }

    public MapStateProvider(Block[] inputBlocks, Block[] outputBlocks) {
        ArrayList<BlockState> inputBlockStates = new ArrayList<BlockState>();
        for (Block block : inputBlocks) {
            inputBlockStates.add(block.defaultBlockState());
        }
        ArrayList<BlockStateProvider> outputProviders = new ArrayList<BlockStateProvider>();
        for (Block block : outputBlocks) {
            outputProviders.add(new SimpleStateProvider(block.defaultBlockState()));
        }

        this(true, inputBlockStates, outputProviders);
        checkListLengths(List.of(inputBlocks), List.of(outputBlocks));

        checkDuplicatesInput(this.input);
    }

    @Override
    public BlockState getState(LevelAccessor level, RandomSource random, BlockPos pos) {
        for (int i = 0; i < input.size(); i++) {
            BlockState state = input.get(i);
            if (useBlock && level.getBlockState(pos).getBlock() == state.getBlock()) {
                BlockStateProvider provider = output.get(i);
                return provider.getState(level, random, pos);
            } else if (level.getBlockState(pos) == state) {
                BlockStateProvider provider = output.get(i);
                return provider.getState(level, random, pos);
            }
        }
        return level.getBlockState(pos);
    }

    private void checkDuplicatesInput(List<BlockState> list) {
        Set<BlockState> seen = new HashSet<>();

        for (BlockState state : list) {
            if (!seen.add(state)) {
                throw new IllegalArgumentException("Map state provider lists cannot contain duplicate input entries: "
                        + state.getBlock().toString());
            }
        }
    }

    private void checkListLengths(List<?> list1, List<?> list2) {
        if (list1.isEmpty() || list2.isEmpty()) {
            throw new IllegalArgumentException("Map state provider lists must have at least one entry");
        } else if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Map state provider lists must be equal lengths");
        }
    }

    @Override
    public MapCodec<? extends BlockStateProvider> codec() {
        return null;
    }
}
