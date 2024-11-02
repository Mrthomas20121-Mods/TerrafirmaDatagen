package mrthomas20121.terrafirmadatagen.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.dries007.tfc.common.capabilities.food.FoodData;
import net.dries007.tfc.common.capabilities.food.FoodTrait;
import net.dries007.tfc.common.recipes.outputs.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class TFCRecipeHelpers {

    private static final Map<Class<? extends ItemStackModifier>, Function<ItemStackModifier, JsonObject>> modifiers = new HashMap<>();

    /**
     * register custom modifiers to the datagen
     * @param modClass modifier class
     * @param func modifier to JsonObject function, used to turn the modifier to json
     * @param <T> modifier
     */
    @SuppressWarnings("unchecked")
    public static <T extends ItemStackModifier> void registerModifier(Class<T> modClass, Function<T, JsonObject> func) {
        modifiers.put(modClass, (Function<ItemStackModifier, JsonObject>) func);
    }

    /**
     * register custom modifiers to the datagen
     * @param modClass modifier class
     * @param name name of the modifier
     * @param <T> modifier
     */
    public static <T extends ItemStackModifier> void registerModifier(Class<T> modClass, String name) {
        registerModifier(modClass, (mod) -> {
            JsonObject obj = new JsonObject();

            obj.addProperty("type", name);

            return obj;
        });
    }

    static {
        registerModifier(CopyFoodModifier.class, "tfc:copy_food");
        registerModifier(AddHeatModifier.class, heat -> {
            JsonObject obj = new JsonObject();

            obj.addProperty("type", "tfc:add_heat");
            obj.addProperty("temperature", heat.temperature());

            return obj;
        });
        registerModifier(AddBaitToRodModifier.class, "tfc:add_bait_to_rod");
        registerModifier(AddGlassModifier.class, "tfc:add_glass");
        registerModifier(AddPowderModifier.class, "tfc:add_powder");
        registerModifier(AddRemoveTraitModifier.class, add -> {
            JsonObject obj = new JsonObject();

            if (add.add()) {
                obj.addProperty("type", "tfc:add_trait");
            } else {
                obj.addProperty("type", "tfc:remove_trait");
            }

            obj.addProperty("trait", FoodTrait.getId(add.trait()).toString());

            return obj;
        });

        registerModifier(CopyFoodModifier.class, "tfc:copy_food");
        registerModifier(CopyForgingBonusModifier.class, "tfc:copy_forging_bonus");
        registerModifier(CopyHeatModifier.class, "tfc:copy_heat");
        registerModifier(CopyInputModifier.class, "tfc:copy_input");
        registerModifier(CopyOldestFoodModifier.class, "tfc:copy_oldest_food");
        registerModifier(EmptyBowlModifier.class, "tfc:empty_bowl");
        registerModifier(MealModifier.class, meal -> {
            JsonObject obj = new JsonObject();

            obj.addProperty("type", "tfc:meal");

            if(!meal.baseFood().equals(FoodData.EMPTY)) {
                obj.add("food", parseFood(meal.baseFood()));
            }

            obj.add("portions", parsePortions(meal.portions()));

            return obj;
        });

        registerModifier(ResetFoodModifier.class, "tfc:reset_food");
    }

    private static JsonElement parsePortions(List<MealModifier.MealPortion> portions) {
        JsonArray obj = new JsonArray();

        portions.stream().map(p -> {
            JsonObject j = new JsonObject();
            Ingredient i = p.ingredient();
            if(i != null && !i.isEmpty()) {
                j.add("ingredient", i.toJson());
            }
            if(p.nutrientModifier() > 0) {
                j.addProperty("nutrient_modifier", p.nutrientModifier());
            }
            if(p.saturationModifier() > 0) {
                j.addProperty("saturation_modifier", p.saturationModifier());
            }
            if(p.waterModifier() > 0) {
                j.addProperty("water_modifier", p.waterModifier());
            }

            return j;
        }).forEach(obj::add);

        return obj;
    }

    public static JsonElement parseFood(FoodData food) {
        JsonObject obj = new JsonObject();

        if(food.hunger() > 0) {
            obj.addProperty("hunger", food.hunger());
        }
        if(food.water() > 0) {
            obj.addProperty("water", food.water());
        }
        if(food.saturation() > 0) {
            obj.addProperty("saturation", food.saturation());
        }
        if(food.grain() > 0) {
            obj.addProperty("grain", food.grain());
        }
        if(food.fruit() > 0) {
            obj.addProperty("fruit", food.fruit());
        }
        if(food.vegetables() > 0) {
            obj.addProperty("vegetables", food.vegetables());
        }
        if(food.protein() > 0) {
            obj.addProperty("protein", food.protein());
        }
        if(food.dairy() > 0) {
            obj.addProperty("dairy", food.dairy());
        }
        if(food.decayModifier() > 0) {
            obj.addProperty("decay_modifier", food.decayModifier());
        }

        return obj;
    }

    public static JsonElement parseFluidStackIngredient(Fluid fluid, int amount) {
        JsonObject obj = new JsonObject();
        String fluidName = Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)).toString();

        obj.addProperty("ingredient", fluidName);

        obj.addProperty("amount", amount);

        return obj;
    }

    public static JsonElement parseFluidStackIngredient(TagKey<Fluid> fluid, int amount) {
        JsonObject obj = new JsonObject();
        JsonObject ingredient = new JsonObject();
        String fluidName = fluid.location().toString();

        ingredient.addProperty("tag", fluidName);
        obj.add("ingredient", ingredient);

        obj.addProperty("amount", amount);

        return obj;
    }

    public static JsonElement parseFluidStack(FluidStack stack) {
        JsonObject fluidStack = new JsonObject();

        String fluidName = Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(stack.getFluid())).toString();

        fluidStack.addProperty("fluid", fluidName);
        fluidStack.addProperty("amount", stack.getAmount());

        return fluidStack;
    }

    public static JsonElement parseItemStack(ItemStack stack) {
        JsonObject object = new JsonObject();

        object.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).toString());
        object.addProperty("count", stack.getCount());

        return object;
    }

    public static JsonElement parseItemStackIngredient(Ingredient ingredient, int count) {
        JsonObject object = new JsonObject();

        if(count > 1) {
            object.addProperty("count", count);
        }
        object.add("ingredient", ingredient.toJson());

        return object;
    }

    public static JsonElement parseBlockState(BlockState blockState) {
        return new JsonPrimitive(blockState.toString());
    }

    public static JsonElement parseBlockIngredient(TagKey<Block> tag) {
        JsonObject object = new JsonObject();

        object.addProperty("tag", tag.location().toString());

        return object;
    }

    public static JsonElement parseBlockIngredient(Block... blocks) {
        if(blocks.length == 1) {
            Block block = blocks[0];
            return new JsonPrimitive(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).toString());
        }
        else {
            JsonArray items = new JsonArray();
            Stream.of(blocks)
                    .map(ForgeRegistries.BLOCKS::getKey)
                    .map(Objects::requireNonNull)
                    .map(ResourceLocation::toString)
                    .forEach(s -> {
                        JsonObject object = new JsonObject();
                        object.addProperty("block", s);
                        items.add(object);
                    });
            return items;
        }
    }

    /**
     * turn ItemStackProvider into json
     * @param provider
     * @return
     */
    public static JsonElement parseProvider(ItemStackProvider provider) {
        JsonObject obj = new JsonObject();

        ItemStack stack = provider.stack().get();

        JsonObject item = new JsonObject();

        if(!stack.isEmpty()) {
            item.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).toString());

            if(stack.getCount() > 0) {
                obj.addProperty("count", stack.getCount());
            }

            if(provider.modifiers().length == 0) {
                return item;
            }

            obj.add("stack", item);
        }

        JsonArray mods = new JsonArray();
        Arrays.stream(provider.modifiers()).map(modifier -> modifiers.get(modifier.getClass()).apply(modifier)).forEach(mods::add);

        obj.add("modifiers", mods);

        return obj;
    }
}
