package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedIHeatingRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedInstantBarrelRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class HeatingBuilder extends RecipeBuilder<HeatingBuilder> {

    private JsonElement itemInput;
    private FluidStack fluidOutput = FluidStack.EMPTY;
    private ItemStackProvider itemOutput;

    private int temperature;

    public static HeatingBuilder create() { return new HeatingBuilder(); }

    HeatingBuilder() {}

    @Override
    HeatingBuilder getThis() {
        return this;
    }

    public HeatingBuilder temperature(int temp) {
        this.temperature = temp;
        return this;
    }

    public HeatingBuilder itemInput(Ingredient input, int amount) {
        this.itemInput = TFCRecipeHelpers.parseItemStackIngredient(input, amount);
        return this;
    }

    public HeatingBuilder itemInput(ItemStack input, int amount) {
        return this.itemInput(Ingredient.of(input), amount);
    }

    public HeatingBuilder itemInput(TagKey<Item> input, int amount) {
        return this.itemInput(Ingredient.of(input), amount);
    }

    public HeatingBuilder fluidOutput(FluidStack output) {
        this.fluidOutput = output;
        return this;
    }

    public HeatingBuilder itemOutput(ItemStackProvider output) {
        this.itemOutput = output;
        return this;
    }

    public HeatingBuilder itemOutput(ItemStack output) {
        this.itemOutput = ItemStackProvider.of(output);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedIHeatingRecipe(this.id, this.itemInput, this.itemOutput, this.fluidOutput, this.temperature));
    }
}
