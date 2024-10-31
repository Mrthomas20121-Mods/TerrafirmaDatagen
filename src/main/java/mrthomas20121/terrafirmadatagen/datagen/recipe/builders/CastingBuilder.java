package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedBloomeryRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedCastingRecipe;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Consumer;

public class CastingBuilder extends RecipeBuilder<CastingBuilder> {

    private Ingredient mold;
    private JsonElement fluidInput;
    private ItemStack itemOutput;

    private int break_chance;

    public static CastingBuilder create() { return new CastingBuilder(); }

    CastingBuilder() {}

    @Override
    CastingBuilder getThis() {
        return this;
    }

    public CastingBuilder mold(Ingredient input) {
        this.mold = input;
        return this;
    }

    /**
     * A break chance of 1 indicates this recipe breaks the mold every time.
     * @param break_chance A number between [0, 1]. This is the probability that the mold will break upon completion of this recipe, where a higher number indicates a higher probability.
     * @return
     */
    public CastingBuilder breakChance(int break_chance) {
        this.break_chance = break_chance;
        return this;
    }

    public CastingBuilder mold(ItemStack input) {
        return this.mold(Ingredient.of(input));
    }

    public CastingBuilder mold(TagKey<Item> input) {
        return this.mold(Ingredient.of(input));
    }

    public CastingBuilder input(Fluid input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    /**
     * add a fluid input.
     * this method set the default amount to 1.
     * @param input fluid
     * @return this
     */
    public CastingBuilder input(Fluid input) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, 1);
        return this;
    }

    /**
     * add a fluid input.
     * this method set the default amount to 1.
     * @param input tag fluid
     * @return this
     */
    public CastingBuilder input(TagKey<Fluid> input) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, 1);
        return this;
    }

    public CastingBuilder input(TagKey<Fluid> input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public CastingBuilder output(ItemStack output) {
        this.itemOutput = output;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedCastingRecipe(this.id, this.fluidInput, this.mold, this.itemOutput, break_chance));
    }
}
