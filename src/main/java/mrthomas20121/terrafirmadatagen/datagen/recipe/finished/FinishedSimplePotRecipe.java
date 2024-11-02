package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.recipes.SimplePotRecipe;
import net.dries007.tfc.common.recipes.SoupPotRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record FinishedSimplePotRecipe(ResourceLocation id, Ingredient[] input, JsonElement fluidIngredient, int duration, float temperature, ItemStackProvider itemOutput, FluidStack fluidOutput) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        if(input.length > 0) {
            jsonObject.add("ingredients", TFCRecipeHelpers.ingredientsToJsonArray(input));
        }

        if(!fluidIngredient.isJsonNull()) {
            jsonObject.add("fluid_ingredient", fluidIngredient);
        }

        jsonObject.addProperty("duration", duration);
        jsonObject.addProperty("temperature", temperature);

        if(itemOutput != null) {
            jsonObject.add("item_output", TFCRecipeHelpers.parseProvider(itemOutput));
        }
        if(!fluidOutput.isEmpty()) {
            jsonObject.add("fluid_output", TFCRecipeHelpers.parseFluidStack(fluidOutput));
        }
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<SimplePotRecipe> getType() {
        return TFCRecipeSerializers.POT_SIMPLE.get();
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
