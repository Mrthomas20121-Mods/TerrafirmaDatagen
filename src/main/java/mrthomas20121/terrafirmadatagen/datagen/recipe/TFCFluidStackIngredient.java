package mrthomas20121.terrafirmadatagen.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public record TFCFluidStackIngredient(String ingredient, int amount) {

    public static TFCFluidStackIngredient of(Fluid fluid, int amount) {
        return of(Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)), amount);
    }

    public static TFCFluidStackIngredient of(ResourceLocation fluid, int amount) {
        return new TFCFluidStackIngredient(fluid.toString(), amount);
    }

    public static TFCFluidStackIngredient of(String fluid, int amount) {
        return new TFCFluidStackIngredient(fluid, amount);
    }

    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("ingredient", ingredient);
        obj.addProperty("amount", amount);
        return obj;
    }
}
