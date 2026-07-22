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
                .offset(0.26f)
                .build().forEach(point -> builder.add(point, ModBiomes.SALT_DEPOSIT));
        builder.build().forEach(mapper);
    }
}