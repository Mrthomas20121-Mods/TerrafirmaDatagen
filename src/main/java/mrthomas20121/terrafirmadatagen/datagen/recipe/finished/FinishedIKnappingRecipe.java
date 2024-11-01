package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.recipes.HeatingRecipe;
import net.dries007.tfc.common.recipes.KnappingRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record FinishedIKnappingRecipe(ResourceLocation id, String knappingType, Ingredient itemInput, JsonArray pattern, ItemStack result, boolean outside_slot_required) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {

        jsonObject.addProperty("knapping_type", knappingType);

        if(!itemInput.isEmpty()) {
            jsonObject.add("ingredient", itemInput.toJson());
        }

        jsonObject.add("pattern", pattern);

        if(result != null) {
            jsonObject.add("result", TFCRecipeHelpers.parseItemStack(result));
        }

        jsonObject.addProperty("outside_slot_required", outside_slot_required);
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<KnappingRecipe> getType() {
        return TFCRecipeSerializers.KNAPPING.get();
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
