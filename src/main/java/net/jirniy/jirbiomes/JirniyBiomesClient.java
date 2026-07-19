package net.jirniy.jirbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.impl.client.rendering.EntityRendererRegistryImpl;
import net.jirniy.jirbiomes.entity.ModEntities;
import net.jirniy.jirbiomes.entity.ModModelLayers;
import net.jirniy.jirbiomes.particle.ModDripParticle;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.renderer.entity.BoatRenderer;

public class JirniyBiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(ModParticles.GINKGO_LEAVES, FallingLeavesParticle.CherryProvider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.BRIMGRASS_SPORES, ModDripParticle.BrimgrassAmbientProvider::new);

        EntityRendererRegistryImpl.register(ModEntities.GINKGO_BOAT, context -> new BoatRenderer(context, ModModelLayers.GINKGO_BOAT));
        EntityRendererRegistryImpl.register(ModEntities.GINKGO_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.GINKGO_CHEST_BOAT));
    }
}
