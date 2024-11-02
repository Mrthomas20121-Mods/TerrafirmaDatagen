package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.recipes.QuernRecipe;
import net.dries007.tfc.common.recipes.ScrapingRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record FinishedScrapingRecipe(ResourceLocation id, Ingredient input, ItemStack output, ResourceLocation inputTexture, ResourceLocation outputTexture, ItemStack extraDrop) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.add("ingredient", input.toJson());
        jsonObject.add("result", TFCRecipeHelpers.parseItemStack(output));

        jsonObject.addProperty("input_texture", inputTexture.toString());
        jsonObject.addProperty("output_texture", outputTexture.toString());

        if(!extraDrop.isEmpty()) {
            jsonObject.add("extra_drop", TFCRecipeHelpers.parseItemStack(extraDrop));
        }
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<ScrapingRecipe> getType() {
        return TFCRecipeSerializers.SCRAPING.get();
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
