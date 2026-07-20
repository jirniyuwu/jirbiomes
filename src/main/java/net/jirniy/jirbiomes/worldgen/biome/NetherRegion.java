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

public class NetherRegion extends Region {
    public NetherRegion(Identifier name, int weight) {
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterPointListBuilder()
                .temperature(Temperature.FROZEN)
                .humidity(Humidity.WET, Humidity.HUMID)
                .continentalness(Continentalness.FULL_RANGE)
                .erosion(Erosion.FULL_RANGE)
                .depth(Depth.UNDERGROUND)
                .weirdness(Weirdness.FULL_RANGE)
                .build().forEach(point -> builder.add(point, ModBiomes.BRIMSTONE_CRAGS));
        builder.build().forEach(mapper);
    }
}