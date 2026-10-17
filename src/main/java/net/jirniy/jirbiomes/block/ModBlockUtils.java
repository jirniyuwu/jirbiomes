package net.jirniy.jirbiomes.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ModBlockUtils {
    public static BlockBehaviour.Properties logProperties(BlockBehaviour.Properties properties, final MapColor topColor, final MapColor sideColor, final SoundType soundType) {
        return properties
                .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : sideColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(soundType)
                .ignitedByLava();
    }

    public static BlockBehaviour.Properties wallVariant(final Block standingBlock, final boolean copyName, final BlockBehaviour.Properties properties) {
        BlockBehaviour.Properties wallProperties = properties.overrideLootTable(standingBlock.getLootTable());
        if (copyName) {
            wallProperties = wallProperties.overrideDescription(standingBlock.getDescriptionId());
        }

        return wallProperties;
    }
}
