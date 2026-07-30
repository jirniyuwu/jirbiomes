package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class ModTreeDecoratorTypes<P extends TreeDecorator> {
    public static final TreeDecoratorType<CoconutDecorator> COCONUT = create("coconut", CoconutDecorator.CODEC);

    private final MapCodec<P> codec;

    private static <P extends TreeDecorator> TreeDecoratorType<P> create(final String name, final MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, JirniyBiomes.id(name), new TreeDecoratorType(codec));
    }

    public ModTreeDecoratorTypes(final MapCodec<P> codec) {
        this.codec = codec;
    }

    public MapCodec<P> codec() {
        return this.codec;
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering tree decorators for " + JirniyBiomes.MOD_ID);
    }
}
