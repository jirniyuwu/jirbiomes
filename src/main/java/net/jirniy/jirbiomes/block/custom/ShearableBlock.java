package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class ShearableBlock extends Block {
    public static final BooleanProperty SHEARED = BooleanProperty.create("sheared");

    public ShearableBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(SHEARED, false));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!player.isShiftKeyDown() && !state.getValue(SHEARED) && itemStack.is(Items.SHEARS)) {
            level.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.SHEARS_SNIP, SoundSource.BLOCKS);
            level.setBlock(pos, state.setValue(SHEARED, true), 1);
            itemStack.hurtAndBreak(1, player, hand);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state.setValue(SHEARED, true)));
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SHEARED);
    }
}
