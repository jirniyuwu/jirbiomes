package net.jirniy.jirbiomes.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModParticles {
    public static final SimpleParticleType GINKGO_LEAVES = create("ginkgo_leaves", false);

    private static SimpleParticleType create(final String name, final boolean alwaysSpawn) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, JirniyBiomes.id(name), FabricParticleTypes.simple(alwaysSpawn));
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering particles for " + JirniyBiomes.MOD_ID);
    }
}
