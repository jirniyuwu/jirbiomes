package net.jirniy.jirbiomes.villager;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {
    public static final ResourceKey<VillagerTrade> FARMER_2_EMERALD_APPLE_SEEDS = createKey("farmer/2/emerald_apple_seeds");
    public static final ResourceKey<VillagerTrade> FARMER_3_COCONUT_EMERALD = createKey("farmer/3/coconut_emerald");
    public static final ResourceKey<VillagerTrade> FARMER_3_EMERALD_PRICKLY_PEAR = createKey("farmer/3/emerald_prickly_pear");

    public static final ResourceKey<VillagerTrade> MASON_2_EMERALD_SALT = createKey("mason/2/emerald_salt");

    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_DRIED_FIBERS_EMERALD = createKey("wandering_trader/dried_fibers_emerald");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_SALT_LAMP_EMERALD = createKey("wandering_trader/salt_lamp_emerald");

    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_ALGAE_BUCKET = createKey("wandering_trader/emerald_algae_bucket");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_TARR_BUCKET = createKey("wandering_trader/emerald_tarr_bucket");

    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_APPLE_SEEDS = createKey("wandering_trader/emerald_apple_seeds");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_BARREL_CACTUS = createKey("wandering_trader/emerald_barrel_cactus");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_HANGING_MUSHROOMS = createKey("wandering_trader/emerald_hanging_mushrooms");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_SMALL_MUSHROOMS = createKey("wandering_trader/emerald_small_mushrooms");;
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_SEASHELLS = createKey("wandering_trader/emerald_seashells");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_LOTUS = createKey("wandering_trader/emerald_lotus");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_SEA_URCHIN = createKey("wandering_trader/emerald_sea_urchin");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_POTENT_MAGMA = createKey("wandering_trader/emerald_potent_magma");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_PERMAFROST = createKey("wandering_trader/emerald_permafrost");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_THICK_ICICLE = createKey("wandering_trader/emerald_thick_icicle");

    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_GINKGO_LOG = createKey("wandering_trader/emerald_ginkgo_log");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_PALM_LOG = createKey("wandering_trader/emerald_palm_log");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_PROTOTAXITE_LOG = createKey("wandering_trader/emerald_prototaxite_log");

    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_GINKGO_SAPLING = createKey("wandering_trader/emerald_ginkgo_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_PALM_SAPLING = createKey("wandering_trader/emerald_palm_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_PROTOTAXITE_SAPLING = createKey("wandering_trader/emerald_prototaxite_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_TENEBRIS_SAPLING = createKey("wandering_trader/emerald_tenebris_sapling");

    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        context.register(FARMER_2_EMERALD_APPLE_SEEDS, new VillagerTrade(
                new TradeCost(Items.EMERALD, 2),
                new ItemStackTemplate(ModItems.APPLE_SEEDS, 1),
                8, 4, 0.05F, Optional.empty(), List.of()
        ));
        context.register(FARMER_3_COCONUT_EMERALD, new VillagerTrade(
                new TradeCost(ModItems.COCONUT, 1),
                new ItemStackTemplate(Items.EMERALD, 2),
                12, 8, 0.05F, Optional.empty(), List.of()
        ));
        context.register(FARMER_3_EMERALD_PRICKLY_PEAR, new VillagerTrade(
                new TradeCost(Items.EMERALD, 3),
                new ItemStackTemplate(ModItems.PRICKLY_PEAR, 1),
                8, 8, 0.05F, Optional.empty(), List.of()
        ));

        context.register(MASON_2_EMERALD_SALT, new VillagerTrade(
                new TradeCost(Items.EMERALD, 1),
                new ItemStackTemplate(ModBlocks.SALT_BLOCK.asItem(), 16),
                16, 8, 0.05F, Optional.empty(), List.of()
        ));

        context.register(WANDERING_TRADER_DRIED_FIBERS_EMERALD, new VillagerTrade(
                new TradeCost(ModItems.DRIED_FIBERS, 16),
                new ItemStackTemplate(Items.EMERALD, 1),
                8, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_SALT_LAMP_EMERALD, new VillagerTrade(
                new TradeCost(ModBlocks.SALT_LAMP, 1),
                new ItemStackTemplate(Items.EMERALD, 1),
                4, 1, 0.05F, Optional.empty(), List.of()
        ));

        context.register(WANDERING_TRADER_EMERALD_SMALL_MUSHROOMS, new VillagerTrade(
                new TradeCost(Items.EMERALD, 2),
                new ItemStackTemplate(ModBlocks.SMALL_MUSHROOMS.asItem(), 8),
                4, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_HANGING_MUSHROOMS, new VillagerTrade(
                new TradeCost(Items.EMERALD, 2),
                new ItemStackTemplate(ModBlocks.HANGING_MUSHROOMS.asItem(), 1),
                6, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_BARREL_CACTUS, new VillagerTrade(
                new TradeCost(Items.EMERALD, 3),
                new ItemStackTemplate(ModItems.BARREL_CACTUS, 1),
                6, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_SEASHELLS, new VillagerTrade(
                new TradeCost(Items.EMERALD, 1),
                new ItemStackTemplate(ModItems.SEASHELL, 16),
                6, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_LOTUS, new VillagerTrade(
                new TradeCost(Items.EMERALD, 3),
                new ItemStackTemplate(ModItems.LOTUS_FLOWER, 1),
                6, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_SEA_URCHIN, new VillagerTrade(
                new TradeCost(Items.EMERALD, 4),
                new ItemStackTemplate(ModBlocks.SEA_URCHIN.asItem(), 1),
                4, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_POTENT_MAGMA, new VillagerTrade(
                new TradeCost(Items.EMERALD, 4),
                new ItemStackTemplate(ModBlocks.POTENT_MAGMA_BLOCK.asItem(), 1),
                8, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_PERMAFROST, new VillagerTrade(
                new TradeCost(Items.EMERALD, 4),
                new ItemStackTemplate(ModBlocks.PERMAFROST_BLOCK.asItem(), 1),
                8, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_THICK_ICICLE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 2),
                new ItemStackTemplate(ModBlocks.THICK_ICICLE.asItem(), 1),
                6, 1, 0.05F, Optional.empty(), List.of()
        ));

        context.register(WANDERING_TRADER_EMERALD_ALGAE_BUCKET, new VillagerTrade(
                new TradeCost(Items.EMERALD, 4),
                new ItemStackTemplate(ModItems.ALGAE_BUCKET, 1),
                6, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_TARR_BUCKET, new VillagerTrade(
                new TradeCost(Items.EMERALD, 24),
                new ItemStackTemplate(ModItems.TARR_BUCKET, 1),
                4, 1, 0.05F, Optional.empty(), List.of()
        ));
        context.register(WANDERING_TRADER_EMERALD_GINKGO_LOG, createWanderingTraderLogSell(ModBlocks.GINKGO_LOG.asItem()));
        context.register(WANDERING_TRADER_EMERALD_PALM_LOG, createWanderingTraderLogSell(ModBlocks.PALM_LOG.asItem()));
        context.register(WANDERING_TRADER_EMERALD_PROTOTAXITE_LOG, createWanderingTraderLogSell(ModBlocks.PROTOTAXITE_LOG.asItem()));
        context.register(WANDERING_TRADER_EMERALD_GINKGO_SAPLING, createWanderingTraderSaplingSell(ModBlocks.GINKGO_SAPLING.asItem()));
        context.register(WANDERING_TRADER_EMERALD_PALM_SAPLING, createWanderingTraderSaplingSell(ModBlocks.PALM_SAPLING.asItem()));
        context.register(WANDERING_TRADER_EMERALD_PROTOTAXITE_SAPLING, createWanderingTraderSaplingSell(ModBlocks.PROTOTAXITE_SAPLING.asItem()));
        context.register(WANDERING_TRADER_EMERALD_TENEBRIS_SAPLING, createWanderingTraderSaplingSell(ModBlocks.TENEBRIS_SAPLING.asItem()));
        context.register(WANDERING_TRADER_EMERALD_APPLE_SEEDS, createWanderingTraderSeedSell(ModItems.APPLE_SEEDS));
    }

    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, JirniyBiomes.id(name));
    }

    private static VillagerTrade createWanderingTraderLogSell(final Item item) {
        return new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(item, 8), 4, 1, 0.05F, Optional.empty(), List.of());
    }
    private static VillagerTrade createWanderingTraderSaplingSell(final Item item) {
        return new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(item), 8, 1, 0.05F, Optional.empty(), List.of());
    }
    private static VillagerTrade createWanderingTraderSeedSell(final Item item) {
        return new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(item), 12, 1, 0.05F, Optional.empty(), List.of());
    }
}
