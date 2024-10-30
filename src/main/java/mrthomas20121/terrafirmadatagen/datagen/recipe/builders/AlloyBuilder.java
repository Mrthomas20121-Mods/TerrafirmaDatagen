package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import com.google.gson.JsonArray;
import mrthomas20121.terrafirmadatagen.datagen.recipe.finished.FinishedAlloyRecipe;
import net.dries007.tfc.common.recipes.AlloyRecipe;
import net.dries007.tfc.util.DataManager;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AlloyBuilder extends RecipeBuilder<AlloyBuilder> {

    private DataManager.Reference<Metal> result;
    private final Map<DataManager.Reference<Metal>, AlloyRecipe.Range> metals = new IdentityHashMap<>();

    public static AlloyBuilder create() {
        return new AlloyBuilder();
    }

    protected AlloyBuilder() {}

    public AlloyBuilder result(String metal) {
        this.result = Metal.MANAGER.getReference(new ResourceLocation(metal));
        return this;
    }

    public AlloyBuilder input(String metal, double min, double max) {
        this.metals.put(Metal.MANAGER.getReference(new ResourceLocation(metal)), new AlloyRecipe.Range(min, max));
        return this;
    }

    @Override
    void build(Consumer<FinishedRecipe> consumer) {
        consumer.accept(new FinishedAlloyRecipe(this.id, this.result, this.metals));
    }

    @Override
    AlloyBuilder getThis() {
        return this;
    }
}
