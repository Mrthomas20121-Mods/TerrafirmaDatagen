package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedWeldingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class WeldingBuilder extends RecipeBuilder<WeldingBuilder> {

    private Ingredient input;
    private Ingredient input2;
    private int minTier;
    private ItemStackProvider output;

    public static WeldingBuilder create() { return new WeldingBuilder(); }

    WeldingBuilder() {}

    @Override
    WeldingBuilder getThis() {
        return this;
    }

    public WeldingBuilder inputs(Ingredient input, Ingredient input2) {
        this.input = input;
        this.input2 = input2;
        return this;
    }

    public WeldingBuilder inputs(ItemStack input, ItemStack input2) {
        return this.inputs(Ingredient.of(input), Ingredient.of(input2));
    }

    public WeldingBuilder inputs(TagKey<Item> input, TagKey<Item> input2) {
        return this.inputs(Ingredient.of(input), Ingredient.of(input2));
    }

    public WeldingBuilder inputs(ItemStack input, TagKey<Item> input2) {
        return this.inputs(Ingredient.of(input), Ingredient.of(input2));
    }

    public WeldingBuilder inputs(TagKey<Item> input, ItemStack input2) {
        return this.inputs(Ingredient.of(input), Ingredient.of(input2));
    }

    public WeldingBuilder tier(int minTier) {
        this.minTier = minTier;
        return this;
    }

    public WeldingBuilder tier(Metal.Tier minTier) {
        this.minTier = minTier.ordinal();
        return this;
    }

    public WeldingBuilder output(ItemStack output) {
        this.output = ItemStackProvider.of(output);
        return this;
    }

    public WeldingBuilder output(ItemStack output, ItemStackModifier... modifiers) {
        this.output = ItemStackProvider.of(output, modifiers);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedWeldingRecipe(this.id, this.input, this.input2, this.minTier, this.output));
    }
}
