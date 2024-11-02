package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;

/**
 * Common pot builder for all pot recipes since they all share the same data
 * @param <T>
 */
public abstract class PotBuilder<T extends PotBuilder<T>> extends RecipeBuilder<T> {

    public static JamPotBuilder jam() {
        return new JamPotBuilder();
    }

    public static SoupPotBuilder soup() {
        return new SoupPotBuilder();
    }

    public static SimplePotBuilder simple() {
        return new SimplePotBuilder();
    }

    protected Ingredient[] ingredients = {};
    protected JsonElement fluidInput = JsonNull.INSTANCE;
    protected int duration;
    protected float temperature;

    public T duration(int duration) {
        this.duration = duration;
        return getThis();
    }

    public T temperature(float temp) {
        this.temperature = temp;
        return getThis();
    }

    public T input(Ingredient... ingredients) {
        this.ingredients = ingredients;
        return getThis();
    }

    public T input(Fluid input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return getThis();
    }

    public T input(TagKey<Fluid> input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return getThis();
    }
}
