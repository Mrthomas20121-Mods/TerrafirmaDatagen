package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.dries007.tfc.common.recipes.WeldingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record FinishedWeldingRecipe(ResourceLocation id, Ingredient input, Ingredient input2, int minTier, ItemStackProvider output) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.add("first_input", input.toJson());
        jsonObject.add("second_input", input2.toJson());
        jsonObject.addProperty("tier", minTier);
        jsonObject.add("result", TFCRecipeHelpers.provider(output));
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<WeldingRecipe> getType() {
        return TFCRecipeSerializers.WELDING.get();
    }

    @Nullable
    @Override
    public JsonObject serializeAdvancement() {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getAdvancementId() {
        return null;
    }
}
