package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public abstract class RecipeBuilder<T> {

    protected ResourceLocation id;

    protected RecipeBuilder() {}

    abstract void build(Consumer<FinishedRecipe> consumer);

    abstract T getThis();

    public T id(String name) {
        this.id = new ResourceLocation(name);
        return getThis();
    }

    public T id(ResourceLocation name) {
        this.id = name;
        return getThis();
    }

    public T id(String key, String value) {
        this.id = new ResourceLocation(key, value);
        return getThis();
    }
}
