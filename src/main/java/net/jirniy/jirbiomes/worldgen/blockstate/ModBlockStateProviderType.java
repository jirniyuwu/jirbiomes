package net.jirniy.jirbiomes.worldgen.blockstate;

import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModBlockStateProviderType<P extends BlockStateProvider> {
    public static final MapCodec<MapStateProvider> MAP_STATE_PROVIDER = registerProvider(
            "map_state_provider", MapStateProvider.CODEC);
    public static final MapCodec<WeightedStateProviderProvider> WEIGHTED_STATE_PROVIDER_PROVIDER = registerProvider(
            "weighted_state_provider_provider", WeightedStateProviderProvider.CODEC);

    private static <P extends BlockStateProvider> MapCodec<P> registerProvider(final String name, final MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.BLOCK_STATE_PROVIDER_TYPE, JirniyBiomes.id(name), codec);
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering block state providers for " + JirniyBiomes.MOD_ID);
    }
}
