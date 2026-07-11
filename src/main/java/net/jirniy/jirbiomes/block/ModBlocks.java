package net.jirniy.jirbiomes.block;

import com.mojang.datafixers.types.Func;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.item.ModCreativeModeTabs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
    public static final Block IRON_GRATE = registerBlock("iron_grate", true, properties ->
            new WaterloggedTransparentBlock(properties.noOcclusion().strength(3f).requiresCorrectToolForDrops()
                    .isValidSpawn(Blocks::never).isRedstoneConductor(Blocks::never).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                    .mapColor(MapColor.METAL).pushReaction(PushReaction.NORMAL).sound(SoundType.COPPER_GRATE)));

    private static Block registerBlock(String name, boolean addItem, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, JirniyBiomes.id(name))));
        if (addItem) {
            registerBlockItem(name, toRegister);
        }
        return Registry.register(BuiltInRegistries.BLOCK, JirniyBiomes.id(name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Item item = Registry.register(BuiltInRegistries.ITEM, JirniyBiomes.id(name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, JirniyBiomes.id(name)))));
        CreativeModeTabEvents.modifyOutputEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, JirniyBiomes.id("jirbiomes_tab"))).register(output -> {
            output.accept(item);
        });
    }
    public static void register() {
        JirniyBiomes.LOGGER.info("registering blocks for " + JirniyBiomes.MOD_ID);
    }
}
