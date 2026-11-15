package net.jirniy.jirbiomes.misc;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;

public class ModContextIntProviders {
    public static final ResourceKey<ContextIntProvider> COOKING_TIME_WOOD_PLATFORMS = createKey("cooking/time_wood_platforms");
    public static final ResourceKey<ContextIntProvider> COOKING_TIME_STRAW_BLOCK = createKey("cooking/time_straw_block");
    public static final ResourceKey<ContextIntProvider> COOKING_TIME_TARR_BUCKET = createKey("cooking/time_tarr_bucket");

    private static ResourceKey<ContextIntProvider> createKey(final String path) {
        return ResourceKey.create(Registries.CONTEXT_INT_PROVIDER, JirniyBiomes.id(path));
    }

}
