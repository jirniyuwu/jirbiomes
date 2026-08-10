package net.jirniy.jirbiomes.block;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.custom.*;
import net.jirniy.jirbiomes.misc.ModTags;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.jirniy.jirbiomes.worldgen.ModPlacedFeatures;
import net.jirniy.jirbiomes.worldgen.ModTreeGrowers;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
    public static final Block IRON_GRATE = registerBlock("iron_grate", properties ->
            new WaterloggedTransparentBlock(properties.noOcclusion().strength(3f).requiresCorrectToolForDrops()
                    .isValidSpawn(Blocks::never).isRedstoneConductor(Blocks::never).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                    .mapColor(MapColor.METAL).pushReaction(PushReaction.NORMAL).sound(SoundType.COPPER_GRATE)));

    public static final Block ROOTED_SAND = registerBlock("rooted_sand", properties ->
            new FallingRootedBlock(new ColorRGBA(14406560), Blocks.SAND, properties.mapColor(MapColor.SAND)
                    .instrument(NoteBlockInstrument.SNARE).strength(0.55F).sound(SoundType.SAND)));
    public static final Block ROOTED_RED_SAND = registerBlock("rooted_red_sand", properties ->
            new FallingRootedBlock(new ColorRGBA(11098145), Blocks.RED_SAND, properties.mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.SNARE).strength(0.55F).sound(SoundType.SAND)));
    public static final Block ROOTED_MUD = registerBlock("rooted_mud", properties ->
            new RootedMudBlock(properties.mapColor(MapColor.TERRACOTTA_CYAN).strength(0.55F)
                    .isValidSpawn(Blocks::always).isRedstoneConductor(Blocks::always).isViewBlocking(Blocks::always)
                    .isSuffocating(Blocks::always).sound(SoundType.MUD)));

    public static final Block DRIED_DIRT = registerBlock("dried_dirt", properties ->
            new Block(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block ROOTED_DRIED_DIRT = registerBlock("rooted_dried_dirt", properties ->
            new RootedDirtBlock(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block COARSE_DRIED_DIRT = registerBlock("coarse_dried_dirt", properties ->
            new Block(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block DRIED_GRASS_BLOCK = registerBlock("dried_grass_block", properties ->
            new CustomGrassBlock(getKey(DRIED_DIRT), VegetationPlacements.GRASS_BONEMEAL, properties.strength(0.4f).randomTicks()
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
            new RootedDirtBlock(properties.strength(0.6f).speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block COARSE_WETLAND = registerBlock("coarse_wetland", properties ->
            new Block(properties.strength(0.6f).speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WET_GRASS_BLOCK = registerBlock("wet_grass_block", properties ->
            new CustomGrassBlock(getKey(WETLAND), VegetationPlacements.GRASS_BONEMEAL, properties.strength(0.6f).speedFactor(0.98f).randomTicks()
                    .mapColor(MapColor.GRASS).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WET_FARMLAND = registerBlock("wet_farmland", properties ->
            new CustomFarmlandBlock(WETLAND, properties.strength(0.6f).randomTicks().speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WETLAND_PATH = registerBlock("wetland_path", properties ->
            new CustomPathBlock(WETLAND, properties.strength(0.8f).isViewBlocking(Blocks::always).isSuffocating(Blocks::always)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));

    public static final Block CATTAIL = registerBlock("cattail", properties ->
            new CattailBlock(properties.instabreak().mapColor(MapColor.TERRACOTTA_BROWN).noCollision().noOcclusion().ignitedByLava()
                    .randomTicks().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).sound(SoundType.CROP)));
    public static final Block ALGAE = registerBlock("algae", false, properties ->
            new AlgaeBlock(properties.instabreak().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).noCollision().noOcclusion().ignitedByLava().speedFactor(0.8f)
                    .noLootTable().replaceable().randomTicks().pushReaction(PushReaction.DESTROY).sound(SoundType.WET_SPONGE)));
    public static final Block FROZEN_GRASS = registerBlock("frozen_grass", properties ->
            new VegetationPlaceableOnBlock(ModTags.Blocks.SUPPORTS_ICE_VEGETATION, properties.instabreak().mapColor(MapColor.ICE)
                    .noCollision().noOcclusion().ignitedByLava().offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY).sound(SoundType.GLASS)));
    public static final Block POTTED_FROZEN_GRASS = registerBlock("potted_frozen_grass", false, properties ->
            new FlowerPotBlock(ModBlocks.FROZEN_GRASS, properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block STRAW_BLOCK = registerBlock("straw_block", properties ->
            new RotatedPillarBlock(properties.strength(0.8f).mapColor(MapColor.TERRACOTTA_ORANGE).ignitedByLava()
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.GRASS).instrument(NoteBlockInstrument.BANJO)));
    public static final Block PACKED_STRAW = registerBlock("packed_straw", properties ->
            new Block(properties.strength(0.95f).mapColor(MapColor.TERRACOTTA_ORANGE).ignitedByLava()
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.GRASS).instrument(NoteBlockInstrument.BANJO)));
    public static final Block PACKED_STRAW_STAIRS = registerBlock("packed_straw_stairs", properties ->
            new StairBlock(PACKED_STRAW.defaultBlockState(), properties.strength(0.95f).mapColor(MapColor.TERRACOTTA_ORANGE).ignitedByLava()
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.GRASS).instrument(NoteBlockInstrument.BANJO)));
    public static final Block PACKED_STRAW_SLAB = registerBlock("packed_straw_slab", properties ->
            new SlabBlock(properties.strength(0.95f).mapColor(MapColor.TERRACOTTA_ORANGE).ignitedByLava()
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.GRASS).instrument(NoteBlockInstrument.BANJO)));

    public static final Block PACKED_SNOW = registerBlock("packed_snow", properties ->
            new Block(properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.4F).sound(SoundType.SNOW)));
    public static final Block POLISHED_SNOW = registerBlock("polished_snow", properties ->
            new Block(properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.7F).sound(SoundType.SNOW)));
    public static final Block POLISHED_SNOW_STAIRS = registerBlock("polished_snow_stairs", properties ->
            new StairBlock(POLISHED_SNOW.defaultBlockState(), properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.7F).sound(SoundType.SNOW)));
    public static final Block POLISHED_SNOW_SLAB = registerBlock("polished_snow_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.7F).sound(SoundType.SNOW)));
    public static final Block POLISHED_SNOW_WALL = registerBlock("polished_snow_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.7F).sound(SoundType.SNOW)));
    public static final Block SNOW_BRICKS = registerBlock("snow_bricks", properties ->
            new Block(properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.7F).sound(SoundType.SNOW)));
    public static final Block SNOW_BRICKS_STAIRS = registerBlock("snow_bricks_stairs", properties ->
            new StairBlock(SNOW_BRICKS.defaultBlockState(), properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.7F).sound(SoundType.SNOW)));
    public static final Block SNOW_BRICKS_SLAB = registerBlock("snow_bricks_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.7F).sound(SoundType.SNOW)));
    public static final Block SNOW_BRICKS_WALL = registerBlock("snow_bricks_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(0.7F).sound(SoundType.SNOW)));

    public static final Block NETHERSTONE = registerBlock("netherstone", properties ->
            new Block(properties.mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(0.34F).sound(SoundType.NETHERRACK)));
    public static final Block POLISHED_NETHERSTONE = registerBlock("polished_netherrack", properties ->
            new Block(properties.mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.NETHERRACK)));
    public static final Block POLISHED_NETHERSTONE_STAIRS = registerBlock("polished_netherrack_stairs", properties ->
            new StairBlock(POLISHED_NETHERSTONE.defaultBlockState(), properties.mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.NETHERRACK)));
    public static final Block POLISHED_NETHERSTONE_SLAB = registerBlock("polished_netherrack_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.NETHERRACK)));
    public static final Block POLISHED_NETHERSTONE_WALL = registerBlock("polished_netherrack_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.NETHERRACK)));
    public static final Block NETHERSTONE_BRICKS = registerBlock("netherrack_bricks", properties ->
            new Block(properties.mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.NETHERRACK)));
    public static final Block NETHERSTONE_BRICKS_STAIRS = registerBlock("netherrack_bricks_stairs", properties ->
            new StairBlock(NETHERSTONE_BRICKS.defaultBlockState(), properties.mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.NETHERRACK)));
    public static final Block NETHERSTONE_BRICKS_SLAB = registerBlock("netherrack_bricks_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.NETHERRACK)));
    public static final Block NETHERSTONE_BRICKS_WALL = registerBlock("netherrack_bricks_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.NETHERRACK)));

    public static final Block SALT_BLOCK = registerBlock("salt", properties ->
            new Block(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.COW_BELL)
                    .requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));
    public static final Block POLISHED_SALT = registerBlock("polished_salt", properties ->
            new Block(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.COW_BELL)
                    .requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));
    public static final Block POLISHED_SALT_STAIRS = registerBlock("polished_salt_stairs", properties ->
            new StairBlock(POLISHED_SALT.defaultBlockState(), properties.mapColor(MapColor.QUARTZ)
                    .instrument(NoteBlockInstrument.COW_BELL).requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));
    public static final Block POLISHED_SALT_SLAB = registerBlock("polished_salt_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.QUARTZ)
                    .instrument(NoteBlockInstrument.COW_BELL).requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));
    public static final Block POLISHED_SALT_WALL = registerBlock("polished_salt_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.QUARTZ)
                    .instrument(NoteBlockInstrument.COW_BELL).requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));
    public static final Block SALT_BRICKS = registerBlock("salt_bricks", properties ->
            new Block(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.COW_BELL)
                    .requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));
    public static final Block SALT_BRICKS_STAIRS = registerBlock("salt_bricks_stairs", properties ->
            new StairBlock(SALT_BRICKS.defaultBlockState(), properties.mapColor(MapColor.QUARTZ)
                    .instrument(NoteBlockInstrument.COW_BELL).requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));
    public static final Block SALT_BRICKS_SLAB = registerBlock("salt_bricks_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.QUARTZ)
                    .instrument(NoteBlockInstrument.COW_BELL).requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));
    public static final Block SALT_BRICKS_WALL = registerBlock("salt_bricks_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.QUARTZ)
                    .instrument(NoteBlockInstrument.COW_BELL).requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));

    public static final Block SALT_LAMP = registerBlock("salt_lamp", properties ->
            new LampBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.COW_BELL).lightLevel(LampBlock::getLight)
                    .strength(1.2F).sound(SoundType.CALCITE)));

    public static final Block BRIMSTONE = registerBlock("brimstone", properties ->
            new Block(properties.mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(2.8F).sound(SoundType.CINNABAR)));
    public static final Block IGNITED_BRIMSTONE = registerBlock("ignited_brimstone", properties ->
            new MagmaLikeBlock(ModBlocks.BRIMSTONE, properties.mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(2.8F).sound(SoundType.CINNABAR).lightLevel(state -> 6)));
    public static final Block BRIMSTONE_GOLD_ORE = registerBlock("brimstone_gold_ore", properties ->
            new Block(properties.mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.1F).sound(SoundType.CINNABAR)));
    public static final Block POLISHED_BRIMSTONE = registerBlock("polished_brimstone", properties ->
            new Block(properties.mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block POLISHED_BRIMSTONE_STAIRS = registerBlock("polished_brimstone_stairs", properties ->
            new StairBlock(POLISHED_BRIMSTONE.defaultBlockState(), properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block POLISHED_BRIMSTONE_SLAB = registerBlock("polished_brimstone_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block POLISHED_BRIMSTONE_WALL = registerBlock("polished_brimstone_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block BRIMSTONE_BRICKS = registerBlock("brimstone_bricks", properties ->
            new Block(properties.mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block BRIMSTONE_BRICKS_STAIRS = registerBlock("brimstone_bricks_stairs", properties ->
            new StairBlock(BRIMSTONE_BRICKS.defaultBlockState(), properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block BRIMSTONE_BRICKS_SLAB = registerBlock("brimstone_bricks_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block BRIMSTONE_BRICKS_WALL = registerBlock("brimstone_bricks_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block CRACKED_BRIMSTONE_BRICKS = registerBlock("cracked_brimstone_bricks", properties ->
            new Block(properties.mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block CRACKED_BRIMSTONE_BRICKS_STAIRS = registerBlock("cracked_brimstone_bricks_stairs", properties ->
            new StairBlock(CRACKED_BRIMSTONE_BRICKS.defaultBlockState(), properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block CRACKED_BRIMSTONE_BRICKS_SLAB = registerBlock("cracked_brimstone_bricks_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block CRACKED_BRIMSTONE_BRICKS_WALL = registerBlock("cracked_brimstone_bricks_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block MOSSY_BRIMSTONE_BRICKS = registerBlock("mossy_brimstone_bricks", properties ->
            new Block(properties.mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block MOSSY_BRIMSTONE_BRICKS_STAIRS = registerBlock("mossy_brimstone_bricks_stairs", properties ->
            new StairBlock(MOSSY_BRIMSTONE_BRICKS.defaultBlockState(), properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block MOSSY_BRIMSTONE_BRICKS_SLAB = registerBlock("mossy_brimstone_bricks_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block MOSSY_BRIMSTONE_BRICKS_WALL = registerBlock("mossy_brimstone_bricks_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));
    public static final Block CHISELED_BRIMSTONE_BRICKS = registerBlock("chiseled_brimstone_bricks", properties ->
            new Block(properties.mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).lightLevel(state -> 7)
                    .requiresCorrectToolForDrops().strength(3F).sound(SoundType.CINNABAR)));

    public static final Block BRIMGRASS_BLOCK = registerBlock("brimgrass_block", properties ->
            new CustomGrassBlock(getKey(BRIMSTONE), ModPlacedFeatures.BRIMGRASS_BONEMEAL, properties.mapColor(MapColor.TERRACOTTA_PURPLE).randomTicks()
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.1F).sound(SoundType.NETHER_SPROUTS)));
    public static final Block BRIMGRASS = registerBlock("brimgrass", properties ->
            new GenericGrassBlock(properties.replaceable().noCollision().instabreak().sound(SoundType.NETHER_SPROUTS)
                    .offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));

    public static final Block PERMAFROST_BLOCK = registerBlock("permafrost_block", properties ->
            new Block(properties.mapColor(MapColor.COLOR_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).friction(0.986f)
                    .sound(SoundType.GLASS).requiresCorrectToolForDrops().strength(4.5F, 7.0F)));
    public static final Block ICICLE = registerBlock("icicle", properties ->
            new IcicleBlock(PERMAFROST_BLOCK.defaultBlockState(), properties.mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion()
                    .sound(SoundType.GLASS).randomTicks().strength(0.5F, 0.2F)
                    .dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never).noOcclusion().friction(0.986f)));

    public static final Block FROSTED_STONE = registerBlock("frosted_stone", properties ->
            new Block(properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).friction(0.68f)
                    .requiresCorrectToolForDrops().strength(1.4F).sound(SoundType.STONE)));
    public static final Block POLISHED_FROST = registerBlock("polished_frost", properties ->
            new Block(properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(1.6F).sound(SoundType.STONE)));
    public static final Block POLISHED_FROST_STAIRS = registerBlock("polished_frost_stairs", properties ->
            new StairBlock(POLISHED_FROST.defaultBlockState(), properties.mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.6F).sound(SoundType.STONE)));
    public static final Block POLISHED_FROST_SLAB = registerBlock("polished_frost_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.6F).sound(SoundType.STONE)));
    public static final Block POLISHED_FROST_WALL = registerBlock("polished_frost_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.6F).sound(SoundType.STONE)));
    public static final Block FROST_BRICKS = registerBlock("frost_bricks", properties ->
            new Block(properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(1.6F).sound(SoundType.STONE)));
    public static final Block FROST_BRICKS_STAIRS = registerBlock("frost_bricks_stairs", properties ->
            new StairBlock(FROST_BRICKS.defaultBlockState(), properties.mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.6F).sound(SoundType.STONE)));
    public static final Block FROST_BRICKS_SLAB = registerBlock("frost_bricks_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.6F).sound(SoundType.STONE)));
    public static final Block FROST_BRICKS_WALL = registerBlock("frost_bricks_wall", properties ->
            new WallBlock(properties.mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.6F).sound(SoundType.STONE)));

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
            new ButtonBlock(ModBlockSetTypes.Sets.GINKGO, 20, properties.noCollision().pushReaction(PushReaction.DESTROY)
                    .strength(1.0F, 2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_PRESSURE_PLATE = registerBlock("ginkgo_pressure_plate", properties ->
            new PressurePlateBlock(ModBlockSetTypes.Sets.GINKGO, properties.noCollision().pushReaction(PushReaction.DESTROY)
                    .strength(1.0F, 2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_FENCE = registerBlock("ginkgo_fence", properties ->
            new FenceBlock(properties.mapColor(MapColor.SAND)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_FENCE_GATE = registerBlock("ginkgo_fence_gate", properties ->
            new FenceGateBlock(ModBlockSetTypes.WoodTypes.GINKGO, properties.mapColor(MapColor.SAND)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block GINKGO_TRAPDOOR = registerBlock("ginkgo_trapdoor", properties ->
            new TrapDoorBlock(ModBlockSetTypes.Sets.GINKGO, properties.mapColor(MapColor.SAND)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()
                    .pushReaction(PushReaction.DESTROY).noOcclusion()));
    public static final Block GINKGO_DOOR = registerBlock("ginkgo_door", properties ->
            new DoorBlock(ModBlockSetTypes.Sets.GINKGO, properties.mapColor(MapColor.SAND)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()
                    .pushReaction(PushReaction.DESTROY).noOcclusion()));

    public static final Block GINKGO_SHELF = registerBlockEntity("ginkgo_shelf", BlockEntityTypes.SHELF,
            properties -> new ShelfBlock(properties.mapColor(GINKGO_PLANKS.defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.SHELF)
                    .ignitedByLava().strength(2.0F, 3.0F)));
    public static final Block GINKGO_SIGN = registerBlockEntity("ginkgo_sign", BlockEntityTypes.SIGN, false,
            properties -> new StandingSignBlock(ModBlockSetTypes.WoodTypes.GINKGO, properties.mapColor(GINKGO_PLANKS.defaultMapColor())
                    .noCollision().strength(1.0F).ignitedByLava()));
    public static final Block WALL_GINKGO_SIGN = registerBlockEntity("ginkgo_wall_sign", BlockEntityTypes.SIGN, false,
            properties -> new WallSignBlock(ModBlockSetTypes.WoodTypes.GINKGO, wallVariant(GINKGO_SIGN, true,
                    properties.mapColor(GINKGO_PLANKS.defaultMapColor())
                            .noCollision().strength(1.0F).ignitedByLava())));
    public static final Block HANGING_GINKGO_SIGN = registerBlockEntity("ginkgo_hanging_sign", BlockEntityTypes.HANGING_SIGN, false,
            properties -> new CeilingHangingSignBlock(ModBlockSetTypes.WoodTypes.GINKGO, properties.mapColor(GINKGO_PLANKS.defaultMapColor())
                    .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision()
                    .strength(1.0F).ignitedByLava()));
    public static final Block HANGING_WALL_GINKGO_SIGN = registerBlockEntity("ginkgo_wall_hanging_sign", BlockEntityTypes.HANGING_SIGN, false,
            properties -> new WallHangingSignBlock(ModBlockSetTypes.WoodTypes.GINKGO, wallVariant(HANGING_GINKGO_SIGN, true,
                    properties.mapColor(GINKGO_PLANKS.defaultMapColor())
                            .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision()
                            .strength(1.0F).ignitedByLava())));

    public static final Block PALM_LOG = registerBlock("palm_log", properties ->
            new RotatedPillarBlock(logProperties(properties, MapColor.EMERALD, MapColor.TERRACOTTA_LIGHT_GRAY, SoundType.WOOD)));
    public static final Block PALM_WOOD = registerBlock("palm_wood", properties ->
            new RotatedPillarBlock(properties.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_PALM_LOG = registerBlock("stripped_palm_log", properties ->
            new RotatedPillarBlock(logProperties(properties, MapColor.EMERALD, MapColor.EMERALD, SoundType.WOOD)));
    public static final Block STRIPPED_PALM_WOOD = registerBlock("stripped_palm_wood", properties ->
            new RotatedPillarBlock(properties.mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block PALM_LEAVES = registerBlock("palm_leaves", properties ->
            new PalmLeavesBlock(properties.mapColor(MapColor.EMERALD).strength(0.2F).randomTicks()
                    .sound(SoundType.AZALEA_LEAVES).noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                    .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));

    public static final Block COCONUT_PLANT = registerBlock("coconut_plant", false, properties ->
            new CoconutBlock(properties.mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F)
                    .sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block PALM_SAPLING = registerBlock("palm_sapling", properties ->
            new SaplingWithAdditionalPlaceableBlock(ModTreeGrowers.PALM, ModTags.Blocks.PALM_PLACEABLE, properties.mapColor(MapColor.EMERALD).noCollision().randomTicks()
                    .instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Block POTTED_PALM_SAPLING = registerBlock("potted_palm_sapling", false, properties ->
            new FlowerPotBlock(ModBlocks.PALM_SAPLING, properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block PALM_PLANKS = registerBlock("palm_planks", properties ->
            new Block(properties.mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block PALM_STAIRS = registerBlock("palm_stairs", properties ->
            new StairBlock(PALM_PLANKS.defaultBlockState(), properties.mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block PALM_SLAB = registerBlock("palm_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block PALM_BUTTON = registerBlock("palm_button", properties ->
            new ButtonBlock(ModBlockSetTypes.Sets.PALM, 20, properties.noCollision().pushReaction(PushReaction.DESTROY)
                    .strength(1.0F, 2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block PALM_PRESSURE_PLATE = registerBlock("palm_pressure_plate", properties ->
            new PressurePlateBlock(ModBlockSetTypes.Sets.PALM, properties.noCollision().pushReaction(PushReaction.DESTROY)
                    .strength(1.0F, 2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block PALM_FENCE = registerBlock("palm_fence", properties ->
            new FenceBlock(properties.mapColor(MapColor.EMERALD)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block PALM_FENCE_GATE = registerBlock("palm_fence_gate", properties ->
            new FenceGateBlock(ModBlockSetTypes.WoodTypes.PALM, properties.mapColor(MapColor.EMERALD)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block PALM_TRAPDOOR = registerBlock("palm_trapdoor", properties ->
            new TrapDoorBlock(ModBlockSetTypes.Sets.PALM, properties.mapColor(MapColor.EMERALD)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()
                    .pushReaction(PushReaction.DESTROY).noOcclusion()));
    public static final Block PALM_DOOR = registerBlock("palm_door", properties ->
            new DoorBlock(ModBlockSetTypes.Sets.PALM, properties.mapColor(MapColor.EMERALD)
                    .strength(1.5F, 2.5F).sound(SoundType.WOOD).ignitedByLava()
                    .pushReaction(PushReaction.DESTROY).noOcclusion()));

    public static final Block PALM_SHELF = registerBlockEntity("palm_shelf", BlockEntityTypes.SHELF,
            properties -> new ShelfBlock(properties.mapColor(PALM_PLANKS.defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.SHELF)
                    .ignitedByLava().strength(2.0F, 3.0F)));
    public static final Block PALM_SIGN = registerBlockEntity("palm_sign", BlockEntityTypes.SIGN, false,
            properties -> new StandingSignBlock(ModBlockSetTypes.WoodTypes.PALM, properties.mapColor(PALM_PLANKS.defaultMapColor())
                    .noCollision().strength(1.0F).ignitedByLava()));
    public static final Block WALL_PALM_SIGN = registerBlockEntity("palm_wall_sign", BlockEntityTypes.SIGN, false,
            properties -> new WallSignBlock(ModBlockSetTypes.WoodTypes.PALM, wallVariant(PALM_SIGN, true,
                    properties.mapColor(PALM_PLANKS.defaultMapColor())
                            .noCollision().strength(1.0F).ignitedByLava())));
    public static final Block HANGING_PALM_SIGN = registerBlockEntity("palm_hanging_sign", BlockEntityTypes.HANGING_SIGN, false,
            properties -> new CeilingHangingSignBlock(ModBlockSetTypes.WoodTypes.PALM, properties.mapColor(PALM_PLANKS.defaultMapColor())
                    .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision()
                    .strength(1.0F).ignitedByLava()));
    public static final Block HANGING_WALL_PALM_SIGN = registerBlockEntity("palm_wall_hanging_sign", BlockEntityTypes.HANGING_SIGN, false,
            properties -> new WallHangingSignBlock(ModBlockSetTypes.WoodTypes.PALM, wallVariant(HANGING_PALM_SIGN, true,
                    properties.mapColor(PALM_PLANKS.defaultMapColor())
                            .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision()
                            .strength(1.0F).ignitedByLava())));

    public static final Block TENEBRIS_LOG = registerBlock("tenebris_stem", properties ->
            new RotatedPillarBlock(logProperties(properties, MapColor.COLOR_MAGENTA, MapColor.TERRACOTTA_BLACK, SoundType.NETHER_WOOD)));
    public static final Block TENEBRIS_WOOD = registerBlock("tenebris_hyphae", properties ->
            new RotatedPillarBlock(properties.mapColor(MapColor.COLOR_MAGENTA).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.NETHER_WOOD)));
    public static final Block STRIPPED_TENEBRIS_LOG = registerBlock("stripped_tenebris_stem", properties ->
            new RotatedPillarBlock(logProperties(properties, MapColor.COLOR_MAGENTA, MapColor.TERRACOTTA_BLACK, SoundType.NETHER_WOOD)));
    public static final Block STRIPPED_TENEBRIS_WOOD = registerBlock("stripped_tenebris_hyphae", properties ->
            new RotatedPillarBlock(properties.mapColor(MapColor.COLOR_MAGENTA).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.NETHER_WOOD)));

    public static final Block TENEBRIS_PLANKS = registerBlock("tenebris_planks", properties ->
            new Block(properties.mapColor(MapColor.COLOR_MAGENTA).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
    public static final Block TENEBRIS_STAIRS = registerBlock("tenebris_stairs", properties ->
            new StairBlock(TENEBRIS_PLANKS.defaultBlockState(), properties.mapColor(MapColor.COLOR_MAGENTA).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
    public static final Block TENEBRIS_SLAB = registerBlock("tenebris_slab", properties ->
            new SlabBlock(properties.mapColor(MapColor.COLOR_MAGENTA).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
    public static final Block TENEBRIS_BUTTON = registerBlock("tenebris_button", properties ->
            new ButtonBlock(ModBlockSetTypes.Sets.TENEBRIS, 20, properties.noCollision().pushReaction(PushReaction.DESTROY)
                    .strength(1.0F, 2.0F).sound(SoundType.NETHER_WOOD)));
    public static final Block TENEBRIS_PRESSURE_PLATE = registerBlock("tenebris_pressure_plate", properties ->
            new PressurePlateBlock(ModBlockSetTypes.Sets.TENEBRIS, properties.noCollision().pushReaction(PushReaction.DESTROY)
                    .strength(1.0F, 2.0F).sound(SoundType.NETHER_WOOD)));
    public static final Block TENEBRIS_FENCE = registerBlock("tenebris_fence", properties ->
            new FenceBlock(properties.mapColor(MapColor.COLOR_MAGENTA)
                    .strength(1.5F, 2.5F).sound(SoundType.NETHER_WOOD)));
    public static final Block TENEBRIS_FENCE_GATE = registerBlock("tenebris_fence_gate", properties ->
            new FenceGateBlock(ModBlockSetTypes.WoodTypes.TENEBRIS, properties.mapColor(MapColor.COLOR_MAGENTA)
                    .strength(1.5F, 2.5F).sound(SoundType.NETHER_WOOD)));
    public static final Block TENEBRIS_TRAPDOOR = registerBlock("tenebris_trapdoor", properties ->
            new TrapDoorBlock(ModBlockSetTypes.Sets.TENEBRIS, properties.mapColor(MapColor.COLOR_MAGENTA)
                    .strength(1.5F, 2.5F).sound(SoundType.NETHER_WOOD)
                    .pushReaction(PushReaction.DESTROY).noOcclusion()));
    public static final Block TENEBRIS_DOOR = registerBlock("tenebris_door", properties ->
            new DoorBlock(ModBlockSetTypes.Sets.TENEBRIS, properties.mapColor(MapColor.COLOR_MAGENTA)
                    .strength(1.5F, 2.5F).sound(SoundType.NETHER_WOOD)
                    .pushReaction(PushReaction.DESTROY).noOcclusion()));

    public static final Block TENEBRIS_SHELF = registerBlockEntity("tenebris_shelf", BlockEntityTypes.SHELF,
            properties -> new ShelfBlock(properties.mapColor(TENEBRIS_PLANKS.defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS).sound(SoundType.SHELF)
                    .strength(2.0F, 3.0F)));
    public static final Block TENEBRIS_SIGN = registerBlockEntity("tenebris_sign", BlockEntityTypes.SIGN, false,
            properties -> new StandingSignBlock(ModBlockSetTypes.WoodTypes.TENEBRIS, properties.mapColor(TENEBRIS_PLANKS.defaultMapColor())
                    .noCollision().strength(1.0F)));
    public static final Block WALL_TENEBRIS_SIGN = registerBlockEntity("tenebris_wall_sign", BlockEntityTypes.SIGN, false,
            properties -> new WallSignBlock(ModBlockSetTypes.WoodTypes.TENEBRIS, wallVariant(TENEBRIS_SIGN, true,
                    properties.mapColor(TENEBRIS_PLANKS.defaultMapColor())
                            .noCollision().strength(1.0F))));
    public static final Block HANGING_TENEBRIS_SIGN = registerBlockEntity("tenebris_hanging_sign", BlockEntityTypes.HANGING_SIGN, false,
            properties -> new CeilingHangingSignBlock(ModBlockSetTypes.WoodTypes.TENEBRIS, properties.mapColor(TENEBRIS_PLANKS.defaultMapColor())
                    .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision()
                    .strength(1.0F)));
    public static final Block HANGING_WALL_TENEBRIS_SIGN = registerBlockEntity("tenebris_wall_hanging_sign", BlockEntityTypes.HANGING_SIGN, false,
            properties -> new WallHangingSignBlock(ModBlockSetTypes.WoodTypes.TENEBRIS, wallVariant(HANGING_TENEBRIS_SIGN, true,
                    properties.mapColor(TENEBRIS_PLANKS.defaultMapColor())
                            .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision()
                            .strength(1.0F))));

    public static final Block TENEBRIS_LEAVES = registerBlock("tenebris_leaves", properties ->
            new PlantLikeLeavesBlock(properties.noCollision().instabreak().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)));
    public static final Block TENEBRIS_SAPLING = registerBlock("tenebris_bud", properties ->
            new SaplingBlock(ModTreeGrowers.TENEBRIS, properties.mapColor(MapColor.COLOR_PURPLE).noCollision().lightLevel(state -> 7)
                    .instabreak().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)));
    public static final Block POTTED_TENEBRIS_SAPLING = registerBlock("potted_tenebris_bud", false, properties ->
            new FlowerPotBlock(ModBlocks.TENEBRIS_SAPLING, properties.instabreak().noOcclusion().lightLevel(state -> 6).pushReaction(PushReaction.DESTROY)));

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

    public static final Block PRICKLY_PEAR_SEED = registerBlock("prickly_pear_seed", false, properties ->
            new BarrelCactusSeedBlock(properties.noCollision().randomTicks().noOcclusion()
                    .strength(0.03f).sound(SoundType.CACTUS_FLOWER).pushReaction(PushReaction.DESTROY)));
    public static final Block SMALL_BARREL_CACTUS = registerBlock("small_barrel_cactus", false, properties ->
            new SmallBarrelCactusBlock(properties.mapColor(MapColor.COLOR_GREEN).noCollision().randomTicks().noOcclusion()
                    .strength(0.05f).sound(SoundType.CACTUS_FLOWER).pushReaction(PushReaction.DESTROY)));
    public static final Block LARGE_BARREL_CACTUS = registerBlock("large_barrel_cactus", false, properties ->
            new LargeBarrelCactusBlock(properties.mapColor(MapColor.COLOR_GREEN).noCollision().randomTicks().noOcclusion()
                    .strength(0.07f).sound(SoundType.CACTUS_FLOWER).pushReaction(PushReaction.DESTROY)));
    public static final Block POTTED_BARREL_CACTUS = registerBlock("potted_barrel_cactus", false, properties ->
            new FlowerPotBlock(ModBlocks.SMALL_BARREL_CACTUS, properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

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

    private static Block registerBlockEntity(String name, BlockEntityType<?> type, Function<BlockBehaviour.Properties, Block> function) {
        return registerBlockEntity(name, type, true, function);
    }

    private static Block registerBlockEntity(String name, BlockEntityType<?> type, boolean addItem, Function<BlockBehaviour.Properties, Block> function) {
        Block block = registerBlock(name, addItem, function);
        type.addValidBlock(block);
        return block;
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

    private static BlockBehaviour.Properties wallVariant(final Block standingBlock, final boolean copyName, final BlockBehaviour.Properties properties) {
        BlockBehaviour.Properties wallProperties = properties.overrideLootTable(standingBlock.getLootTable());
        if (copyName) {
            wallProperties = wallProperties.overrideDescription(standingBlock.getDescriptionId());
        }

        return wallProperties;
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering blocks for " + JirniyBiomes.MOD_ID);
    }
}
