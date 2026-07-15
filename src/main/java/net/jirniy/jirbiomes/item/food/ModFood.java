package net.jirniy.jirbiomes.item.food;

import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ConsumeEffect;

public class ModFood {
    public class Properties {
        public static final FoodProperties PRICKLY_PEAR = new FoodProperties.Builder().nutrition(3).saturationModifier(0.4F).build();
    }
    public class Effects {
        public static final Consumable DROP_APPLE_SEEDS = Consumables.defaultFood()
                .onConsume(new DropSeedsConsumeEffect(ModItems.APPLE_SEEDS, 0.25f)).build();
        public static final Consumable PRICKLY_PEAR_EFFECT = Consumables.defaultFood()
                .onConsume(new DealDamageConsumeEffect(1f, 0.9f)).build();
    }
    record Type<T extends ConsumeEffect>(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        public static final ConsumeEffect.Type<DropSeedsConsumeEffect> DROP_SEEDS_EFFECTS = register(
                "drop_seeds", DropSeedsConsumeEffect.CODEC, DropSeedsConsumeEffect.STREAM_CODEC);
        public static final ConsumeEffect.Type<DealDamageConsumeEffect> DEAL_DAMAGE_EFFECT = register(
                "deal_damage", DealDamageConsumeEffect.CODEC, DealDamageConsumeEffect.STREAM_CODEC);

        private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(
                final String name, final MapCodec<T> codec, final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec
        ) {
            return Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, name, new ConsumeEffect.Type<>(codec, streamCodec));
        }
    }
}
