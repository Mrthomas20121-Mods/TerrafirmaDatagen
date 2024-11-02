package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedQuernRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedWeldingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class QuernBuilder extends RecipeBuilder<QuernBuilder> {

    private Ingredient input;
    private ItemStack output;

    public static QuernBuilder create() { return new QuernBuilder(); }

    QuernBuilder() {}

    @Override
    QuernBuilder getThis() {
        return this;
    }

    public QuernBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public QuernBuilder input(ItemStack input) {
        return this.input(Ingredient.of(input));
    }

    public QuernBuilder input(TagKey<Item> input) {
        return this.input(Ingredient.of(input));
    }

    public QuernBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedQuernRecipe(this.id, this.input, this.output));
    }
}
