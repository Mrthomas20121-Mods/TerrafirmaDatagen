package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonArray;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedAnvilRecipe;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedIKnappingRecipe;
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class KnappingBuilder extends RecipeBuilder<KnappingBuilder> {

    private Ingredient input = Ingredient.EMPTY;
    private ResourceLocation type;

    private boolean outside_slot_required = true;

    private final List<String> pattern = new ArrayList<>();

    private ItemStack output;

    public static KnappingBuilder builder() { return new KnappingBuilder(); }

    private KnappingBuilder() {}

    @Override
    KnappingBuilder getThis() {
        return this;
    }

    /**
     * @param outside_slot_required Boolean. (Default: true) For knapping patterns that are smaller than 5 x 5, this defines if the slots outside that grid are required to be filled, or empty.
     * @return
     */
    public KnappingBuilder outsideSlotRequired(boolean outside_slot_required) {
        this.outside_slot_required = outside_slot_required;
        return this;
    }

    /**
     * @param type An id of a Knapping Type.
     * @return
     */
    public KnappingBuilder knappingType(ResourceLocation type) {
        this.type = type;
        return this;
    }

    public KnappingBuilder pattern(String str) {
        this.pattern.add(str);
        return this;
    }

    public KnappingBuilder optionalInput(Ingredient input) {
        this.input = input;
        return this;
    }

    public KnappingBuilder optionalInput(ItemStack input) {
        this.input = Ingredient.of(input);
        return this;
    }

    public KnappingBuilder optionalInput(TagKey<Item> input) {
        this.input = Ingredient.of(input);
        return this;
    }

    public KnappingBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        JsonArray patternArray = new JsonArray();
        Stream.of(this.pattern.toArray(String[]::new)).forEach(patternArray::add);
        consumer.accept(new FinishedIKnappingRecipe(this.id, this.type.toString(), this.input, patternArray, this.output, this.outside_slot_required));
    }
}
