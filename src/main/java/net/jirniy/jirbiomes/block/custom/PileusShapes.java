package net.jirniy.jirbiomes.block.custom;

import net.minecraft.util.StringRepresentable;

public enum PileusShapes implements StringRepresentable {
    FULL("full"),
    HANGING("hanging"),
    GROUNDED("grounded"),
    SUPPORTING("supporting");

    private final String name;

    PileusShapes(final String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

}
