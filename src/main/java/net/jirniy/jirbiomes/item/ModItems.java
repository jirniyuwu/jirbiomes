package net.jirniy.jirbiomes.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class ModItems {

    public static final Item APPLE_SEEDS = registerItem("apple_seeds", properties -> new Item(properties.stacksTo(64)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        Item item = Registry.register(BuiltInRegistries.ITEM, JirniyBiomes.id(name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, JirniyBiomes.id((name))))));
        CreativeModeTabEvents.modifyOutputEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, JirniyBiomes.id("jirbiomes_tab"))).register(output -> {
            output.accept(item);
        });
        return item;
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering items for " + JirniyBiomes.MOD_ID);
    }

    public static ResourceKey<Item> getKey(ItemLike item) {
        return BuiltInRegistries.ITEM.getResourceKey(item.asItem()).get();
    }
    public static ResourceKey<Item>[] getKeys(ItemLike... items) {
        ResourceKey<Item>[] keys = new ResourceKey[items.length];
        for (int i = 0; i < items.length; i++) {
            keys[i] = getKey(items[i]);
        }
        return keys;
    }
}
