package net.jirniy.jirbiomes.mixin;

import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.GlowstoneFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GlowstoneFeature.class)
public class GlowstoneFeatureMixin {

    /**
     * @author jirniyuwu
     * @reason use ModTags.Blocks.GLOWSTONE_FEATURE_PLACEABLE instead of hardcoded netherrack, basalt and blackstone
     */
    @Overwrite
    public boolean place(final FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        if (!level.isEmptyBlock(origin)) {
            return false;
        } else {
            BlockState aboveState = level.getBlockState(origin.above());
            if (!aboveState.is(ModTags.Blocks.GLOWSTONE_FEATURE_PLACEABLE)) {
                return false;
            } else {
                level.setBlock(origin, Blocks.GLOWSTONE.defaultBlockState(), 2);

                for(int i = 0; i < 1500; ++i) {
                    BlockPos placePos = origin.offset(random.nextInt(8) - random.nextInt(8), -random.nextInt(12), random.nextInt(8) - random.nextInt(8));
                    if (level.getBlockState(placePos).isAir()) {
                        int neighbours = 0;

                        for(Direction direction : Direction.values()) {
                            if (level.getBlockState(placePos.relative(direction)).is(Blocks.GLOWSTONE)) {
                                ++neighbours;
                            }

                            if (neighbours > 1) {
                                break;
                            }
                        }

                        if (neighbours == 1) {
                            level.setBlock(placePos, Blocks.GLOWSTONE.defaultBlockState(), 2);
                        }
                    }
                }

                return true;
            }
        }
    }
}
