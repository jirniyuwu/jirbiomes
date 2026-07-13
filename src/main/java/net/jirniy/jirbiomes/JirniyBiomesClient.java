package net.jirniy.jirbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.minecraft.client.particle.FallingLeavesParticle;

public class JirniyBiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(ModParticles.GINKGO_LEAVES, FallingLeavesParticle.CherryProvider::new);
    }
}
