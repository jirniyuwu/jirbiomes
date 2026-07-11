package net.jirniy.jirbiomes.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ModCreativeModeTabs {
    public static final CreativeModeTab JIRBIOMES_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, JirniyBiomes.id("jirbiomes_tab"),
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.DRIED_GRASS_BLOCK))
                    .title(Component.translatable("creativemodetab.jirbiomes.jirbiomes_tab"))
                    .build());

    public static void register() {
        JirniyBiomes.LOGGER.info("registering creative mode tabs for " + JirniyBiomes.MOD_ID);
    }
}
