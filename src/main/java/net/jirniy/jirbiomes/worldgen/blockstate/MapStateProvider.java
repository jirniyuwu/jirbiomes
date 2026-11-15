package net.jirniy.jirbiomes.worldgen.blockstate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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

public record MapStateProvider(List<BlockState> input, List<Holder<BlockStateProvider>> output, boolean useBlock) implements BlockStateProvider {
    public static final MapCodec<MapStateProvider> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            BlockState.CODEC.listOf().fieldOf("input").forGetter(p -> p.input),
                            BlockStateProvider.CODEC.listOf().fieldOf("output").forGetter(p -> p.output),
                            Codec.BOOL.optionalFieldOf("use_block", true).forGetter(p -> p.useBlock)
                    )
                    .apply(i, MapStateProvider::new)
    );

    @Override
    public MapCodec<MapStateProvider> codec() {
        return CODEC;
    }

    public MapStateProvider {
        checkListLengths(input, output);
        checkDuplicatesInput(input);
    }

    public MapStateProvider(List<BlockState> input, List<Holder<BlockStateProvider>> output) {
        this(input, output, true);
    }

    public MapStateProvider(Block[] inputBlocks, List<Holder<BlockStateProvider>> output) {
        checkListLengths(List.of(inputBlocks), output);;

        ArrayList<BlockState> inputBlockStates = new ArrayList<BlockState>();
        for (Block block : inputBlocks) {
            inputBlockStates.add(block.defaultBlockState());
        }
        checkDuplicatesInput(inputBlockStates);
        this(inputBlockStates, output, true);
    }

    public MapStateProvider(Block[] inputBlocks, Block[] outputBlocks) {
        checkListLengths(List.of(inputBlocks), List.of(outputBlocks));

        ArrayList<BlockState> inputBlockStates = new ArrayList<BlockState>();
        for (Block block : inputBlocks) {
            inputBlockStates.add(block.defaultBlockState());
        }
        ArrayList<Holder<BlockStateProvider>> outputProviders = new ArrayList<Holder<BlockStateProvider>>();
        for (Block block : outputBlocks) {
            outputProviders.add(Holder.direct(new SimpleStateProvider(block.defaultBlockState())));
        }
        checkDuplicatesInput(inputBlockStates);
        this(inputBlockStates, outputProviders, true);
    }

    @Override
    public BlockState getState(final LevelAccessor level, final RandomSource random, final BlockPos pos) {
        for (int i = 0; i < input.size(); i++) {
            BlockState state = input.get(i);
            if (useBlock && level.getBlockState(pos).getBlock() == state.getBlock()) {
                BlockStateProvider provider = output.get(i).value();
                return provider.getState(level, random, pos);
            } else if (level.getBlockState(pos) == state) {
                BlockStateProvider provider = output.get(i).value();
                return provider.getState(level, random, pos);
            }
        }
        return level.getBlockState(pos);
    }

    protected static void checkDuplicatesInput(List<BlockState> list) {
        Set<BlockState> seen = new HashSet<>();

        for (BlockState state : list) {
            if (!seen.add(state)) {
                throw new IllegalArgumentException("Map state provider lists cannot contain duplicate input entries: "
                        + state.getBlock().toString());
            }
        }
    }

    protected static void checkListLengths(List<?> list1, List<?> list2) {
        if (list1.isEmpty() || list2.isEmpty()) {
            throw new IllegalArgumentException("Map state provider lists must have at least one entry");
        } else if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Map state provider lists must be equal lengths");
        }
    }
}
