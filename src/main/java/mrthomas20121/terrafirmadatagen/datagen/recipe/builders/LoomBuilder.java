package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedLandslideRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedLoomRecipe;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class LoomBuilder extends RecipeBuilder<LoomBuilder> {

    private JsonElement input;
    private ItemStack result;
    private int steps_required;
    private ResourceLocation in_progress_texture;

    public static LoomBuilder create() { return new LoomBuilder(); }

    LoomBuilder() {}

    @Override
    LoomBuilder getThis() {
        return this;
    }

    /**
     * @param n An integer, which determines how many steps of the loom’s working animation need to be completed to produce one product item.
     * @return
     */
    public LoomBuilder stepsRequired(int n) {
        this.steps_required = n;
        return this;
    }

    public LoomBuilder inProgressTexture(ResourceLocation texture) {
        this.in_progress_texture = texture;
        return this;
    }

    public LoomBuilder input(TagKey<Item> input, int count) {
        this.input = TFCRecipeHelpers.parseItemStackIngredient(Ingredient.of(input), count);
        return this;
    }

    public LoomBuilder input(Ingredient inputIngredient, int count) {
        this.input = TFCRecipeHelpers.parseItemStackIngredient(inputIngredient, count);
        return this;
    }

    public LoomBuilder input(ItemStack inputItem, int count) {
        this.input = TFCRecipeHelpers.parseItemStackIngredient(Ingredient.of(inputItem), count);
        return this;
    }

    public LoomBuilder input(ItemStack... inputs) {
        this.input = TFCRecipeHelpers.parseItemStackIngredient(Ingredient.of(inputs), 1);
        return this;
    }

    public LoomBuilder result(ItemStack itemStack) {
        this.result = itemStack;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedLoomRecipe(this.id, this.input, this.result, this.steps_required, this.in_progress_texture));
    }
}
