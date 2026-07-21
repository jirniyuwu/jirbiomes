package net.jirniy.jirbiomes.worldgen.biome;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import terrablender.api.Regions;

public class ModBiomes {
    public static final ResourceKey<Biome> SALT_DEPOSIT = registerBiomeKey("salt_deposit");
    public static final ResourceKey<Biome> BRIMSTONE_CRAGS = registerBiomeKey("brimstone_crags");

    public static void registerBiomes() {
        Regions.register(new NetherRegion(JirniyBiomes.id("jirbiomes_nether"), 20));
        Regions.register(new OverworldRegion(JirniyBiomes.id("jirbiomes_overworld"), 20));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var carvers = context.lookup(Registries.CONFIGURED_CARVER);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, BRIMSTONE_CRAGS, ModNetherBiomes.brimstoneCrags(placedFeatures, carvers));
        register(context, SALT_DEPOSIT, ModOverworldBiomes.saltDeposit(placedFeatures, carvers));
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, JirniyBiomes.id(name));
    }
}
