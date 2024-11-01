package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedCollapseRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedLandslideRecipe;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class LandslideBuilder extends RecipeBuilder<LandslideBuilder> {

    private JsonElement input;
    private JsonElement result;
    private boolean copyInput = false;

    public static LandslideBuilder create() { return new LandslideBuilder(); }

    LandslideBuilder() {}

    @Override
    LandslideBuilder getThis() {
        return this;
    }

    public LandslideBuilder copyInput() {
        this.copyInput = true;
        return this;
    }

    public LandslideBuilder inputBlockTag(TagKey<Block> input) {
        this.input = TFCRecipeHelpers.parseBlockIngredient(input);
        return this;
    }

    public LandslideBuilder inputBlocks(Block... inputs) {
        this.input = TFCRecipeHelpers.parseBlockIngredient(inputs);
        return this;
    }

    public LandslideBuilder result(BlockState blockState) {
        this.result = TFCRecipeHelpers.parseBlockState(blockState);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedLandslideRecipe(this.id, this.input, this.copyInput, this.result));
    }
}
