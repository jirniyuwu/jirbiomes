package net.jirniy.jirbiomes.block;

import net.fabricmc.fabric.api.registry.fluid.EntityFluidInteractionRegistry;
import net.fabricmc.fabric.api.registry.fluid.FluidBehavior;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.fluid.TarrFluid;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public class ModFluids {
    public static final FlowingFluid FLOWING_TARR = registerFluid("flowing_tarr", new TarrFluid.Flowing());
    public static final FlowingFluid TARR = registerFluid("tarr", new TarrFluid.Source());

    private static <T extends Fluid> T registerFluid(String name, final T fluid) {
        ResourceKey<Fluid> id = ResourceKey.create(Registries.FLUID, JirniyBiomes.id(name));
        T toRegister = Registry.register(BuiltInRegistries.FLUID, id, fluid);
        for (FluidState state : fluid.getStateDefinition().getPossibleStates()) {
            Fluid.FLUID_STATE_REGISTRY.add(state);
        }

        return toRegister;
    }

    public static ResourceKey<Fluid> getKey(Fluid fluid) {
        return BuiltInRegistries.FLUID.getResourceKey(fluid).get();
    }
    public static ResourceKey<Fluid>[] getKeys(Fluid... fluids) {
        ResourceKey<Fluid>[] keys = new ResourceKey[fluids.length];
        for (int i = 0; i < fluids.length; i++) {
            keys[i] = getKey(fluids[i]);
        }
        return keys;
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering fluids for " + JirniyBiomes.MOD_ID);

        EntityFluidInteractionRegistry.register(ModTags.Fluids.TARR, FluidBehavior.simple()
                .allowSwimming(true).allowBoats(false).enableDrowning(true)
                .movementSlowdown(0.7f, 0.85f).allowSprinting(false)
                .makeMobsFloat(true).makeRiddenMobsFloat(true)
                .build());
    }
}
