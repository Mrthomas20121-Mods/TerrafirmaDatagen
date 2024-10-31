package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.recipes.InstantFluidBarrelRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record FinishedInstantFluidBarrelRecipe(ResourceLocation id, JsonElement fluidInput, JsonElement fluidInput2, FluidStack fluidResult, SoundEvent sound) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {

        jsonObject.add("primary_fluid", fluidInput);
        jsonObject.add("added_fluid", fluidInput2);

        jsonObject.add("output_fluid", TFCRecipeHelpers.parseFluidStack(fluidResult));

        if(sound != null) {
            String name = Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getKey(sound)).toString();
            jsonObject.addProperty("sound", name);
        }
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<InstantFluidBarrelRecipe> getType() {
        return TFCRecipeSerializers.INSTANT_FLUID_BARREL.get();
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
