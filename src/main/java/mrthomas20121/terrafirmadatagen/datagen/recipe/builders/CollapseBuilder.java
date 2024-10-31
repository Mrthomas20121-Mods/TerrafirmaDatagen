package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedChiselRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedCollapseRecipe;
import net.dries007.tfc.common.recipes.ChiselRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class CollapseBuilder extends RecipeBuilder<CollapseBuilder> {

    private JsonElement input;
    private JsonElement result;
    private boolean copyInput = false;

    public static CollapseBuilder create() { return new CollapseBuilder(); }

    CollapseBuilder() {}

    @Override
    CollapseBuilder getThis() {
        return this;
    }

    public CollapseBuilder copyInput() {
        this.copyInput = true;
        return this;
    }

    public CollapseBuilder inputBlockTag(TagKey<Block> input) {
        this.input = TFCRecipeHelpers.parseBlockIngredient(input);
        return this;
    }

    public CollapseBuilder inputBlocks(Block... inputs) {
        this.input = TFCRecipeHelpers.parseBlockIngredient(inputs);
        return this;
    }

    public CollapseBuilder result(BlockState blockState) {
        this.result = TFCRecipeHelpers.parseBlockState(blockState);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedCollapseRecipe(this.id, this.input, this.copyInput, this.result));
    }
}
