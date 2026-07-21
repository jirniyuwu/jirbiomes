package net.jirniy.jirbiomes.worldgen.biome;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class ModSurfaceRules {
    private static final RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);

    public static RuleSource netherRules(HolderGetter<Biome> biomes) {
        ConditionSource aboveNetherLavaLevel = yBlockCheck(VerticalAnchor.absolute(31), 0);
        ConditionSource aboveNetherLavaSurface = yBlockCheck(VerticalAnchor.absolute(32), 0);
        ConditionSource netherBandAroundLavaLevelBottom = yStartCheck(VerticalAnchor.absolute(30), 0);
        ConditionSource netherBandAroundLavaLevelTop = not(yStartCheck(VerticalAnchor.absolute(35), 0));
        ConditionSource closeToCeiling = yBlockCheck(VerticalAnchor.belowTop(5), 0);
        ConditionSource hole = hole();
        ConditionSource netherrack = noiseCondition2d(Noises.NETHERRACK, 0.54);
        
        return sequence(
                ifTrue(verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                ifTrue(not(verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),

                ifTrue(isBiome(biomes, ModBiomes.BRIMSTONE_CRAGS), sequence(
                        ifTrue(noiseCondition3d(Noises.SPAGHETTI_3D_1, -0.025, 0.025),
                                makeStateRule(ModBlocks.IGNITED_BRIMSTONE)),
                        ifTrue(ON_FLOOR, sequence(ifTrue(noiseCondition3d(Noises.ICE, -0.05),
                                ifTrue(aboveNetherLavaLevel, makeStateRule(ModBlocks.BRIMGRASS_BLOCK))))),
                        ifTrue(UNDER_CEILING, makeStateRule(ModBlocks.BRIMSTONE)),
                        ifTrue(UNDER_FLOOR, makeStateRule(ModBlocks.BRIMSTONE)),
                        ifTrue(DEEP_UNDER_FLOOR, makeStateRule(ModBlocks.IGNITED_BRIMSTONE)),
                        ifTrue(noiseCondition3d(Noises.GRAVEL_LAYER, 0.1), makeStateRule(ModBlocks.NETHERSTONE))))
        );
    }

    public static RuleSource overworldRules(HolderGetter<Biome> biomes) {;
        SurfaceRules.ConditionSource notUnderDeepWater = SurfaceRules.waterStartCheck(-6, -1);

        return sequence(
                ifTrue(verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),

                ifTrue(isBiome(biomes, ModBiomes.SALT_DEPOSIT), sequence(
                        ifTrue(not(verticalGradient("salt", VerticalAnchor.absolute(48), VerticalAnchor.absolute(52))), sequence(
                                ifTrue(UNDER_FLOOR, sequence(
                                        ifTrue(noiseCondition2d(Noises.ORE_GAP, -0.09f, 0.09f),
                                                makeStateRule(Blocks.SAND)),
                                        ifTrue(noiseCondition2d(Noises.ORE_GAP, -0.29f, 0.29f),
                                                makeStateRule(Blocks.CALCITE)),
                                        ifTrue(noiseCondition2d(Noises.ORE_GAP, -0.60f, 0.60f),
                                                makeStateRule(ModBlocks.SALT_BLOCK)),
                                        makeStateRule(Blocks.SAND)
                                )),
                                ifTrue(DEEP_UNDER_FLOOR, makeStateRule(Blocks.SANDSTONE))
                        ))
                    ))
        );
    }

    private static RuleSource makeStateRule(Block block) {
        return state(block.defaultBlockState());
    }
}
