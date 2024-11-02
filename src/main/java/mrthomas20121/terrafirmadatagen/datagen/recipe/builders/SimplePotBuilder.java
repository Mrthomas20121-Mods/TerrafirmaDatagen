package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedSimplePotRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedSoupPotRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class SimplePotBuilder extends PotBuilder<SimplePotBuilder> {

    private FluidStack fluidStack = FluidStack.EMPTY;
    private ItemStackProvider output = null;

    public SimplePotBuilder output(FluidStack stack) {
        this.fluidStack = stack;
        return this;
    }

    public SimplePotBuilder output(ItemStackProvider provider) {
        this.output = provider;
        return this;
    }

    public SimplePotBuilder output(ItemStack stack, ItemStackModifier... modifiers) {
        return this.output(ItemStackProvider.of(stack, modifiers));
    }

    @Override
    void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedSimplePotRecipe(this.id, this.ingredients, this.fluidInput, this.duration, this.temperature, this.output, this.fluidStack));
    }

    @Override
    SimplePotBuilder getThis() {
        return this;
    }
}
