package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.recipes.HeatingRecipe;
import net.dries007.tfc.common.recipes.InstantBarrelRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record FinishedIHeatingRecipe(ResourceLocation id, JsonElement itemInput, ItemStackProvider result, FluidStack fluidResult, int temp) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {

        jsonObject.add("ingredient", itemInput);

        if(!fluidResult.isEmpty()) {
            jsonObject.add("result_fluid", TFCRecipeHelpers.parseFluidStack(fluidResult));
        }

        if(result != null) {
            jsonObject.add("result_item", TFCRecipeHelpers.parseProvider(result));
        }

        jsonObject.addProperty("temperature", temp);
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<HeatingRecipe> getType() {
        return TFCRecipeSerializers.HEATING.get();
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
