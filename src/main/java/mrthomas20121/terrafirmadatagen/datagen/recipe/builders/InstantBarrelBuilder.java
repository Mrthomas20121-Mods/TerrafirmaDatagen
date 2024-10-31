package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
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

public class InstantBarrelBuilder extends SoundRecipeBuilder<InstantBarrelBuilder> {

    private JsonElement itemInput = JsonNull.INSTANCE;
    private JsonElement fluidInput = JsonNull.INSTANCE;
    private FluidStack fluidOutput = FluidStack.EMPTY;
    private ItemStackProvider itemOutput;

    public static InstantBarrelBuilder create() { return new InstantBarrelBuilder(); }

    InstantBarrelBuilder() {}

    @Override
    InstantBarrelBuilder getThis() {
        return this;
    }

    public InstantBarrelBuilder itemInput(Ingredient input, int amount) {
        this.itemInput = TFCRecipeHelpers.parseItemStackIngredient(input, amount);
        return this;
    }

    public InstantBarrelBuilder itemInput(ItemStack input, int amount) {
        return this.itemInput(Ingredient.of(input), amount);
    }

    public InstantBarrelBuilder itemInput(TagKey<Item> input, int amount) {
        return this.itemInput(Ingredient.of(input), amount);
    }

    public InstantBarrelBuilder fluidInput(Fluid input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public InstantBarrelBuilder fluidInput(TagKey<Fluid> input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public InstantBarrelBuilder fluidOutput(FluidStack output) {
        this.fluidOutput = output;
        return this;
    }

    public InstantBarrelBuilder itemOutput(ItemStackProvider output) {
        this.itemOutput = output;
        return this;
    }

    public InstantBarrelBuilder itemOutput(ItemStack output) {
        this.itemOutput = ItemStackProvider.of(output);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedInstantBarrelRecipe(this.id, this.itemInput, this.fluidInput, this.itemOutput, this.fluidOutput, this.sound));
    }
}
