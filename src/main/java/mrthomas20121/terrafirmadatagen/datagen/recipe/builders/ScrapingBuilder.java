package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedQuernRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedScrapingRecipe;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class ScrapingBuilder extends RecipeBuilder<ScrapingBuilder> {

    private Ingredient input;
    private ItemStack output;
    private ResourceLocation inputTexture;
    private ResourceLocation outputTexture;
    private ItemStack extraDrop = ItemStack.EMPTY;

    public static ScrapingBuilder create() { return new ScrapingBuilder(); }

    ScrapingBuilder() {}

    @Override
    ScrapingBuilder getThis() {
        return this;
    }

    public ScrapingBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public ScrapingBuilder input(ItemStack input) {
        return this.input(Ingredient.of(input));
    }

    public ScrapingBuilder input(TagKey<Item> input) {
        return this.input(Ingredient.of(input));
    }

    /**
     * @param input The identifier of the texture displayed on the block for the unfinished item. Must be an existing item/block texture or stitched to the atlas.
     * @return
     */
    public ScrapingBuilder inputTexture(ResourceLocation input) {
        this.inputTexture = input;
        return this;
    }

    /**
     * @param output The identifier of the texture displayed on the block for the finished item. Must be an existing item/block texture or stitched to the atlas.
     * @return
     */
    public ScrapingBuilder outputTexture(ResourceLocation output) {
        this.outputTexture = output;
        return this;
    }

    public ScrapingBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public ScrapingBuilder extraDrop(ItemStack output) {
        this.extraDrop = output;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedScrapingRecipe(this.id, this.input, this.output, this.inputTexture, this.outputTexture, this.extraDrop));
    }
}
