package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedJamPotRecipe;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class JamPotBuilder extends PotBuilder<JamPotBuilder> {

    private ItemStack result;
    private ResourceLocation texture;

    public JamPotBuilder output(ItemStack stack) {
        this.result = stack;
        return this;
    }

    public JamPotBuilder texture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    @Override
    void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedJamPotRecipe(this.id, this.ingredients, this.fluidInput, this.duration, this.temperature, this.result, this.texture));
    }

    @Override
    JamPotBuilder getThis() {
        return this;
    }
}
