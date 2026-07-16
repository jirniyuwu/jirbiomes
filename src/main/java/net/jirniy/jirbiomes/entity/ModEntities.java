package net.jirniy.jirbiomes.entity;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class ModEntities {
    public static final EntityType<Boat> GINKGO_BOAT = create(
            "ginkgo_boat",
            EntityType.Builder.of(boatFactory(() -> ModItems.GINKGO_BOAT), MobCategory.MISC)
                    .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
    );
    public static final EntityType<ChestBoat> GINKGO_CHEST_BOAT = create(
            "ginkgo_chest_boat",
            EntityType.Builder.of(chestBoatFactory(() -> ModItems.GINKGO_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
    );

    private static EntityType.EntityFactory<Boat> boatFactory(final Supplier<Item> boatItem) {
        return (entityType, level) -> new Boat(entityType, level, boatItem);
    }

    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(final Supplier<Item> dropItem) {
        return (entityType, level) -> new ChestBoat(entityType, level, dropItem);
    }

    private static <T extends Entity> EntityType<T> create(final String name, final EntityType.Builder<T> builder) {
        var id = ResourceKey.create(Registries.ENTITY_TYPE, JirniyBiomes.id(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, builder.build(id));
    }

    public static ResourceKey<EntityType<?>> getKey(EntityType entity) {
        return BuiltInRegistries.ENTITY_TYPE.getResourceKey(entity).get();
    }
    public static ResourceKey<EntityType<?>>[] getKeys(EntityType... entities) {
        ResourceKey<EntityType<?>>[] keys = new ResourceKey[entities.length];
        for (int i = 0; i < entities.length; i++) {
            keys[i] = getKey(entities[i]);
        }
        return keys;
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering entities for " + JirniyBiomes.MOD_ID);
    }
}
