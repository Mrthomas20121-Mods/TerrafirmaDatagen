package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedSoupPotRecipe;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class SoupPotBuilder extends PotBuilder<SoupPotBuilder> {

    @Override
    void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedSoupPotRecipe(this.id, this.ingredients, this.fluidInput, this.duration, this.temperature));
    }

    @Override
    SoupPotBuilder getThis() {
        return this;
    }
}
