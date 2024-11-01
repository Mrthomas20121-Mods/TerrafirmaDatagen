package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonElement;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedCollapseRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedGlassworkingRecipe;
import net.dries007.tfc.common.capabilities.glass.GlassOperation;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Consumer;

public class GlassworkingBuilder extends RecipeBuilder<GlassworkingBuilder> {

    private Ingredient batch;
    private GlassOperation[] operations;
    private ItemStack result;

    public static GlassworkingBuilder create() { return new GlassworkingBuilder(); }

    GlassworkingBuilder() {}

    @Override
    GlassworkingBuilder getThis() {
        return this;
    }

    public GlassworkingBuilder operations(GlassOperation... operations) {
        this.operations = operations;
        return this;
    }

    public GlassworkingBuilder batch(TagKey<Item> input) {
        this.batch = Ingredient.of(input);
        return this;
    }

    public GlassworkingBuilder batch(Ingredient input) {
        this.batch = input;
        return this;
    }

    public GlassworkingBuilder batch(ItemStack input) {
        this.batch = Ingredient.of(input);
        return this;
    }

    public GlassworkingBuilder result(ItemStack itemStack) {
        this.result = itemStack;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedGlassworkingRecipe(this.id, this.batch, this.operations, this.result));
    }
}
