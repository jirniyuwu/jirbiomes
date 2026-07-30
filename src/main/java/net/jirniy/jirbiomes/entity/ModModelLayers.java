package net.jirniy.jirbiomes.entity;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.mixin.client.rendering.ModelLayersAccessor;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;

public class ModModelLayers implements ModelLayersAccessor {
    public static final ModelLayerLocation GINKGO_BOAT = create("boat/ginkgo", BoatModel::createBoatModel);
    public static final ModelLayerLocation GINKGO_CHEST_BOAT = create("chest_boat/ginkgo", BoatModel::createChestBoatModel);
    public static final ModelLayerLocation PALM_BOAT = create("boat/palm", BoatModel::createBoatModel);
    public static final ModelLayerLocation PALM_CHEST_BOAT = create("chest_boat/palm", BoatModel::createChestBoatModel);

    private static ModelLayerLocation create(final String model, ModelLayerRegistry.TexturedLayerDefinitionProvider provider) {
        return create(model, provider, "main");
    }

    private static ModelLayerLocation create(final String model, ModelLayerRegistry.TexturedLayerDefinitionProvider provider, final String layer) {
        ModelLayerLocation result = createLocation(model, layer);
        ModelLayerRegistry.registerModelLayer(result, provider);
        ModelLayersAccessor.getLayers().add(result);
        return result;
    }

    private static ModelLayerLocation createLocation(final String model, final String layer) {
        return new ModelLayerLocation(JirniyBiomes.id(model), layer);
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering model layers for " + JirniyBiomes.MOD_ID);
    }
}
