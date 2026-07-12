package net.jirniy.jirbiomes.item.food;

import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.*;

public class ModFood {
    public class Properties {

    }
    public class Effects {
        public static final Consumable DROP_APPLE_SEEDS = Consumables.defaultFood()
                .onConsume(new DropSeedsConsumeEffect(ModItems.APPLE_SEEDS, 0.25f)).build();
    }
    record Type<T extends ConsumeEffect>(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        public static final ConsumeEffect.Type<DropSeedsConsumeEffect> DROP_SEEDS_EFFECTS = register(
                "drop_seeds", DropSeedsConsumeEffect.CODEC, DropSeedsConsumeEffect.STREAM_CODEC
        );

        private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(
                final String name, final MapCodec<T> codec, final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec
        ) {
            return Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, name, new ConsumeEffect.Type<>(codec, streamCodec));
        }
    }
}
