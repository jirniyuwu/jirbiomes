package net.jirniy.jirbiomes.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jirniy.jirbiomes.block.ModFluids;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class ModDripParticle extends SingleQuadParticle {
    private final Fluid type;
    protected boolean isGlowing;

    private ModDripParticle(final ClientLevel level, final double x, final double y, final double z, final Fluid type, final TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = type;
    }

    protected Fluid getType() {
        return this.type;
    }

    public SingleQuadParticle.Layer getLayer() {
        return Layer.OPAQUE;
    }

    public int getLightCoords(final float a) {
        return this.isGlowing ? LightCoordsUtil.withBlock(super.getLightCoords(a), 15) : super.getLightCoords(a);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= (double)0.98F;
                this.yd *= (double)0.98F;
                this.zd *= (double)0.98F;
                if (this.type != Fluids.EMPTY) {
                    BlockPos pos = BlockPos.containing(this.x, this.y, this.z);
                    FluidState fluidState = this.level.getFluidState(pos);
                    if (fluidState.is(this.type) && this.y < (double)((float)pos.getY() + fluidState.getHeight(this.level, pos))) {
                        this.remove();
                    }

                }
            }
        }
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }

    }

    protected void postMoveUpdate() {
    }

    @Environment(EnvType.CLIENT)
    private static class DripLandParticle extends ModDripParticle {
        private DripLandParticle(final ClientLevel level, final double x, final double y, final double z, final Fluid type, final TextureAtlasSprite sprite) {
            super(level, x, y, z, type, sprite);
            this.lifetime = (int)(16.0 / (this.random.nextFloat() * 0.8 + 0.2));
        }
    }

    @Environment(EnvType.CLIENT)
    private static class DripHangParticle extends ModDripParticle {
        private final ParticleOptions fallingParticle;

        private DripHangParticle(
                final ClientLevel level,
                final double x,
                final double y,
                final double z,
                final Fluid type,
                final ParticleOptions fallingParticle,
                final TextureAtlasSprite sprite
        ) {
            super(level, x, y, z, type, sprite);
            this.fallingParticle = fallingParticle;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        @Override
        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

        @Override
        protected void postMoveUpdate() {
            this.xd *= 0.02;
            this.yd *= 0.02;
            this.zd *= 0.02;
        }
    }

    @Environment(EnvType.CLIENT)
    private static class CoolingDripHangParticle extends DripHangParticle {
        private CoolingDripHangParticle(
                final ClientLevel level,
                final double x,
                final double y,
                final double z,
                final Fluid type,
                final ParticleOptions fallingParticle,
                final TextureAtlasSprite sprite
        ) {
            super(level, x, y, z, type, fallingParticle, sprite);
        }

        @Override
        protected void preMoveUpdate() {
            this.rCol = 1.0F;
            this.gCol = 16.0F / (40 - this.lifetime + 16);
            this.bCol = 4.0F / (40 - this.lifetime + 8);
            super.preMoveUpdate();
        }
    }

    @Environment(EnvType.CLIENT)
    private static class FallAndLandParticle extends FallingParticle {
        protected final ParticleOptions landParticle;

        private FallAndLandParticle(
                final ClientLevel level,
                final double x,
                final double y,
                final double z,
                final Fluid type,
                final ParticleOptions landParticle,
                final TextureAtlasSprite sprite
        ) {
            super(level, x, y, z, type, sprite);
            this.lifetime = (int)(64.0 / (this.random.nextFloat() * 0.8 + 0.2));
            this.landParticle = landParticle;
        }

        @Override
        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class BrimgrassAmbientProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public BrimgrassAmbientProvider(final SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(final SimpleParticleType options, final ClientLevel level, final double x, final double y, final double z, final double xAux, final double yAux, final double zAux, final RandomSource random) {
            ModDripParticle particle = new ModDripParticle.FallingParticle(level, x, y, z, Fluids.EMPTY, this.sprite.get(random));
            particle.lifetime = (int)(64.0F / Mth.randomBetween(particle.random, 0.1F, 0.9F));
            particle.gravity = 0.0025F;
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    private static class FallingParticle extends ModDripParticle {
        private FallingParticle(final ClientLevel level, final double x, final double y, final double z, final Fluid type, final TextureAtlasSprite sprite) {
            super(level, x, y, z, type, sprite);
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
            }

        }
    }

    @Environment(EnvType.CLIENT)
    public static class TarrFallProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public TarrFallProvider(final SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(
                final SimpleParticleType options,
                final ClientLevel level,
                final double x,
                final double y,
                final double z,
                final double xAux,
                final double yAux,
                final double zAux,
                final RandomSource random
        ) {
            ModDripParticle particle = new FallAndLandParticle(level, x, y, z, ModFluids.TARR, ModParticles.LANDING_TARR, this.sprite.get(random));
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class TarrHangProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public TarrHangProvider(final SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(
                final SimpleParticleType options,
                final ClientLevel level,
                final double x,
                final double y,
                final double z,
                final double xAux,
                final double yAux,
                final double zAux,
                final RandomSource random
        ) {
            return new CoolingDripHangParticle(level, x, y, z, ModFluids.TARR, ModParticles.FALLING_TARR, this.sprite.get(random));
        }
    }

    @Environment(EnvType.CLIENT)
    public static class TarrLandProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public TarrLandProvider(final SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(
                final SimpleParticleType options,
                final ClientLevel level,
                final double x,
                final double y,
                final double z,
                final double xAux,
                final double yAux,
                final double zAux,
                final RandomSource random
        ) {
            ModDripParticle particle = new DripLandParticle(level, x, y, z, ModFluids.TARR, this.sprite.get(random));
            return particle;
        }
    }
}
