package net.jirniy.jirbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.ParticleEngine;

import java.util.List;

public class JirniyBiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(ModParticles.GINKGO_LEAVES, FallingLeavesParticle.CherryProvider::new);
    }
}
