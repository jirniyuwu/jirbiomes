package net.jirniy.jirbiomes.block;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.custom.*;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.jirniy.jirbiomes.worldgen.ModTreeGrowers;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
    public static final Block NETHERSTONE = registerBlock("netherstone", properties ->
            new Block(properties.mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(0.34F).sound(SoundType.NETHERRACK)));

    public static final Block IRON_GRATE = registerBlock("iron_grate", properties ->
            new WaterloggedTransparentBlock(properties.noOcclusion().strength(3f).requiresCorrectToolForDrops()
                    .isValidSpawn(Blocks::never).isRedstoneConductor(Blocks::never).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                    .mapColor(MapColor.METAL).pushReaction(PushReaction.NORMAL).sound(SoundType.COPPER_GRATE)));

    public static final Block DRIED_DIRT = registerBlock("dried_dirt", properties ->
            new Block(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block ROOTED_DRIED_DIRT = registerBlock("rooted_dried_dirt", properties ->
            new Block(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block COARSE_DRIED_DIRT = registerBlock("coarse_dried_dirt", properties ->
            new Block(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block DRIED_GRASS_BLOCK = registerBlock("dried_grass_block", properties ->
            new CustomGrassBlock(getKey(DRIED_DIRT), properties.strength(0.4f).randomTicks()
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block DRY_FARMLAND = registerBlock("dry_farmland", properties ->
            new CustomFarmlandBlock(DRIED_DIRT, properties.strength(0.4f).randomTicks()
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block DRIED_DIRT_PATH = registerBlock("dried_dirt_path", properties ->
            new CustomPathBlock(DRIED_DIRT, properties.strength(0.6f).isViewBlocking(Blocks::always).isSuffocating(Blocks::always)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));


    public static final Block WETLAND = registerBlock("wetland", properties ->
            new Block(properties.strength(0.6f).speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block ROOTED_WETLAND = registerBlock("rooted_wetland", properties ->
            new Block(properties.strength(0.6f).speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block COARSE_WETLAND = registerBlock("coarse_wetland", properties ->
            new Block(properties.strength(0.6f).speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WET_GRASS_BLOCK = registerBlock("wet_grass_block", properties ->
            new CustomGrassBlock(getKey(WETLAND), properties.strength(0.6f).speedFactor(0.98f).randomTicks()
                    .mapColor(MapColor.GRASS).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WET_FARMLAND = registerBlock("wet_farmland", properties ->
            new CustomFarmlandBlock(WETLAND, properties.strength(0.6f).randomTicks().speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WETLAND_PATH = registerBlock("wetland_path", properties ->
            new CustomPathBlock(WETLAND, properties.strength(0.8f).isViewBlocking(Blocks::always).isSuffocating(Blocks::always)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));

    public static final Block GINKGO_LOG = registerBlock("ginkgo_log", properties ->
            new RotatedPillarBlock(logProperties(properties, MapColor.SAND, MapColor.TERRACOTTA_LIGHT_GRAY, SoundType.WOOD)));
    public static final Block GINKGO_WOOD = registerBlock("ginkgo_wood", properties ->
            new RotatedPillarBlock(properties.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_GINKGO_LOG = registerBlock("stripped_ginkgo_log", properties ->
            new RotatedPillarBlock(logProperties(properties, MapColor.SAND, MapColor.SAND, SoundType.WOOD)));
    public static final Block STRIPPED_GINKGO_WOOD = registerBlock("stripped_ginkgo_wood", properties ->
            new RotatedPillarBlock(properties.mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block GINKGO_LEAVES = registerBlock("ginkgo_leaves", properties ->
            new UntintedParticleLeavesBlock(0.1F, ModParticles.GINKGO_LEAVES, properties.mapColor(MapColor.COLOR_YELLOW)
                    .strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                    .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));

    public static final Block GINKGO_SAPLING = registerBlock("ginkgo_sapling", properties ->
            new SaplingBlock(ModTreeGrowers.GINKGO, properties.mapColor(MapColor.COLOR_YELLOW).noCollision().randomTicks()
                    .instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Block POTTED_GINKGO_SAPLING = registerBlock("potted_ginkgo_sapling", false, properties ->
            new FlowerPotBlock(ModBlocks.GINKGO_SAPLING, properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block GINKGO_PLANKS = registerBlock("ginkgo_planks", properties ->
            new Block(properties.mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_STAIRS = registerBlock("ginkgo_stairs", properties ->
            new StairBlock(GINKGO_PLANKS.defaultBlockState(), properties.mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_SLAB = registerBlock("ginkgo_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_BUTTON = registerBlock("ginkgo_button", properties ->
            new ButtonBlock(BlockSetType.BIRCH, 20, properties.noCollision().pushReaction(PushReaction.DESTROY)
                    .strength(1.0F, 2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_PRESSURE_PLATE = registerBlock("ginkgo_pressure_plate", properties ->
            new PressurePlateBlock(BlockSetType.BIRCH, properties.noCollision().pushReaction(PushReaction.DESTROY)
                    .strength(1.0F, 2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_FENCE = registerBlock("ginkgo_fence", properties ->
            new FenceBlock(properties.mapColor(MapColor.SAND)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_FENCE_GATE = registerBlock("ginkgo_fence_gate", properties ->
            new FenceGateBlock(WoodType.BIRCH, properties.mapColor(MapColor.SAND)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_TRAPDOOR = registerBlock("ginkgo_trapdoor", properties ->
            new TrapDoorBlock(BlockSetType.BIRCH, properties.mapColor(MapColor.SAND)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()
                    .pushReaction(PushReaction.DESTROY).noOcclusion()));
    public static final Block GINKGO_DOOR = registerBlock("ginkgo_door", properties ->
            new DoorBlock(BlockSetType.BIRCH, properties.mapColor(MapColor.SAND)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()
                    .pushReaction(PushReaction.DESTROY).noOcclusion()));

    public static final Block APPLE_CROP = registerBlock("apple_crop", false, properties ->
            new AppleCropBlock(properties.instabreak().pushReaction(PushReaction.DESTROY).noOcclusion().mapColor(MapColor.GRASS)
                    .noCollision().randomTicks().sound(SoundType.CROP)));

    public static final Block APPLE_LEAVES = registerBlock("apple_leaves", properties ->
            new AppleLeavesBlock(0.01F, properties.mapColor(MapColor.PLANT)
                    .strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                    .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));
    public static final Block APPLE_OAK_SAPLING = registerBlock("apple_oak_sapling", properties ->
            new SaplingBlock(ModTreeGrowers.APPLE_OAK, properties.mapColor(MapColor.PLANT).noCollision().randomTicks()
                    .instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Block POTTED_APPLE_OAK_SAPLING = registerBlock("potted_apple_oak_sapling", false, properties ->
            new FlowerPotBlock(ModBlocks.APPLE_OAK_SAPLING, properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    private static Block registerBlock(String name, boolean addItem, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, JirniyBiomes.id(name))));
        if (addItem) {
            registerBlockItem(name, toRegister, new Item.Properties());
        }
        return Registry.register(BuiltInRegistries.BLOCK, JirniyBiomes.id(name), toRegister);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        return registerBlock(name, true, function);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockProperties, Item.Properties itemProperties) {
        Block toRegister = blockProperties.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, JirniyBiomes.id(name))));
        registerBlockItem(name, toRegister, itemProperties);
        return Registry.register(BuiltInRegistries.BLOCK, JirniyBiomes.id(name), toRegister);
    }

    public static ResourceKey<Block> getKey(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }
    public static ResourceKey<Block>[] getKeys(Block... blocks) {
        ResourceKey<Block>[] keys = new ResourceKey[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            keys[i] = getKey(blocks[i]);
        }
        return keys;
    }

    private static void registerBlockItem(String name, Block block, Item.Properties itemProperties) {
        Item item = Registry.register(BuiltInRegistries.ITEM, JirniyBiomes.id(name),
                new BlockItem(block, itemProperties.useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, JirniyBiomes.id(name)))));
        CreativeModeTabEvents.modifyOutputEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, JirniyBiomes.id("jirbiomes_tab"))).register(output -> {
            output.accept(item);
        });
    }

    public static BlockBehaviour.Properties logProperties(BlockBehaviour.Properties properties, final MapColor topColor, final MapColor sideColor, final SoundType soundType) {
        return properties
                .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : sideColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(soundType)
                .ignitedByLava();
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering blocks for " + JirniyBiomes.MOD_ID);
    }
}
