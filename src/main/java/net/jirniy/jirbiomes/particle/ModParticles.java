package net.jirniy.jirbiomes.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModParticles {
    public static final SimpleParticleType GINKGO_LEAVES = create("ginkgo_leaves", false);
    public static final SimpleParticleType SNOWFLAKE = create("snowflake", false);
    public static final SimpleParticleType BRIMGRASS_SPORES = create("brimgrass_spores", false);

    private static SimpleParticleType create(final String name, final boolean alwaysSpawn) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, JirniyBiomes.id(name), FabricParticleTypes.simple(alwaysSpawn));
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering particles for " + JirniyBiomes.MOD_ID);
    }
}
