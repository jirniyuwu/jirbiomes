package net.jirniy.jirbiomes.misc;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> CACTUS_FOOD = ResourceKey.create(Registries.DAMAGE_TYPE,
            JirniyBiomes.id("cactus_food"));

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(CACTUS_FOOD, new DamageType("cactus_food", 0.1f));
    }

    public static DamageSource create(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }
}
