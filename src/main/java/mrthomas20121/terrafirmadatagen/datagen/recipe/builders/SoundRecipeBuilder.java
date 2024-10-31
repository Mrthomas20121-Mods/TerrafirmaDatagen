package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Consumer;

public abstract class SoundRecipeBuilder<T> extends RecipeBuilder<T> {

    protected SoundEvent sound = null;

    protected SoundRecipeBuilder() {}

    public T sound(SoundEvent sound) {
        this.sound = sound;
        return getThis();
    }
}
