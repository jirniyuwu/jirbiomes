package net.jirniy.jirbiomes.item.food;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public record DealDamageConsumeEffect(float damage, float probability) implements ConsumeEffect {
    public static final MapCodec<DealDamageConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            Codec.floatRange(0.0F, 512.0F).optionalFieldOf("damage", 1.0F).forGetter(DealDamageConsumeEffect::damage),
                            Codec.floatRange(0.0F, 1.0F).optionalFieldOf("probability", 1.0F).forGetter(DealDamageConsumeEffect::probability))
                    .apply(i, DealDamageConsumeEffect::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, DealDamageConsumeEffect> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            DealDamageConsumeEffect::damage,
            ByteBufCodecs.FLOAT,
            DealDamageConsumeEffect::probability,
            DealDamageConsumeEffect::new
    );

    public DealDamageConsumeEffect(final float damage) {
        this(damage, 1.0f);
    }

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return ModFood.Type.DROP_SEEDS_EFFECTS;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {
        if (level instanceof ServerLevel serverLevel && user instanceof Player player && user.getRandom().nextFloat() <= this.probability) {
            if (!player.isCreative()) {
                player.hurtServer(serverLevel, serverLevel.damageSources().cactus(), damage);
            }
            return true;
        }
        return false;
    }
}
