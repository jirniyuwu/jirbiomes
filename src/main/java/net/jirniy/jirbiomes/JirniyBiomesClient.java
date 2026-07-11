package net.jirniy.jirbiomes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.client.color.block.BlockTintSources;

import java.util.List;

public class JirniyBiomesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ModBlocks.DRIED_GRASS_BLOCK);
        BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ModBlocks.WET_GRASS_BLOCK);
    }
}
