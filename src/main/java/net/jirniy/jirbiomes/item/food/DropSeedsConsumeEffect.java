package net.jirniy.jirbiomes.item.food;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public record DropSeedsConsumeEffect(ItemStackTemplate drop, float probability) implements ConsumeEffect {
    public static final MapCodec<DropSeedsConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            ItemStackTemplate.CODEC.fieldOf("drop").forGetter(DropSeedsConsumeEffect::drop),
                            Codec.floatRange(0.0F, 1.0F).optionalFieldOf("probability", 1.0F).forGetter(DropSeedsConsumeEffect::probability)
                    )
                    .apply(i, DropSeedsConsumeEffect::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, DropSeedsConsumeEffect> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            DropSeedsConsumeEffect::drop,
            ByteBufCodecs.FLOAT,
            DropSeedsConsumeEffect::probability,
            DropSeedsConsumeEffect::new
    );

    public DropSeedsConsumeEffect(final ItemLike drop) {
        this(drop, 1.0f);
    }
    public DropSeedsConsumeEffect(final ItemLike drop, final float probability) {
        this(new ItemStackTemplate(drop.asItem()), probability);
    }
    public DropSeedsConsumeEffect(final ItemStackTemplate drop) {
        this(drop, 1.0f);
    }

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return ModFood.Type.DROP_SEEDS_EFFECTS;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {
        if (user instanceof Player player && user.getRandom().nextFloat() <= this.probability) {
            if (!player.isCreative() && !player.addItem(drop.create())) {
                player.drop(drop.create(), false);
            }
            return true;
        }
        return false;
    }
}
