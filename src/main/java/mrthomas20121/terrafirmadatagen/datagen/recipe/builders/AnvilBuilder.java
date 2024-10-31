package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedAnvilRecipe;
import net.dries007.tfc.common.capabilities.forge.ForgeRule;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class AnvilBuilder extends RecipeBuilder<AnvilBuilder> {

    private Ingredient input;
    private int minTier;
    private ForgeRule[] rules;
    private boolean applyForgingBonus = false;
    private ItemStackProvider output;

    public static AnvilBuilder anvil() { return new AnvilBuilder(); }

    private AnvilBuilder() {}

    @Override
    AnvilBuilder getThis() {
        return this;
    }

    public AnvilBuilder forgingBonus() {
        this.applyForgingBonus = true;
        return this;
    }

    public AnvilBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public AnvilBuilder input(ItemStack input) {
        this.input = Ingredient.of(input);
        return this;
    }

    public AnvilBuilder input(TagKey<Item> input) {
        this.input = Ingredient.of(input);
        return this;
    }

    public AnvilBuilder tier(int minTier) {
        this.minTier = minTier;
        return this;
    }

    public AnvilBuilder tier(Metal.Tier minTier) {
        this.minTier = minTier.ordinal();
        return this;
    }

    public AnvilBuilder forgeRules(ForgeRule[] rules) {
        this.rules = rules;
        return this;
    }

    public AnvilBuilder output(ItemStack output) {
        this.output = ItemStackProvider.of(output);
        return this;
    }

    public AnvilBuilder output(ItemStack output, ItemStackModifier... modifiers) {
        this.output = ItemStackProvider.of(output, modifiers);
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedAnvilRecipe(this.id, this.input, this.minTier, this.rules, this.applyForgingBonus, this.output));
    }
}
