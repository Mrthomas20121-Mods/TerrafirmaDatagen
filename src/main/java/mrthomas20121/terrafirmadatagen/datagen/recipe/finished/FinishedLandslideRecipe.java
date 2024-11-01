package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dries007.tfc.common.recipes.CollapseRecipe;
import net.dries007.tfc.common.recipes.LandslideRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record FinishedLandslideRecipe(ResourceLocation id, JsonElement ingredient, boolean copyInput, JsonElement result) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.add("ingredient", ingredient);

        if(result != null && !copyInput) {
            jsonObject.add("result", result);
        }
        else {
            jsonObject.addProperty("copyInput", true);
        }
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<LandslideRecipe> getType() {
        return TFCRecipeSerializers.LANDSLIDE.get();
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
