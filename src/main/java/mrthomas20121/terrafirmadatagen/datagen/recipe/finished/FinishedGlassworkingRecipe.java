package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.capabilities.glass.GlassOperation;
import net.dries007.tfc.common.recipes.GlassworkingRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.stream.Stream;

public record FinishedGlassworkingRecipe(ResourceLocation id, Ingredient batch, GlassOperation[] operations, ItemStack result) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.add("operations", TFCRecipeHelpers.stringIterableToJsonArray(Stream.of(operations).map(o -> o.name().toLowerCase(Locale.ROOT)).toList()));
        jsonObject.add("batch", batch.toJson());
        jsonObject.add("result", TFCRecipeHelpers.parseItemStack(result));
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<GlassworkingRecipe> getType() {
        return TFCRecipeSerializers.GLASSWORKING.get();
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
