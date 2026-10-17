package net.jirniy.jirbiomes.worldgen.biome;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.material.VanillaMaterialConditions;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;

import static net.minecraft.world.level.levelgen.material.MaterialRules.*;

public class ModMaterialRules {
    private static final MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);

    public static MaterialRule netherRules(final RegistryAccess registryAccess) {
        HolderGetter<MaterialCondition> conditions = registryAccess.lookupOrThrow(Registries.MATERIAL_CONDITION);
        HolderGetter<Biome> biomes = registryAccess.lookupOrThrow(Registries.BIOME);

        MaterialCondition aboveNetherLavaLevel = yBlockCheck(VerticalAnchor.absolute(31), 0);
        MaterialCondition onFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.ON_FLOOR);
        MaterialCondition underCeiling = MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_CEILING);
        MaterialCondition underFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_FLOOR);
        MaterialCondition deepUnderFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.DEEP_UNDER_FLOOR);

        return sequence(
                ifTrue(verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                ifTrue(not(verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),

                ifTrue(isBiome(biomes, ModBiomes.BRIMSTONE_CRAGS), sequence(
                        ifTrue(noiseCondition3d(Noises.SPAGHETTI_3D_1, -0.025, 0.025),
                                makeStateRule(ModBlocks.IGNITED_BRIMSTONE)),
                        ifTrue(onFloor, sequence(ifTrue(noiseCondition3d(Noises.ICE, -0.05),
                                ifTrue(aboveNetherLavaLevel, makeStateRule(ModBlocks.BRIMGRASS_BLOCK))))),
                        ifTrue(underCeiling, makeStateRule(ModBlocks.BRIMSTONE)),
                        ifTrue(underFloor, makeStateRule(ModBlocks.BRIMSTONE)),
                        ifTrue(deepUnderFloor, makeStateRule(ModBlocks.IGNITED_BRIMSTONE)),
                        ifTrue(noiseCondition3d(Noises.GRAVEL_LAYER, 0.1), makeStateRule(ModBlocks.NETHERSTONE))))
        );
    }

    public static MaterialRule overworldRules(final RegistryAccess registryAccess) {
        HolderGetter<MaterialCondition> conditions = registryAccess.lookupOrThrow(Registries.MATERIAL_CONDITION);
        HolderGetter<Biome> biomes = registryAccess.lookupOrThrow(Registries.BIOME);

        MaterialCondition notUnderDeepWater = waterStartCheck(-6, -1);
        MaterialCondition deepslate = verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8));
        MaterialCondition aboveWater = waterBlockCheck(0, 0);
        MaterialCondition onFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.ON_FLOOR);
        MaterialCondition underCeiling = MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_CEILING);
        MaterialCondition underFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_FLOOR);
        MaterialCondition deepUnderFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.DEEP_UNDER_FLOOR);

        MaterialRule grassOrDirtIfUnderwater = sequence(ifTrue(aboveWater, state(Blocks.GRASS_BLOCK.defaultBlockState())), state(Blocks.DIRT.defaultBlockState()));
        MaterialRule wetGrassOrDirtIfUnderwater = sequence(ifTrue(aboveWater, state(ModBlocks.WET_GRASS_BLOCK.defaultBlockState())), state(ModBlocks.WETLAND.defaultBlockState()));

        return sequence(
                ifTrue(verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),

                ifTrue(isBiome(biomes, ModBiomes.PROTOTAXITE_FOREST), sequence(
                        ifTrue(abovePreliminarySurface(), sequence(
                                ifTrue(aboveWater, sequence(ifTrue(onFloor, sequence(
                                        ifTrue(noiseCondition2d(Noises.ICE, 0.5, 2), state(Blocks.MUD.defaultBlockState())),
                                        ifTrue(noiseCondition2d(Noises.ICE, 0.1, 0.5), wetGrassOrDirtIfUnderwater),
                                        ifTrue(noiseCondition2d(Noises.ICE, -0.1, 0.1), grassOrDirtIfUnderwater),
                                        ifTrue(noiseCondition2d(Noises.ICE, -0.5, -0.1), wetGrassOrDirtIfUnderwater),
                                        ifTrue(noiseCondition2d(Noises.ICE, -2, -0.5), state(Blocks.MUD.defaultBlockState()))
                                )))),
                                ifTrue(aboveWater, sequence(ifTrue(underFloor, sequence(
                                        ifTrue(noiseCondition2d(Noises.ICE, 0.52, 2), state(Blocks.MUD.defaultBlockState())),
                                        ifTrue(noiseCondition2d(Noises.ICE, 0.1, 0.52), state(ModBlocks.WETLAND.defaultBlockState())),
                                        ifTrue(noiseCondition2d(Noises.ICE, -0.1, 0.1), state(Blocks.DIRT.defaultBlockState())),
                                        ifTrue(noiseCondition2d(Noises.ICE, -0.52, -0.1), state(ModBlocks.WETLAND.defaultBlockState())),
                                        ifTrue(noiseCondition2d(Noises.ICE, -2, -0.52), state(Blocks.MUD.defaultBlockState()))
                                ))))
                        ))
                )),

                ifTrue(isBiome(biomes, ModBiomes.SALT_DEPOSIT), sequence(
                        ifTrue(not(verticalGradient("salt", VerticalAnchor.absolute(48), VerticalAnchor.absolute(52))), sequence(
                                ifTrue(underFloor, ifTrue(abovePreliminarySurface(), sequence(
                                        ifTrue(noiseCondition2d(Noises.ORE_GAP, -0.09f, 0.09f),
                                                makeStateRule(Blocks.SAND)),
                                        ifTrue(noiseCondition2d(Noises.ORE_GAP, -0.29f, 0.29f),
                                                makeStateRule(Blocks.CALCITE)),
                                        ifTrue(noiseCondition2d(Noises.ORE_GAP, -0.5f, 0.5f),
                                                makeStateRule(ModBlocks.SALT_BLOCK)),
                                        ifTrue(noiseCondition2d(Noises.ORE_GAP, -0.64f, 0.64f),
                                                makeStateRule(Blocks.CALCITE)),
                                        makeStateRule(Blocks.SAND)
                                ))),
                                ifTrue(deepUnderFloor, makeStateRule(Blocks.SANDSTONE))
                        ))
                    )),

                ifTrue(isBiome(biomes, ModBiomes.PERMAFROST_CAVES), sequence(
                        ifTrue(noiseCondition3d(Noises.ICE, 0.01f),
                                ifTrue(onFloor, ifTrue(not(verticalGradient("snow",
                                        VerticalAnchor.aboveBottom(10), VerticalAnchor.aboveBottom(14))),
                                        makeStateRule(Blocks.SNOW_BLOCK)))),
                        ifTrue(not(deepslate), ifTrue(noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.0f, 0.2f),
                                makeStateRule(Blocks.PACKED_ICE))),
                        ifTrue(not(deepslate), ifTrue(noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.2f, 0.4f),
                                makeStateRule(ModBlocks.FROSTED_STONE))))),

                ifTrue(isBiome(biomes, ModBiomes.POLAR_DESERT), ifTrue(not(verticalGradient("surface", VerticalAnchor.absolute(54), VerticalAnchor.absolute(56))),
                        sequence(
                                ifTrue(noiseCondition3d(Noises.POWDER_SNOW, 0.35, 0.6),
                                        ifTrue(onFloor, makeStateRule(Blocks.POWDER_SNOW))),
                                ifTrue(onFloor, makeStateRule(Blocks.SNOW_BLOCK)),
                                ifTrue(noiseCondition3d(Noises.POWDER_SNOW, -0.35, -0.6),
                                        ifTrue(underFloor, makeStateRule(ModBlocks.PACKED_SNOW))),
                                ifTrue(underFloor, makeStateRule(Blocks.SNOW_BLOCK)),
                                ifTrue(deepUnderFloor, makeStateRule(ModBlocks.PACKED_SNOW)),
                                ifTrue(not(deepslate), ifTrue(noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.2f, 0.4f),
                                makeStateRule(ModBlocks.FROSTED_STONE))))))
        );
    }

    private static MaterialRule makeStateRule(Block block) {
        return state(block.defaultBlockState());
    }
}
