package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedBlastFurnaceRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedBloomeryRecipe;
import net.dries007.tfc.common.TFCTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class BloomeryBuilder extends RecipeBuilder<BloomeryBuilder> {

    private Ingredient catalyst;
    private JsonElement fluidInput;
    private ItemStack itemOutput;

    private int duration;

    public static BloomeryBuilder create() { return new BloomeryBuilder(); }

    BloomeryBuilder() {}

    @Override
    BloomeryBuilder getThis() {
        return this;
    }

    public BloomeryBuilder catalyst(Ingredient input) {
        this.catalyst = input;
        return this;
    }

    public BloomeryBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Set the input to charcoal, the default input.
     * @return this
     */
    public BloomeryBuilder defaultCharcoalCatalyst() {
        return this.catalyst(Ingredient.of(Items.CHARCOAL));
    }

    public BloomeryBuilder catalyst(ItemStack input) {
        return this.catalyst(Ingredient.of(input));
    }

    public BloomeryBuilder catalyst(TagKey<Item> input) {
        return this.catalyst(Ingredient.of(input));
    }

    public BloomeryBuilder input(Fluid input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    /**
     * add a fluid input.
     * this method set the default amount to 1.
     * @param input fluid
     * @return this
     */
    public BloomeryBuilder input(Fluid input) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, 1);
        return this;
    }

    /**
     * add a fluid input.
     * this method set the default amount to 1.
     * @param input tag fluid
     * @return this
     */
    public BloomeryBuilder input(TagKey<Fluid> input) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, 1);
        return this;
    }

    public BloomeryBuilder input(TagKey<Fluid> input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public BloomeryBuilder output(ItemStack output) {
        this.itemOutput = output;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedBloomeryRecipe(this.id, this.fluidInput, this.catalyst, this.itemOutput, duration));
    }
}
