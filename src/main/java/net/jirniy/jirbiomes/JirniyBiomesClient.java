package net.jirniy.jirbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.impl.client.rendering.EntityRendererRegistryImpl;
import net.jirniy.jirbiomes.datagen.ModModelProvider;
import net.jirniy.jirbiomes.entity.ModEntities;
import net.jirniy.jirbiomes.entity.ModModelLayers;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class JirniyBiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(ModParticles.GINKGO_LEAVES, FallingLeavesParticle.CherryProvider::new);

        EntityRendererRegistryImpl.register(ModEntities.GINKGO_BOAT, context -> new BoatRenderer(context, ModModelLayers.GINKGO_BOAT));
        EntityRendererRegistryImpl.register(ModEntities.GINKGO_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.GINKGO_CHEST_BOAT));
    }
}
