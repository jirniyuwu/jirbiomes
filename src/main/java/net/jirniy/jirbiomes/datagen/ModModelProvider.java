package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.block.custom.AppleCropBlock;
import net.jirniy.jirbiomes.block.custom.SmallBarrelCactusBlock;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.IRON_GRATE);
        blockModelGenerators.createTrivialCube(ModBlocks.NETHERSTONE);

        blockModelGenerators.createTrivialCube(ModBlocks.COARSE_DRIED_DIRT);
        blockModelGenerators.createTrivialCube(ModBlocks.ROOTED_DRIED_DIRT);
        blockModelGenerators.createTrivialCube(ModBlocks.DRIED_DIRT);
        blockModelGenerators.createTrivialCube(ModBlocks.COARSE_WETLAND);
        blockModelGenerators.createTrivialCube(ModBlocks.ROOTED_WETLAND);
        blockModelGenerators.createTrivialCube(ModBlocks.WETLAND);

        blockModelGenerators.createTrivialCube(ModBlocks.SALT_BLOCK);
        blockModelGenerators.family(ModBlocks.SALT_BRICKS)
                .wall(ModBlocks.SALT_BRICKS_WALL)
                .stairs(ModBlocks.SALT_BRICKS_STAIRS)
                .slab(ModBlocks.SALT_BRICKS_SLAB);

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.BRIMSTONE,
                BlockModelGenerators.createRotatedVariants(BlockModelGenerators.plainModel(
                        TexturedModel.COLUMN_WITH_WALL.create(ModBlocks.BRIMSTONE, blockModelGenerators.modelOutput)))));
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.IGNITED_BRIMSTONE,
                BlockModelGenerators.createRotatedVariants(BlockModelGenerators.plainModel(
                        TexturedModel.COLUMN_WITH_WALL.create(ModBlocks.IGNITED_BRIMSTONE, blockModelGenerators.modelOutput)))));
        blockModelGenerators.createTrivialBlock(ModBlocks.CHISELED_BRIMSTONE_BRICKS, TexturedModel.COLUMN_WITH_WALL);
        blockModelGenerators.createTrivialCube(ModBlocks.BRIMSTONE_GOLD_ORE);
        blockModelGenerators.createCrossBlockWithDefaultItem(ModBlocks.BRIMGRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.family(ModBlocks.MOSSY_BRIMSTONE_BRICKS)
                .wall(ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL)
                .stairs(ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS)
                .slab(ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB);
        blockModelGenerators.family(ModBlocks.CRACKED_BRIMSTONE_BRICKS)
                .wall(ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL)
                .stairs(ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS)
                .slab(ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB);
        blockModelGenerators.family(ModBlocks.POLISHED_BRIMSTONE)
                .wall(ModBlocks.POLISHED_BRIMSTONE_WALL)
                .stairs(ModBlocks.POLISHED_BRIMSTONE_STAIRS)
                .slab(ModBlocks.POLISHED_BRIMSTONE_SLAB);
        blockModelGenerators.family(ModBlocks.BRIMSTONE_BRICKS)
                .wall(ModBlocks.BRIMSTONE_BRICKS_WALL)
                .stairs(ModBlocks.BRIMSTONE_BRICKS_STAIRS)
                .slab(ModBlocks.BRIMSTONE_BRICKS_SLAB);

        blockModelGenerators.family(ModBlocks.GINKGO_PLANKS)
                .stairs(ModBlocks.GINKGO_STAIRS)
                .slab(ModBlocks.GINKGO_SLAB)
                .button(ModBlocks.GINKGO_BUTTON)
                .fence(ModBlocks.GINKGO_FENCE)
                .fenceGate(ModBlocks.GINKGO_FENCE_GATE)
                .pressurePlate(ModBlocks.GINKGO_PRESSURE_PLATE);
        createSign(ModBlocks.GINKGO_SIGN, ModBlocks.WALL_GINKGO_SIGN, ModBlocks.GINKGO_PLANKS, blockModelGenerators);
        createHangingSign(ModBlocks.HANGING_GINKGO_SIGN, ModBlocks.HANGING_WALL_GINKGO_SIGN, ModBlocks.STRIPPED_GINKGO_LOG, BlockFamily.Variant.WALL_HANGING_SIGN, blockModelGenerators);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.GINKGO_SAPLING, ModBlocks.POTTED_GINKGO_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createTrivialCube(ModBlocks.GINKGO_LEAVES);
        blockModelGenerators.createDoor(ModBlocks.GINKGO_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.GINKGO_TRAPDOOR);
        blockModelGenerators.woodProvider(ModBlocks.GINKGO_LOG).log(ModBlocks.GINKGO_LOG).wood(ModBlocks.GINKGO_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_GINKGO_LOG).log(ModBlocks.STRIPPED_GINKGO_LOG).wood(ModBlocks.STRIPPED_GINKGO_WOOD);
        blockModelGenerators.createShelf(ModBlocks.GINKGO_SHELF, ModBlocks.STRIPPED_GINKGO_LOG);

        blockModelGenerators.family(ModBlocks.PALM_PLANKS)
                .stairs(ModBlocks.PALM_STAIRS)
                .slab(ModBlocks.PALM_SLAB)
                .button(ModBlocks.PALM_BUTTON)
                .fence(ModBlocks.PALM_FENCE)
                .fenceGate(ModBlocks.PALM_FENCE_GATE)
                .pressurePlate(ModBlocks.PALM_PRESSURE_PLATE);
        createSign(ModBlocks.PALM_SIGN, ModBlocks.WALL_PALM_SIGN, ModBlocks.PALM_PLANKS, blockModelGenerators);
        createHangingSign(ModBlocks.HANGING_PALM_SIGN, ModBlocks.HANGING_WALL_PALM_SIGN, ModBlocks.STRIPPED_PALM_LOG, BlockFamily.Variant.WALL_HANGING_SIGN, blockModelGenerators);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.PALM_SAPLING, ModBlocks.POTTED_PALM_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createTrivialCube(ModBlocks.PALM_LEAVES);
        blockModelGenerators.createDoor(ModBlocks.PALM_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.PALM_TRAPDOOR);
        blockModelGenerators.woodProvider(ModBlocks.PALM_LOG).log(ModBlocks.PALM_LOG).wood(ModBlocks.PALM_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_PALM_LOG).log(ModBlocks.STRIPPED_PALM_LOG).wood(ModBlocks.STRIPPED_PALM_WOOD);
        blockModelGenerators.createShelf(ModBlocks.PALM_SHELF, ModBlocks.STRIPPED_PALM_LOG);

        blockModelGenerators.family(ModBlocks.TENEBRIS_PLANKS)
                .stairs(ModBlocks.TENEBRIS_STAIRS)
                .slab(ModBlocks.TENEBRIS_SLAB)
                .button(ModBlocks.TENEBRIS_BUTTON)
                .fence(ModBlocks.TENEBRIS_FENCE)
                .fenceGate(ModBlocks.TENEBRIS_FENCE_GATE)
                .pressurePlate(ModBlocks.TENEBRIS_PRESSURE_PLATE);
        createSign(ModBlocks.TENEBRIS_SIGN, ModBlocks.WALL_TENEBRIS_SIGN, ModBlocks.TENEBRIS_PLANKS, blockModelGenerators);
        createHangingSign(ModBlocks.HANGING_TENEBRIS_SIGN, ModBlocks.HANGING_WALL_TENEBRIS_SIGN, ModBlocks.STRIPPED_TENEBRIS_LOG, BlockFamily.Variant.WALL_HANGING_SIGN, blockModelGenerators);
        blockModelGenerators.createDoor(ModBlocks.TENEBRIS_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.TENEBRIS_TRAPDOOR);
        blockModelGenerators.woodProvider(ModBlocks.TENEBRIS_LOG).log(ModBlocks.TENEBRIS_LOG).wood(ModBlocks.TENEBRIS_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_TENEBRIS_LOG).log(ModBlocks.STRIPPED_TENEBRIS_LOG).wood(ModBlocks.STRIPPED_TENEBRIS_WOOD);
        blockModelGenerators.createShelf(ModBlocks.TENEBRIS_SHELF, ModBlocks.STRIPPED_TENEBRIS_LOG);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.TENEBRIS_SAPLING, ModBlocks.POTTED_TENEBRIS_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.APPLE_OAK_SAPLING, ModBlocks.POTTED_APPLE_OAK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createCrossBlock(ModBlocks.APPLE_CROP, BlockModelGenerators.PlantType.NOT_TINTED, AppleCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);

        blockModelGenerators.createCrossBlock(ModBlocks.PRICKLY_PEAR_SEED, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createCrossBlock(ModBlocks.SMALL_BARREL_CACTUS, BlockModelGenerators.PlantType.NOT_TINTED, SmallBarrelCactusBlock.AGE, 0, 1, 2);
        blockModelGenerators.createCrossBlock(ModBlocks.LARGE_BARREL_CACTUS, BlockModelGenerators.PlantType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.PRICKLY_PEAR, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.DRIED_FIBERS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COCONUT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CRACKED_COCONUT, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.ALGAE_BUCKET, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModBlocks.TENEBRIS_LEAVES.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModBlocks.SALT_LAMP.asItem(), ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.GINKGO_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GINKGO_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PALM_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PALM_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }

    public void createSign(Block standingSign, Block wallSign, Block baseBlock, BlockModelGenerators generator) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(standingSign))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(baseBlock));
        MultiVariant standingRot0 = BlockModelGenerators.plainVariant(
                ModelTemplates.SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(standingSign, "_rot_0"), mapping, generator.modelOutput)
        );
        MultiVariant standingRot1 = BlockModelGenerators.plainVariant(
                ModelTemplates.SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(standingSign, "_rot_1"), mapping, generator.modelOutput)
        );
        MultiVariant standingRot2 = BlockModelGenerators.plainVariant(
                ModelTemplates.SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(standingSign, "_rot_2"), mapping, generator.modelOutput)
        );
        MultiVariant standingRot3 = BlockModelGenerators.plainVariant(
                ModelTemplates.SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(standingSign, "_rot_3"), mapping, generator.modelOutput)
        );
        generator.blockStateOutput.accept(BlockModelGenerators.createSign(standingSign, standingRot0, standingRot1, standingRot2, standingRot3));
        MultiVariant wallModel = BlockModelGenerators.plainVariant(ModelTemplates.WALL_SIGN.create(wallSign, mapping, generator.modelOutput));
        generator.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(wallSign, wallModel).with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                        .select(Direction.SOUTH, BlockModelGenerators.NOP)
                        .select(Direction.WEST, BlockModelGenerators.Y_ROT_90)
                        .select(Direction.NORTH, BlockModelGenerators.Y_ROT_180)
                        .select(Direction.EAST, BlockModelGenerators.Y_ROT_270)));
        generator.registerSimpleFlatItemModel(standingSign.asItem());
    }
    public void createHangingSign(final Block hangingSign, final Block wallSign, final Block particleBlock, final BlockFamily.Variant wallVarient, BlockModelGenerators generator) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(hangingSign))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(particleBlock));
        generator.blockStateOutput
                .accept(
                        BlockModelGenerators.createHangingSign(
                                hangingSign,
                                BlockModelGenerators.plainVariant(
                                        ModelTemplates.HANGING_SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_0"), mapping, generator.modelOutput)
                                ),
                                BlockModelGenerators.plainVariant(
                                        ModelTemplates.HANGING_SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_1"), mapping, generator.modelOutput)
                                ),
                                BlockModelGenerators.plainVariant(
                                        ModelTemplates.HANGING_SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_2"), mapping, generator.modelOutput)
                                ),
                                BlockModelGenerators.plainVariant(
                                        ModelTemplates.HANGING_SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_3"), mapping, generator.modelOutput)
                                ),
                                BlockModelGenerators.plainVariant(
                                        ModelTemplates.ATTACHED_HANGING_SIGN_ROT_0
                                                .create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_0"), mapping, generator.modelOutput)
                                ),
                                BlockModelGenerators.plainVariant(
                                        ModelTemplates.ATTACHED_HANGING_SIGN_ROT_1
                                                .create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_1"), mapping, generator.modelOutput)
                                ),
                                BlockModelGenerators.plainVariant(
                                        ModelTemplates.ATTACHED_HANGING_SIGN_ROT_2
                                                .create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_2"), mapping, generator.modelOutput)
                                ),
                                BlockModelGenerators.plainVariant(
                                        ModelTemplates.ATTACHED_HANGING_SIGN_ROT_3
                                                .create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_3"), mapping, generator.modelOutput)
                                )
                        )
                );
        MultiVariant wallModel = BlockModelGenerators.plainVariant(ModelTemplates.WALL_HANGING_SIGN.create(wallSign, mapping, generator.modelOutput));
        generator.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(wallSign, wallModel).with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                        .select(Direction.SOUTH, BlockModelGenerators.NOP)
                        .select(Direction.WEST, BlockModelGenerators.Y_ROT_90)
                        .select(Direction.NORTH, BlockModelGenerators.Y_ROT_180)
                        .select(Direction.EAST, BlockModelGenerators.Y_ROT_270)));
        generator.registerSimpleFlatItemModel(hangingSign.asItem());
    }
}
