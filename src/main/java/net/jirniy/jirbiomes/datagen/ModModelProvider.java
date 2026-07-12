package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.IRON_GRATE);
        blockModelGenerators.createTrivialCube(ModBlocks.COARSE_DRIED_DIRT);
        blockModelGenerators.createTrivialCube(ModBlocks.ROOTED_DRIED_DIRT);
        blockModelGenerators.createTrivialCube(ModBlocks.DRIED_DIRT);
        blockModelGenerators.createTrivialCube(ModBlocks.COARSE_WETLAND);
        blockModelGenerators.createTrivialCube(ModBlocks.ROOTED_WETLAND);
        blockModelGenerators.createTrivialCube(ModBlocks.WETLAND);

        blockModelGenerators.family(ModBlocks.GINKGO_PLANKS)
                .stairs(ModBlocks.GINKGO_STAIRS)
                .slab(ModBlocks.GINKGO_SLAB)
                .button(ModBlocks.GINKGO_BUTTON)
                .fence(ModBlocks.GINKGO_FENCE)
                .fenceGate(ModBlocks.GINKGO_FENCE_GATE)
                .pressurePlate(ModBlocks.GINKGO_PRESSURE_PLATE);
        blockModelGenerators.createDoor(ModBlocks.GINKGO_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.GINKGO_TRAPDOOR);
        blockModelGenerators.woodProvider(ModBlocks.GINKGO_LOG).log(ModBlocks.GINKGO_LOG).wood(ModBlocks.GINKGO_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_GINKGO_LOG).log(ModBlocks.STRIPPED_GINKGO_LOG).wood(ModBlocks.STRIPPED_GINKGO_WOOD);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.APPLE_SEEDS, ModelTemplates.FLAT_ITEM);
    }
}
