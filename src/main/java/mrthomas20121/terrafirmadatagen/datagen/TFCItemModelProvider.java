package mrthomas20121.terrafirmadatagen.datagen;

import com.google.common.base.Preconditions;
import mrthomas20121.terrafirmadatagen.TerrafirmaDatagen;
import mrthomas20121.terrafirmadatagen.api.ExtendedRegistryMetal;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistryMetal;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public abstract class TFCItemModelProvider extends ItemModelProvider {

    public TFCItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TerrafirmaDatagen.mod_id, existingFileHelper);
    }

    /**
     * Create the model for a tfc metal item
     * @param metal metal
     * @param item metal item
     */
    protected void metalModel(ExtendedRegistryMetal metal, Metal.ItemType itemType, Item item) {
        String name = this.itemName(item);
        final ResourceLocation texture = this.texture(name);
        switch (itemType) {
            case JAVELIN -> {
                this.metalJavelin(metal, item);
            }
            case FISHING_ROD -> {
                this.metalFishingRod(metal, item);
            }
            case ROD -> {
                this.withExistingParent(name, "item/handheld_rod")
                        .texture("layer0", texture);
            }
            case SAW -> {
                this.withExistingParent(name, "tfc:item/handheld_flipped")
                        .texture("layer0", texture);
            }
            case PICKAXE, PROPICK, SHOVEL, SWORD, MACE, HAMMER, KNIFE, SCYTHE, SHEARS, CHISEL, HOE, AXE -> {
                this.withExistingParent(name, "item/handheld")
                        .texture("layer0", texture);
            }
            default -> {
                this.withExistingParent(name, "item/generated")
                        .texture("layer0", texture);
            }
        }
    }

    protected void metalFishingRod(ExtendedRegistryMetal metal, Item item) {
        this.withExistingParent(this.itemName(item)+"_cast", "minecraft:item/fishing_rod")
                .texture("layer0", "minecraft:item/fishing_rod_cast");

        this.withExistingParent(this.itemName(item), "minecraft:item/handheld_rod")
                .texture("layer0", this.modLoc("item/metal/fishing_rod/"+metal.getSerializedName()))
                .override().predicate(new ResourceLocation("tfc:cast"), 1)
                .model(this.getExistingFile(modLoc(this.itemName(item)+"_cast"))).end();
    }

    protected void metalJavelin(ExtendedRegistryMetal metal, Item item) {
        ItemModelBuilder inHand = withExistingParent(this.itemName(item)+"_in_hand", "minecraft:item/trident_in_hand")
                .texture("particle", this.modLoc("item/metal/javelin/"+metal.getSerializedName()));
        ItemModelBuilder throwing = withExistingParent(this.itemName(item)+"_throwing_base", "minecraft:item/trident_throwing")
                .texture("particle", this.modLoc("item/metal/javelin/"+metal.getSerializedName()));
        ItemModelBuilder gui = withExistingParent(this.itemName(item)+"_gui", "minecraft:item/generated")
                .texture("layer0", this.modLoc("item/metal/javelin/"+metal.getSerializedName()));

        getBuilder(this.itemName(item))
                .guiLight(BlockModel.GuiLight.FRONT)
                .override()
                .predicate(new ResourceLocation("tfc:throwing"), 1)
                .model(this.getExistingFile(modLoc(this.itemName(item)+"_throwing")))
                .end()
                .customLoader((modelBuilder, existingFileHelper) ->
                SeparateTransformsModelBuilder
                        .begin(inHand, existingFileHelper)
                        .base(inHand)
                        .perspective(ItemDisplayContext.NONE, gui)
                        .perspective(ItemDisplayContext.FIXED, gui)
                        .perspective(ItemDisplayContext.GROUND, gui)
                        .perspective(ItemDisplayContext.GUI, gui)
        ).end();

        getBuilder(this.itemName(item)+"_throwing")
                .guiLight(BlockModel.GuiLight.FRONT)
                .customLoader((modelBuilder, existingFileHelper) ->
                        SeparateTransformsModelBuilder
                                .begin(throwing, existingFileHelper)
                                .base(throwing)
                                .perspective(ItemDisplayContext.NONE, gui)
                                .perspective(ItemDisplayContext.FIXED, gui)
                                .perspective(ItemDisplayContext.GROUND, gui)
                                .perspective(ItemDisplayContext.GUI, gui)
                ).end();
    }

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
    }

    public void itemBlock(Block block, Block block2) {
        this.withExistingParent(this.blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + this.blockName(block2)));
    }

    public void bedItem(BedBlock bed, Block wool) {
        this.withExistingParent(this.blockName(bed), mcLoc("item/template_bed"))
                .texture("particle", modLoc("block/" + this.blockName(wool)));
    }

    public void metalSupport(RegistryMetal metal) {
        this.withExistingParent("metal/support/"+metal.getSerializedName(), this.modid+":block/metal/horizontal_support/"+metal.getSerializedName()+"_inventory");
    }

    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.textureTFC(this.blockName(baseBlock)));
    }

    public void itemWallBlockAFC(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.textureAFC(this.blockName(baseBlock)));
    }

    public void itemWall(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(this.blockName(baseBlock)));
    }

    public void itemWall(Block block, String baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(baseBlock));
    }

    public void itemBlock(Block block) {
        this.withExistingParent(this.blockName(block), modLoc("block/"+this.blockName(block)));
    }

    public ItemModelBuilder getBuilder(String path) {
        Preconditions.checkNotNull(path, "Path must not be null");
        ResourceLocation outputLoc = extendWithFolder(path.contains(":") ? new ResourceLocation(path) : new ResourceLocation(modid, path));
        this.existingFileHelper.trackGenerated(outputLoc, MODEL);
        return generatedModels.computeIfAbsent(outputLoc, factory);
    }

    private ResourceLocation extendWithFolder(ResourceLocation rl) {
        return new ResourceLocation(rl.getNamespace(), folder + "/" + rl.getPath());
    }

    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    protected ResourceLocation textureTFC(String name) {
        return new ResourceLocation("tfc","block/" + name);
    }

    protected ResourceLocation textureAFC(String name) {
        return new ResourceLocation("afc","block/" + name);
    }

    public String blockName(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public String itemName(Item item) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(item);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown item: " + item.toString());
        }
    }
}
