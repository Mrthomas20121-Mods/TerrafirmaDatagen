package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dries007.tfc.common.recipes.AlloyRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.dries007.tfc.util.DataManager;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public record FinishedAlloyRecipe(ResourceLocation id, DataManager.Reference<Metal> result, Map<DataManager.Reference<Metal>, AlloyRecipe.Range> metals) implements FinishedRecipe {
    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.addProperty("result", result.id().toString());

        JsonArray contents = new JsonArray();

        metals.forEach((ref, range) -> {
            JsonObject obj = new JsonObject();

            obj.addProperty("metal", ref.id().toString());
            obj.addProperty("min", range.min());
            obj.addProperty("max", range.max());

            contents.add(obj);
        });

        jsonObject.add("contents", contents);
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getType() {
        return TFCRecipeSerializers.ALLOY.get();
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
