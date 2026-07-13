package net.jirniy.jirbiomes.mixin;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Predicate;

@Mixin(EatBlockGoal.class)
public class SheepEdibleMixin {
    @Unique
    private static final Map<Block, BlockState> BLOCK_TRANSFORM = Map.of(
            Blocks.GRASS_BLOCK, Blocks.DIRT.defaultBlockState(),
            ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT.defaultBlockState(),
            ModBlocks.WET_GRASS_BLOCK, ModBlocks.WETLAND.defaultBlockState()
    );

    @Shadow
    private static final Predicate<BlockState> IS_EDIBLE = state -> state.is(BlockTags.EDIBLE_FOR_SHEEP);
    @Shadow @Final
    private Mob mob;
    @Shadow @Final
    private Level level;

    @Inject(method = "canUse", at = @At("TAIL"), cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> cir) {

        BlockPos pos = this.mob.blockPosition();
        cir.setReturnValue(IS_EDIBLE.test(this.level.getBlockState(pos)) || this.level.getBlockState(pos.below()).is(ModTags.Blocks.GRASS_BLOCKS));
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;below()Lnet/minecraft/core/BlockPos;"), cancellable = true)
    private void tick(CallbackInfo ci) {
        ci.cancel();
        BlockPos pos = this.mob.blockPosition();
        BlockPos below = pos.below();
        if (this.level.getBlockState(below).is(ModTags.Blocks.GRASS_BLOCKS)) {
            if (getServerLevel(this.level).getGameRules().get(GameRules.MOB_GRIEFING)) {
                Block blockBelow = this.level.getBlockState(below).getBlock();
                if (BLOCK_TRANSFORM.get(blockBelow) != null) {
                    this.level.levelEvent(2001, below, Block.getId(blockBelow.defaultBlockState()));
                    this.level.setBlock(below, BLOCK_TRANSFORM.get(blockBelow), 2);
                }
            }

            this.mob.ate();
        }
    }

    private static ServerLevel getServerLevel(final Level level) {
        return (ServerLevel)level;
    }
}
