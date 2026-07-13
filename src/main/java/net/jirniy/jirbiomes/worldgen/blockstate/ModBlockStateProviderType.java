package net.jirniy.jirbiomes.worldgen.blockstate;

import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;

public class ModBlockStateProviderType<P extends BlockStateProvider> {
    public static final BlockStateProviderType<MapStateProvider> MAP_STATE_PROVIDER = registerProvider(
            "map_state_provider", MapStateProvider.CODEC
    );

    private static <P extends BlockStateProvider> BlockStateProviderType<P> registerProvider(final String name, final MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.BLOCKSTATE_PROVIDER_TYPE, name, new BlockStateProviderType<>(codec));
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering block state providers for " + JirniyBiomes.MOD_ID);
    }
}
