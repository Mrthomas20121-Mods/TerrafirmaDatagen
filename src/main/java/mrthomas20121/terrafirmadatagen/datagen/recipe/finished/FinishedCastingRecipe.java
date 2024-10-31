package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.recipes.BlastFurnaceRecipe;
import net.dries007.tfc.common.recipes.CastingRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record FinishedCastingRecipe(ResourceLocation id, JsonElement fluidInput, Ingredient catalyst, ItemStack output, int breakChance) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        jsonObject.add("fluid", fluidInput);
        jsonObject.add("mold", catalyst.toJson());
        jsonObject.add("result", TFCRecipeHelpers.parseItemStack(output));
        jsonObject.addProperty("break_chance", breakChance);
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<CastingRecipe> getType() {
        return TFCRecipeSerializers.CASTING.get();
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
