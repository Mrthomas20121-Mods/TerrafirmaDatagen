package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
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

public record FinishedSealedBarrelRecipe(ResourceLocation id, JsonElement itemInput, JsonElement fluidInput, ItemStackProvider result, FluidStack fluidResult, SoundEvent sound, int duration, ItemStackProvider onSeal, ItemStackProvider onUnseal) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {

        if(!itemInput.isJsonNull()) {
            jsonObject.add("input_item", itemInput);
        }

        if(!fluidInput.isJsonNull()) {
            jsonObject.add("input_fluid", fluidInput);
        }

        if(!fluidResult.isEmpty()) {
            jsonObject.add("output_fluid", TFCRecipeHelpers.parseFluidStack(fluidResult));
        }

        if(result != null) {
            jsonObject.add("output_item", TFCRecipeHelpers.parseProvider(result));
        }

        if(sound != null) {
            String name = Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getKey(sound)).toString();
            jsonObject.addProperty("sound", name);
        }

        if(onSeal != null) {
            jsonObject.add("on_seal", TFCRecipeHelpers.parseProvider(this.onSeal));
        }

        if(onUnseal != null) {
            jsonObject.add("on_unseal", TFCRecipeHelpers.parseProvider(this.onUnseal));
        }
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<InstantBarrelRecipe> getType() {
        return TFCRecipeSerializers.INSTANT_BARREL.get();
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
