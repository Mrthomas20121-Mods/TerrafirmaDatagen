package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedBlastFurnaceRecipe;
import net.dries007.tfc.common.TFCTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class BlastFurnaceBuilder extends RecipeBuilder<BlastFurnaceBuilder> {

    private Ingredient catalyst;
    private JsonElement fluidInput;
    private FluidStack fluidOutput;

    public static BlastFurnaceBuilder create() { return new BlastFurnaceBuilder(); }

    BlastFurnaceBuilder() {}

    @Override
    BlastFurnaceBuilder getThis() {
        return this;
    }

    public BlastFurnaceBuilder catalyst(Ingredient input) {
        this.catalyst = input;
        return this;
    }

    /**
     * Set the catalyst to flux, the default catalyst.
     * @return this
     */
    public BlastFurnaceBuilder defaultCatalyst() {
        return this.catalyst(Ingredient.of(TFCTags.Items.FLUX));
    }

    public BlastFurnaceBuilder catalyst(ItemStack input) {
        return this.catalyst(Ingredient.of(input));
    }

    public BlastFurnaceBuilder catalyst(TagKey<Item> input) {
        return this.catalyst(Ingredient.of(input));
    }

    public BlastFurnaceBuilder input(Fluid input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    /**
     * add a fluid input.
     * this method set the default amount to 1.
     * @param input fluid
     * @return this
     */
    public BlastFurnaceBuilder input(Fluid input) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, 1);
        return this;
    }

    /**
     * add a fluid input.
     * this method set the default amount to 1.
     * @param input tag fluid
     * @return this
     */
    public BlastFurnaceBuilder input(TagKey<Fluid> input) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, 1);
        return this;
    }

    public BlastFurnaceBuilder input(TagKey<Fluid> input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public BlastFurnaceBuilder output(FluidStack output) {
        this.fluidOutput = output;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedBlastFurnaceRecipe(this.id, this.fluidInput, this.catalyst, this.fluidOutput));
    }
}
