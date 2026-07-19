package net.jirniy.jirbiomes.effect;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.effect.custom.FlamingEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.level.biome.Biome;

public class ModEffects {
    public static final Holder<MobEffect> FLAMING = registerMobEffect("flaming",
            new FlamingEffect(MobEffectCategory.HARMFUL, 0xFFAA3F));

    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, JirniyBiomes.id(name), effect);
    }

    public static void registerEffects() {
        JirniyBiomes.LOGGER.info("Registering effects for " + JirniyBiomes.MOD_ID);
    }
}
