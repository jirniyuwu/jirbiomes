package net.jirniy.jirbiomes.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class PotentMagmaBlock extends MagmaLikeBlock {
    public static final MapCodec<PotentMagmaBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance
                    .group(Block.CODEC.fieldOf("on_contact_water").forGetter(block -> block.waterTurningInto),
                           Block.CODEC.fieldOf("on_broken").forGetter(block -> block.breakTurningInto),
                           Codec.intRange(0, 65535).optionalFieldOf("fire_ticks", 0).forGetter(block -> block.fireTicks), propertiesCodec())
                    .apply(instance, PotentMagmaBlock::new)
    );
    @Override
    public MapCodec<? extends PotentMagmaBlock> codec() {
        return CODEC;
    }
    private final Block breakTurningInto;
    private final int fireTicks;

    public PotentMagmaBlock(Block onContactWater, Block onBreak, Properties properties) {
        super(onContactWater, properties);
        this.breakTurningInto = onBreak;
        this.fireTicks = 0;
    }

    public PotentMagmaBlock(Block onContactWater, Block onBreak, int fireTicks, Properties properties) {
        super(onContactWater, properties);
        this.breakTurningInto = onBreak;
        this.fireTicks = fireTicks;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !entity.fireImmune()) {
            if (entity.getRemainingFireTicks() < this.fireTicks) {
                entity.setRemainingFireTicks(this.fireTicks);
            }
        }
        super.stepOn(level, pos, onState, entity);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
        if (!EnchantmentHelper.hasTag(destroyedWith, EnchantmentTags.PREVENTS_ICE_MELTING)) {
            BlockState belowState = level.getBlockState(pos.below());
            if (belowState.blocksMotion() || belowState.liquid()) {
                level.setBlockAndUpdate(pos, this.breakTurningInto.defaultBlockState());
            }
        }
    }
}
