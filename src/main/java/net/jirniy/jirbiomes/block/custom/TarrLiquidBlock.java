package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class TarrLiquidBlock extends LiquidBlock {
    private final Map<TagKey<Fluid>, BlockState> turnsInto;

    public TarrLiquidBlock(FlowingFluid fluid, Map<TagKey<Fluid>, BlockState> turnsInto, Properties properties) {
        super(fluid, properties);
        this.turnsInto = turnsInto;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!replaceOnContact(level, pos)) {
            super.onPlace(state, level, pos, oldState, movedByPiston);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!replaceOnContact(level, pos)) {
            super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
        }
    }

    private boolean replaceOnContact(Level level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            FluidState neighborFluid = level.getFluidState(neighborPos);

            if (dir == Direction.DOWN && neighborFluid.is(FluidTags.WATER)) {
                continue;
            }

            for (TagKey<Fluid> fluidTag : turnsInto.keySet()) {
                if (neighborFluid.is(fluidTag)) {
                    if (dir == Direction.DOWN) {
                        level.setBlockAndUpdate(pos.below(), turnsInto.get(fluidTag));
                        level.playSound(null, pos.below(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 0.7F);
                    } else {
                        level.setBlockAndUpdate(pos, turnsInto.get(fluidTag));
                        level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 0.7F);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
