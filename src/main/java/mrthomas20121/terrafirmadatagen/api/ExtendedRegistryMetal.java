package mrthomas20121.terrafirmadatagen.api;

import mrthomas20121.terrafirmadatagen.api.tags.MetalTag;
import net.dries007.tfc.util.registry.RegistryMetal;
import net.minecraft.world.level.material.Fluid;

/**
 * ExtendedRegistryMetal.getHeatCapacityBase(); is the base heat for a metal,
 * 0.35f is the default and is the value used by tfc bronze, copper and iron.
 */
public interface ExtendedRegistryMetal extends RegistryMetal {

    MetalTag getTags();

    /**
     * required by some methods
     * @return the modid
     */
    String getModID();

    /**
     * most alloys/bronze/iron/copper is 0.35.
     * bismuth/tin is 0.14, zinc is 0.21.
     * it's used in the calculation for adding heating cap to items/blocks.
     * @return the heat capacity base
     */
    float getHeatCapacityBase();

    /**
     * Get the melting temperature of the metal for adding heating cap and other stuff
     * @return the metal melting temperature
     */
    float getMeltTemperature();

    /**
     * @return metal fluid
     */
    Fluid getFluid();

    /**
     * this method is why I need the heat capacity for a metal.
     * @param bool true = ingot heat capacity, false = other heat capacity
     * @return heat capacity
     */
    default float getHeatCapacity(boolean bool) {
        if(bool) {
            return 1 / this.getHeatCapacityBase();
        }
        else {
            return (float) Math.round(300 / this.getHeatCapacityBase()) / 100000;
        }
    }
}
