package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedInstantBarrelRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedSealedBarrelRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class SealedBarrelBuilder extends SoundRecipeBuilder<SealedBarrelBuilder> {

    private JsonElement itemInput = JsonNull.INSTANCE;
    private JsonElement fluidInput = JsonNull.INSTANCE;
    private FluidStack fluidOutput = FluidStack.EMPTY;
    private ItemStackProvider itemOutput;

    private int duration = 0;

    private ItemStackProvider onSeal = null;
    private ItemStackProvider onUnseal = null;

    public static SealedBarrelBuilder create() { return new SealedBarrelBuilder(); }

    SealedBarrelBuilder() {}

    @Override
    SealedBarrelBuilder getThis() {
        return this;
    }

    public SealedBarrelBuilder onSeal(ItemStack output) {
        this.onSeal = ItemStackProvider.of(output);
        return this;
    }

    public SealedBarrelBuilder onSeal(ItemStack output, ItemStackModifier... modifiers) {
        this.onSeal = ItemStackProvider.of(output, modifiers);
        return this;
    }

    public SealedBarrelBuilder onSeal(ItemStackModifier... modifiers) {
        this.onSeal = ItemStackProvider.of(ItemStack.EMPTY, modifiers);
        return this;
    }

    public SealedBarrelBuilder onUnseal(ItemStack output) {
        this.onUnseal = ItemStackProvider.of(output);
        return this;
    }

    public SealedBarrelBuilder onUnseal(ItemStack output, ItemStackModifier... modifiers) {
        this.onUnseal = ItemStackProvider.of(output, modifiers);
        return this;
    }

    public SealedBarrelBuilder onUnseal(ItemStackModifier... modifiers) {
        this.onUnseal = ItemStackProvider.of(ItemStack.EMPTY, modifiers);
        return this;
    }

    public SealedBarrelBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    public SealedBarrelBuilder itemInput(Ingredient input, int amount) {
        this.itemInput = TFCRecipeHelpers.parseItemStackIngredient(input, amount);
        return this;
    }

    public SealedBarrelBuilder itemInput(ItemStack input, int amount) {
        return this.itemInput(Ingredient.of(input), amount);
    }

    public SealedBarrelBuilder itemInput(TagKey<Item> input, int amount) {
        return this.itemInput(Ingredient.of(input), amount);
    }

    public SealedBarrelBuilder fluidInput(Fluid input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public SealedBarrelBuilder fluidInput(TagKey<Fluid> input, int amount) {
        this.fluidInput = TFCRecipeHelpers.parseFluidStackIngredient(input, amount);
        return this;
    }

    public SealedBarrelBuilder fluidOutput(FluidStack output) {
        this.fluidOutput = output;
        return this;
    }

    public SealedBarrelBuilder itemOutput(ItemStackProvider output) {
        this.itemOutput = output;
        return this;
    }

    public SealedBarrelBuilder itemOutput(ItemStack output) {
        this.itemOutput = ItemStackProvider.of(output);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedSealedBarrelRecipe(this.id, this.itemInput, this.fluidInput, this.itemOutput, this.fluidOutput, this.sound, this.duration, this.onSeal, this.onUnseal));
    }
}
