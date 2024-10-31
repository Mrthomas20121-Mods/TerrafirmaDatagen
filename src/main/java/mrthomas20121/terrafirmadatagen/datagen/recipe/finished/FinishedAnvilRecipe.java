package mrthomas20121.terrafirmadatagen.datagen.recipe.finished;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.capabilities.forge.ForgeRule;
import net.dries007.tfc.common.recipes.AnvilRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public record FinishedAnvilRecipe(ResourceLocation id, Ingredient input, int minTier, ForgeRule[] rules,
                                  boolean applyForgingBonus, ItemStackProvider output) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
        JsonArray ruleArray = new JsonArray();

        Arrays.stream(rules).map(ForgeRule::name).map(String::toLowerCase).forEach(ruleArray::add);

        jsonObject.add("input", input.toJson());
        jsonObject.add("result", TFCRecipeHelpers.parseProvider(output));
        jsonObject.addProperty("tier", minTier);

        jsonObject.add("rules", ruleArray);

        if(applyForgingBonus) {
            jsonObject.addProperty("apply_forging_bonus", true);
        }
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<AnvilRecipe> getType() {
        return TFCRecipeSerializers.ANVIL.get();
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
