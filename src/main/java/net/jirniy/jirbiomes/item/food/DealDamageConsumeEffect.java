package net.jirniy.jirbiomes.item.food;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public record DealDamageConsumeEffect(float damage, ResourceKey<DamageType> damageType, float probability) implements ConsumeEffect {
    public static final MapCodec<DealDamageConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            Codec.floatRange(0.0F, 512.0F).optionalFieldOf("damage", 1.0F).forGetter(DealDamageConsumeEffect::damage),
                            ResourceKey.codec(Registries.DAMAGE_TYPE).fieldOf("damage_type").forGetter(DealDamageConsumeEffect::damageType),
                            Codec.floatRange(0.0F, 1.0F).optionalFieldOf("probability", 1.0F).forGetter(DealDamageConsumeEffect::probability))
                    .apply(i, DealDamageConsumeEffect::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, DealDamageConsumeEffect> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            DealDamageConsumeEffect::damage,
            ResourceKey.streamCodec(Registries.DAMAGE_TYPE),
            DealDamageConsumeEffect::damageType,
            ByteBufCodecs.FLOAT,
            DealDamageConsumeEffect::probability,
            DealDamageConsumeEffect::new
    );

    public DealDamageConsumeEffect(final float damage, ResourceKey<DamageType> damageType) {
        this(damage, damageType, 1.0f);
    }

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return ModFood.Type.DEAL_DAMAGE_EFFECT;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {
        if (level instanceof ServerLevel serverLevel && user instanceof Player player && user.getRandom().nextFloat() <= this.probability) {
            if (!player.isCreative()) {
                player.hurtServer(serverLevel, serverLevel.damageSources().source(damageType), damage);
            }
            return true;
        }
        return false;
    }
}
