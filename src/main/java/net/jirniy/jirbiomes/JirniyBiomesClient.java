package net.jirniy.jirbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.impl.client.rendering.EntityRendererRegistryImpl;
import net.jirniy.jirbiomes.block.ModFluids;
import net.jirniy.jirbiomes.entity.ModEntities;
import net.jirniy.jirbiomes.entity.ModModelLayers;
import net.jirniy.jirbiomes.particle.*;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.util.ARGB;

public class JirniyBiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(ModParticles.GINKGO_LEAVES, FallingLeavesParticle.CherryProvider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.SNOWFLAKE, ModLeavesParticle.SnowflakeProvider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.BRIMGRASS_SPORES, ModDripParticle.BrimgrassAmbientProvider::new);

        ParticleProviderRegistry.getInstance().register(ModParticles.TARR_BUBBLE, TarrBubbleParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.TARR_BUBBLE_POP, TarrBubblePopParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.DRIPPING_TARR, ModDripParticle.TarrHangProvider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.FALLING_TARR, ModDripParticle.TarrFallProvider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.LANDING_TARR, ModDripParticle.TarrLandProvider::new);

        FluidRenderingRegistry.register(ModFluids.TARR, ModFluids.FLOWING_TARR, new FluidModel.Unbaked(
                new Material(JirniyBiomes.id("block/tarr_still")),
                new Material(JirniyBiomes.id("block/tarr_flow")),
                null,
                BlockTintSources.constant(ARGB.opaque(0xFFFFFF))
        ));

        EntityRendererRegistryImpl.register(ModEntities.GINKGO_BOAT, context -> new BoatRenderer(context, ModModelLayers.GINKGO_BOAT));
        EntityRendererRegistryImpl.register(ModEntities.GINKGO_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.GINKGO_CHEST_BOAT));
        EntityRendererRegistryImpl.register(ModEntities.PALM_BOAT, context -> new BoatRenderer(context, ModModelLayers.PALM_BOAT));
        EntityRendererRegistryImpl.register(ModEntities.PALM_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.PALM_CHEST_BOAT));
        EntityRendererRegistryImpl.register(ModEntities.TAXITE_BOAT, context -> new BoatRenderer(context, ModModelLayers.TAXITE_BOAT));
        EntityRendererRegistryImpl.register(ModEntities.TAXITE_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.TAXITE_CHEST_BOAT));
    }
}
