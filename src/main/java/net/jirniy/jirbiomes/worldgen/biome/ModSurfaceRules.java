package net.jirniy.jirbiomes.worldgen.biome;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class ModSurfaceRules {
    private static final RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);

    public static RuleSource brimstoneCragsRules(HolderGetter<Biome> biomes) {
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

    private static RuleSource makeStateRule(Block block) {
        return state(block.defaultBlockState());
    }

    /*
    sequence(new RuleSource[]{
        ifTrue(
                verticalGradient("bedrock_floor", 
                        VerticalAnchor.bottom(), 
                        VerticalAnchor.aboveBottom(5)), BEDROCK), 
                ifTrue(not(
                        verticalGradient("bedrock_roof", 
                                VerticalAnchor.belowTop(5), 
                                VerticalAnchor.top())), BEDROCK), 
                ifTrue(closeToCeiling, NETHERRACK), 
                ifTrue(
                        isBiome(biomes, new ResourceKey[]{Biomes.BASALT_DELTAS}), 
                        sequence(new RuleSource[]{
                                ifTrue(UNDER_CEILING, BASALT), 
                                ifTrue(UNDER_FLOOR, 
                                        sequence(new RuleSource[]{gravelPatch, 
                                                ifTrue(netherStateSelector, BASALT), BLACKSTONE}))})), 
                ifTrue(isBiome(biomes, new ResourceKey[]{Biomes.SOUL_SAND_VALLEY}), 
                        sequence(new RuleSource[]{
                                ifTrue(UNDER_CEILING, 
                                        sequence(new RuleSource[]{
                                                ifTrue(netherStateSelector, SOUL_SAND), SOUL_SOIL})), 
                                ifTrue(UNDER_FLOOR, 
                                        sequence(new RuleSource[]{gravelPatch, 
                                                ifTrue(netherStateSelector, SOUL_SAND), SOUL_SOIL}))})), 
                ifTrue(ON_FLOOR, sequence(new RuleSource[]{ 
                        ifTrue(isBiome(biomes, new ResourceKey[]{Biomes.CRIMSON_FOREST}), 
                                ifTrue(not(netherrack), ifTrue(aboveNetherLavaLevel, sequence(ifTrue(netherWart, NETHER_WART_BLOCK), CRIMSON_NYLIUM))))})), 
                ifTrue(isBiome(biomes, new ResourceKey[]{Biomes.NETHER_WASTES}), 
                        sequence(new RuleSource[]{ifTrue(
                                UNDER_FLOOR, ifTrue(soulSandLayer, 
                                        sequence(new RuleSource[]{
                                                ifTrue(not(hole), 
                                                        ifTrue(netherBandAroundLavaLevelBottom, 
                                                                ifTrue(netherBandAroundLavaLevelTop, SOUL_SAND))), 
                                                NETHERRACK}))), ifTrue(ON_FLOOR, 
                                ifTrue(aboveNetherLavaLevel, 
                                        ifTrue(netherBandAroundLavaLevelTop, 
                                                ifTrue(gravelLayer, sequence(new RuleSource[]{
                                                        ifTrue(aboveNetherLavaSurface, GRAVEL), 
                                                        ifTrue(not(hole), GRAVEL)})))))})), NETHERRACK});
     */
}
