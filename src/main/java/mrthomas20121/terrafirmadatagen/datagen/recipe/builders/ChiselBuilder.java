package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedCastingRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedChiselRecipe;
import net.dries007.tfc.common.recipes.ChiselRecipe;
import net.dries007.tfc.common.recipes.ingredients.BlockIngredient;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Consumer;

public class ChiselBuilder extends RecipeBuilder<ChiselBuilder> {

    private JsonElement input;
    private JsonElement result;
    private ItemStackProvider extra;

    private ChiselRecipe.Mode mode;

    public static ChiselBuilder create() { return new ChiselBuilder(); }

    ChiselBuilder() {}

    @Override
    ChiselBuilder getThis() {
        return this;
    }

    public ChiselBuilder mode(ChiselRecipe.Mode mode) {
        this.mode = mode;
        return this;
    }

    public ChiselBuilder inputBlockTag(TagKey<Block> input) {
        this.input = TFCRecipeHelpers.parseBlockIngredient(input);
        return this;
    }

    public ChiselBuilder inputBlocks(Block... inputs) {
        this.input = TFCRecipeHelpers.parseBlockIngredient(inputs);
        return this;
    }

    public ChiselBuilder extra(ItemStackProvider output) {
        this.extra = output;
        return this;
    }

    public ChiselBuilder extra(ItemStack output) {
        return this.extra(ItemStackProvider.of(output));
    }

    public ChiselBuilder extra(ItemStack output, ItemStackModifier... modifiers) {
        return this.extra(ItemStackProvider.of(output, modifiers));
    }

    public ChiselBuilder result(BlockState blockState) {
        this.result = TFCRecipeHelpers.parseBlockState(blockState);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedChiselRecipe(this.id, this.input, this.mode, this.result, this.extra));
    }
}
