package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedInstantFluidBarrelRecipe;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class InstantFluidBarrelBuilder extends SoundRecipeBuilder<InstantFluidBarrelBuilder> {

    private JsonElement fluidInput;
    private JsonElement fluidInput2;
    private FluidStack fluidOutput;

    public static InstantFluidBarrelBuilder create() { return new InstantFluidBarrelBuilder(); }

    InstantFluidBarrelBuilder() {}

    @Override
    InstantFluidBarrelBuilder getThis() {
        return this;
    }

    public InstantFluidBarrelBuilder fluidInput(Fluid input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public InstantFluidBarrelBuilder fluidInput(TagKey<Fluid> input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public InstantFluidBarrelBuilder fluidInput2(Fluid input, int amount) {
        this.fluidInput2 = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public InstantFluidBarrelBuilder fluidInput2(TagKey<Fluid> input, int amount) {
        this.fluidInput2 = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public InstantFluidBarrelBuilder fluidOutput(FluidStack output) {
        this.fluidOutput = output;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedInstantFluidBarrelRecipe(this.id, this.fluidInput, this.fluidInput2, this.fluidOutput, this.sound));
    }
}
