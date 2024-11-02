package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedSewingRecipe;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class SewingBuilder extends RecipeBuilder<SewingBuilder> {

    private int[] stitches;
    private int[] squares;
    private ItemStack output;

    public static SewingBuilder create() { return new SewingBuilder(); }

    SewingBuilder() {}

    @Override
    SewingBuilder getThis() {
        return this;
    }

    public SewingBuilder squares(int... square) {
        this.squares = square;
        return this;
    }

    public SewingBuilder stitches(int... stitch) {
        this.stitches = stitch;
        return this;
    }

    public SewingBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedSewingRecipe(this.id, this.output, TFCRecipeHelpers.intArrayToJsonArray(this.stitches), TFCRecipeHelpers.intArrayToJsonArray(this.squares)));
    }
}
