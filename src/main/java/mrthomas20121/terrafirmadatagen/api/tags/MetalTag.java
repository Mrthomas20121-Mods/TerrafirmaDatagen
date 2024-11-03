package mrthomas20121.terrafirmadatagen.api.tags;

import mrthomas20121.terrafirmadatagen.api.ExtendedRegistryMetal;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MetalTag {

    private final TagKey<Item> ingot;
    private final TagKey<Item> double_ingot;
    private final TagKey<Item> sheets;

    public MetalTag(ExtendedRegistryMetal metal) {
        this.ingot = tag("ingots/"+metal.getSerializedName());
        this.double_ingot = tag("double_ingots/"+metal.getSerializedName());
        this.sheets = tag("sheets/"+metal.getSerializedName());
    }

    public TagKey<Item> getIngot() {
        return ingot;
    }

    public TagKey<Item> getDoubleIngot() {
        return double_ingot;
    }

    public TagKey<Item> getSheets() {
        return sheets;
    }

    private TagKey<Item> tag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation("forge", name));
    }
}
