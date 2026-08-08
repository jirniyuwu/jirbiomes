package net.jirniy.jirbiomes.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class OverworldRegion extends Region {
    public OverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterPointListBuilder()
                .temperature(Temperature.NEUTRAL, Temperature.WARM)
                .humidity(Humidity.NEUTRAL, Humidity.DRY)
                .continentalness(Continentalness.COAST)
                .erosion(Erosion.EROSION_6, Erosion.EROSION_5)
                .depth(Depth.FULL_RANGE)
                .weirdness(Weirdness.VALLEY, Weirdness.LOW_SLICE_VARIANT_ASCENDING)
                .offset(0.24f)
                .build().forEach(point -> builder.add(point, ModBiomes.SALT_DEPOSIT));
        new ParameterPointListBuilder()
                .temperature(Temperature.FROZEN)
                .humidity(Humidity.FULL_RANGE)
                .continentalness(Continentalness.OCEAN, Continentalness.COAST, Continentalness.NEAR_INLAND, Continentalness.MID_INLAND)
                .erosion(Erosion.FULL_RANGE)
                .depth(Depth.UNDERGROUND)
                .weirdness(Weirdness.FULL_RANGE)
                .offset(0f)
                .build().forEach(point -> builder.add(point, ModBiomes.PERMAFROST_CAVES));
        new ParameterPointListBuilder()
                .temperature(Temperature.FROZEN)
                .humidity(Humidity.ARID, Humidity.DRY)
                .continentalness(Continentalness.NEAR_INLAND, Continentalness.MID_INLAND, Continentalness.FAR_INLAND)
                .erosion(Erosion.EROSION_0, Erosion.EROSION_1, Erosion.EROSION_2, Erosion.EROSION_3)
                .depth(Depth.FULL_RANGE)
                .weirdness(Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                .offset(0f)
                .build().forEach(point -> builder.add(point, ModBiomes.POLAR_DESERT));
        builder.build().forEach(mapper);
    }
}