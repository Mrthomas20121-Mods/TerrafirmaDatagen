package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.recipes.QuernRecipe;
import net.dries007.tfc.common.recipes.SoupPotRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record FinishedSoupPotRecipe(ResourceLocation id, Ingredient[] input, JsonElement fluidIngredient, int duration, float temperature) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.add("ingredients", TFCRecipeHelpers.ingredientsToJsonArray(input));
        jsonObject.add("fluid_ingredient", fluidIngredient);
        jsonObject.addProperty("duration", duration);
        jsonObject.addProperty("temperature", temperature);
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<SoupPotRecipe> getType() {
        return TFCRecipeSerializers.POT_SOUP.get();
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
