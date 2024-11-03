package mrthomas20121.terrafirmadatagen.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mrthomas20121.terrafirmadatagen.TerrafirmaDatagen;
import mrthomas20121.terrafirmadatagen.api.ExtendedRegistryMetal;
import mrthomas20121.terrafirmadatagen.api.tags.MetalTag;
import mrthomas20121.terrafirmadatagen.api.tags.TFCBlockTags;
import mrthomas20121.terrafirmadatagen.datagen.recipe.TFCRecipeHelpers;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.common.capabilities.size.Weight;
import net.dries007.tfc.util.Drinkable;
import net.dries007.tfc.util.registry.RegistryRock;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * DataProvider for TFC Data(fuel, metal heat, etc...)
 */
public abstract class CustomDataProvider implements DataProvider {

    private final PackOutput output;
    private final String modid;

    Map<String, JsonObject> data = new HashMap<>();

    public CustomDataProvider(PackOutput output, String modid) {

        this.output = output;
        this.modid = modid;
    }

    protected void sluicing(String path, Ingredient ingredient, String lootTable) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("loot_table", lootTable);

        data.put("sluicing/"+path+".json", object);
    }

    /**
     * This is specifically to do it like tfc
     * for the models to work, you need to call ForgeModelBakery#addSpecialModel during ModelRegistryEvent
     * See the tfc docs for more info
     * @param path path
     * @param block block
     * @param ore the ore name
     * @param rock a RegistryRock object
     */
    protected void orePanning(String path, Block block, String ore, RegistryRock rock) {
        String rockName = rock.getSerializedName();
        this.panning(path, block, List.of("%s:item/pan/%s/%s_full".formatted(this.modid, ore, rockName),
                "%s:item/pan/%s/%s_half".formatted(this.modid, ore, rockName),
                "%s:item/pan/%s/result".formatted(this.modid, ore)), "%s:panning/deposits/%s_%s".formatted(this.modid, ore, rockName));
    }

    /**
     * @param path path
     * @param block block
     * @param model_stages An array of model locations that will be iterated through and rendered as panning progresses. If no panning is in progress, just the first model is rendered. This array must not be empty, but can be any length.
     * @param loot_table The location of a loot table to be dropped for this block after panning.
     */
    protected void panning(String path, Block block, List<String> model_stages, String loot_table) {
        JsonObject object = new JsonObject();

        object.add("ingredient", TFCRecipeHelpers.parseBlockIngredient(block));
        object.add("model_stages", TFCRecipeHelpers.stringIterableToJsonArray(model_stages));
        object.addProperty("loot_table", loot_table);

        data.put("panning/"+path+".json", object);
    }

    /**
     *
     * @param path path
     * @param block
     * @param model_stages An array of model locations that will be iterated through and rendered as panning progresses. If no panning is in progress, just the first model is rendered. This array must not be empty, but can be any length.
     * @param loot_table The location of a loot table to be dropped for this block after panning.
     */
    protected void panning(String path, TagKey<Block> block, List<String> model_stages, String loot_table) {
        JsonObject object = new JsonObject();

        object.add("ingredient", TFCRecipeHelpers.parseBlockIngredient(block));
        object.add("model_stages", TFCRecipeHelpers.stringIterableToJsonArray(model_stages));
        object.addProperty("loot_table", loot_table);

        data.put("panning/"+path+".json", object);
    }

    protected void metal(String path, ExtendedRegistryMetal metal) {
        MetalTag tags = metal.getTags();
        this.metal(path, metal.metalTier().ordinal(), metal.getFluid(), metal.getMeltTemperature(), metal.getHeatCapacity(false), Ingredient.of(tags.getIngot()), Ingredient.of(tags.getDoubleIngot()), Ingredient.of(tags.getSheets()));
    }

    protected void metal(String path, int tier, Fluid fluid, float melt_temperature, float specific_heat_capacity, Ingredient ingot, Ingredient double_ingot, Ingredient sheet) {
        JsonObject object = new JsonObject();

        object.add("ingots", ingot.toJson());
        object.add("double_ingots", double_ingot.toJson());
        object.add("sheets", sheet.toJson());

        data.put("metals/"+path+".json", object);
    }

    protected void lamp_fuel(String path, Fluid fluid, int burn_rate) {
        this.lamp_fuel(path, fluid, TFCRecipeHelpers.parseBlockIngredient(TFCBlockTags.LAMPS), burn_rate);
    }

    protected void lamp_fuel(String path, Fluid fluid, JsonElement lamp, int burn_rate) {
        JsonObject object = new JsonObject();

        object.addProperty("fluid", Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)).toString());
        object.add("valid_lamps", lamp);
        object.addProperty("burn_rate", burn_rate);

        data.put("lamp_fuel/"+path+".json", object);
    }

    protected void knapping_type(String path, Ingredient input, int count, int amount_to_consume, SoundEvent click_sound, boolean consume_after_complete, boolean use_disabled_texture, boolean spawns_particles, ItemStack jei_icon_item) {
        JsonObject object = new JsonObject();

        object.add("input", TFCRecipeHelpers.parseItemStackIngredient(input, count));
        object.addProperty("amount_to_consume", amount_to_consume);
        object.addProperty("click_sound", Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getKey(click_sound)).toString());
        object.addProperty("consume_after_complete", consume_after_complete);
        object.addProperty("use_disabled_texture", use_disabled_texture);
        object.addProperty("spawns_particles", spawns_particles);
        object.add("jei_icon_item", TFCRecipeHelpers.parseItemStack(jei_icon_item));

        data.put("knapping_type/"+path+".json", object);
    }

    /**
     * Set any int or float to -1 to disable
     * @param path
     * @param ingredient
     * @param type An optional string, either dynamic or dynamic_bowl. If this parameter is specified, none of the parameters below should be used, as the food values are specified via code.
     * @param hunger (Default 4). Defines how much hunger this food restores. The player’s full hunger bar is equal to 20.
     * @param saturation (Default 0.0). Defines how much saturation this food restores. Measured in the same units as hunger.
     * @param water (Default 0.0). Defines how much water this food restores. The player’s full water bar is equal to 100.
     * @param decay_modifier (Default 1.0). Defines how quickly this item decays. Higher values indicate faster decay, and thus shorter expiration times.
     * @param grain (Default 0.0). Defines how much Grain nutrient this food adds.
     * @param fruit (Default 0.0). Defines how much Fruit nutrient this food adds.
     * @param vegetables (Default 0.0). Defines how much Vegetables nutrient this food adds.
     * @param protein (Default 0.0). Defines how much Protein nutrient this food adds.
     * @param dairy (Default 0.0). Defines how much Dairy nutrient this food adds.
     */
    protected void food_items(String path, Ingredient ingredient, @Nullable String type, int hunger, float saturation, float water, float decay_modifier, float grain, float fruit, float vegetables, float protein, float dairy) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());

        if(type != null) {
            object.addProperty("type", type);
        }

        if(hunger != -1) {
            object.addProperty("hunger", hunger);
        }
        if(saturation != -1) {
            object.addProperty("saturation", saturation);
        }
        if(water != -1) {
            object.addProperty("water", water);
        }
        if(decay_modifier != -1) {
            object.addProperty("decay_modifier", decay_modifier);
        }
        if(grain != -1) {
            object.addProperty("grain", grain);
        }
        if(fruit != -1) {
            object.addProperty("fruit", fruit);
        }
        if(vegetables != -1) {
            object.addProperty("vegetables", vegetables);
        }
        if(protein != -1) {
            object.addProperty("protein", protein);
        }
        if(dairy != -1) {
            object.addProperty("dairy", dairy);
        }

        data.put("food_items/"+path+".json", object);
    }

    protected void fertilizers(String path, Ingredient ingredient, float nitrogen, float phosphorus, float potassium) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("nitrogen", nitrogen);
        object.addProperty("phosphorus", phosphorus);
        object.addProperty("potassium", potassium);

        data.put("fertilizers/"+path+".json", object);
    }

    protected void drinkables(String path, Ingredient ingredient, int thirst, int intoxication) {
        this.drinkables(path, ingredient, 0, thirst, intoxication);
    }

    protected void drinkables(String path, Ingredient ingredient, float consume_chance, int thirst, int intoxication) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());

        if(consume_chance > 1 || consume_chance < 0) {
            TerrafirmaDatagen.LOGGER.error("consume_chance in drinkables needs to be between 0 and 1");
            return;
        }

        if(thirst > 100 || thirst < 0) {
            TerrafirmaDatagen.LOGGER.error("thirst in thirst needs to be between 0 and 100");
            return;
        }

        object.addProperty("consume_chance", consume_chance);
        object.addProperty("thirst", thirst);
        object.addProperty("intoxication", intoxication);

        data.put("drinkables/"+path+".json", object);
    }

    protected void drinkables(String path, Ingredient ingredient, float consume_chance, int thirst, int intoxication, Drinkable.Effect... effects) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());

        if(consume_chance > 1 || consume_chance < 0) {
            TerrafirmaDatagen.LOGGER.error("consume_chance in drinkables needs to be between 0 and 1");
            return;
        }

        if(thirst > 100 || thirst < 0) {
            TerrafirmaDatagen.LOGGER.error("consume_chance in thirst needs to be between 0 and 100");
            return;
        }

        object.addProperty("consume_chance", consume_chance);
        object.addProperty("thirst", thirst);
        object.addProperty("intoxication", intoxication);

        if(effects.length > 0) {
            object.add("effects", TFCRecipeHelpers.drinkableEffectToJson(effects));
        }

        data.put("drinkables/"+path+".json", object);
    }

    protected void item_damage_resistances(String path, Ingredient ingredient, int piercing, int slashing, int crushing) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("piercing", piercing);
        object.addProperty("slashing", slashing);
        object.addProperty("crushing", crushing);

        data.put("item_damage_resistances/"+path+".json", object);
    }

    protected void entity_damage_resistances(String path, EntityType<?> type, int piercing, int slashing, int crushing) {
        JsonObject object = new JsonObject();

        object.addProperty("entity", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(type)).toString());
        object.addProperty("piercing", piercing);
        object.addProperty("slashing", slashing);
        object.addProperty("crushing", crushing);

        data.put("entity_damage_resistances/"+path+".json", object);
    }

    protected void fuel(String path, Ingredient ingredient, int duration, float temperature) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("duration", duration);
        object.addProperty("temperature", temperature);

        data.put("fuels/"+path+".json", object);
    }

    protected void item_size(String path, Ingredient ingredient, Size size, Weight weight) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("size", size.name);
        object.addProperty("weight", weight.name);

        data.put("item_sizes/"+path+".json", object);
    }

    protected void support(String path, Ingredient ingredient, int support_up, int support_down, int support_horizontal) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("support_up", support_up);
        object.addProperty("support_down", support_down);
        object.addProperty("support_horizontal", support_horizontal);

        data.put("supports/"+path+".json", object);
    }

    public abstract void addData();

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {

        this.addData();

        if(!this.data.isEmpty()) {
            return CompletableFuture.allOf(this.save(cache));
        }
        return CompletableFuture.allOf();
    }

    private CompletableFuture<?>[] save(CachedOutput cache) {

        List<CompletableFuture<?>> futures = new ArrayList<>();

        for(Map.Entry<String, JsonObject> entry : this.data.entrySet()) {
            String key = entry.getKey();
            JsonObject value = entry.getValue();

            Path target = this.output
                    .getOutputFolder(PackOutput.Target.DATA_PACK)
                    .resolve(this.modid)
                    .resolve("tfc")
                    .resolve(key);

            futures.add(DataProvider.saveStable(cache, value, target));
        }

        return futures.toArray(new CompletableFuture<?>[] {});
    }
}
