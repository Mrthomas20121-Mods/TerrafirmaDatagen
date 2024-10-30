package mrthomas20121.terrafirmadatagen.datagen;

import mrthomas20121.terrafirmadatagen.TerrafirmaDatagen;
import mrthomas20121.terrafirmadatagen.api.IDye;
import net.dries007.tfc.common.blocks.HorizontalPipeBlock;
import net.dries007.tfc.common.blocks.StainedWattleBlock;
import net.dries007.tfc.util.registry.RegistryMetal;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class TFCBlockStateProvider extends BlockStateProvider {

    private final TFCBlockModelProvider blockModels;

    public TFCBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TerrafirmaDatagen.mod_id, exFileHelper);
        this.blockModels = new TFCBlockModelProvider(output, TerrafirmaDatagen.mod_id, exFileHelper);
    }

    @Override
    public BlockModelProvider models() {
        return this.blockModels;
    }

    @Override
    protected void registerStatesAndModels() {

    }

    public void carpetBlock(Block block, Block wool) {
        simpleBlock(block, models().carpet(name(block), key(wool)));
    }

    private ResourceLocation key(Block block) {
        ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
        return new ResourceLocation(key.getNamespace(), "block/" + key.getPath());
    }

    public void block(Block block) {
        this.simpleBlock(block, this.cubeAll(block));
    }

    public void bookshelf(Block block, ResourceLocation side, ResourceLocation end) {
        this.simpleBlock(block, this.cubeColumb(block, side, end));
    }

    private ModelFile cubeColumb(Block block, ResourceLocation side, ResourceLocation end) {
        return this.models().cubeColumn(this.name(block), side, end);
    }

    public ModelFile cubeAll(Block block) {
        return this.models().cubeAll(this.name(block), this.key(block));
    }


    public String name(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public ResourceLocation getName(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location;
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public void wallBlock(WallBlock block, ResourceLocation texture) {
        wallBlockInternalWithRenderType(block, key(block).toString(), texture, new ResourceLocation("cutout"));
    }

    private void wallBlockInternalWithRenderType(WallBlock block, String baseName, ResourceLocation texture, ResourceLocation renderType) {
        wallBlock(block, models().wallPost(baseName + "_post", texture).renderType(renderType),
                models().wallSide(baseName + "_side", texture).renderType(renderType),
                models().wallSideTall(baseName + "_side_tall", texture).renderType(renderType));
    }

    public void metalHorizontalSupport(Block block, RegistryMetal metal, String modid) {
        metalSupport(getName(block).toString(), block, new ResourceLocation("tfc:block/wood/support/horizontal"), new ResourceLocation("tfc:block/wood/support/connection"), metal, modid);
    }

    public void metalVerticalSupport(Block block, RegistryMetal metal, String modid) {
        metalSupport(getName(block).toString(), block, new ResourceLocation("tfc:block/wood/support/vertical"), new ResourceLocation("tfc:block/wood/support/connection"), metal, modid);
    }

    public void woodHorizontalSupport(Block block, RegistryWood wood, String modid) {
        woodSupport(getName(block).toString(), block, new ResourceLocation("tfc:block/wood/support/horizontal"), new ResourceLocation("tfc:block/wood/support/connection"), wood, modid);
    }

    public void woodVerticalSupport(Block block, RegistryWood wood, String modid) {
        woodSupport(getName(block).toString(), block, new ResourceLocation("tfc:block/wood/support/vertical"), new ResourceLocation("tfc:block/wood/support/connection"), wood, modid);
    }

    private void woodSupport(String name, Block block, ResourceLocation part, ResourceLocation connection, RegistryWood wood, String modid) {

        String texture = modid + ":block/metal/block/" + wood.getSerializedName();
        models().withExistingParent(name+"_inventory", new ResourceLocation("tfc:block/wood/support/inventory"))
                .texture("texture", texture)
                .renderType(new ResourceLocation("cutout"));
        support(block, models().withExistingParent(name, part)
                        .texture("texture", texture)
                        .texture("particle", texture)
                        .renderType(new ResourceLocation("cutout")),
                models().withExistingParent(name+"_connection", connection)
                        .texture("texture", texture)
                        .texture("particle", texture)
                        .renderType(new ResourceLocation("cutout")));
    }

    private void metalSupport(String name, Block block, ResourceLocation part, ResourceLocation connection, RegistryMetal metal, String modid) {

        String texture = modid + ":block/metal/block/" + metal.getSerializedName();
        models().withExistingParent(name+"_inventory", new ResourceLocation("tfc:block/wood/support/inventory"))
                .texture("texture", texture)
                .renderType(new ResourceLocation("cutout"));
        support(block, models().withExistingParent(name, part)
                        .texture("texture", texture)
                        .texture("particle", texture)
                        .renderType(new ResourceLocation("cutout")),
                models().withExistingParent(name+"_connection", connection)
                        .texture("texture", texture)
                        .texture("particle", texture)
                        .renderType(new ResourceLocation("cutout")));
    }

    protected void support(Block block, ModelFile part, ModelFile connection) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part().modelFile(part).addModel().end();
        builder.part().modelFile(connection).rotationY(270).addModel()
                .condition(HorizontalPipeBlock.NORTH, true).end();
        builder.part().modelFile(connection).addModel()
                .condition(HorizontalPipeBlock.EAST, true).end();
        builder.part().modelFile(connection).rotationY(90).addModel()
                .condition(HorizontalPipeBlock.SOUTH, true).end();
        builder.part().modelFile(connection).rotationY(180).addModel()
                .condition(HorizontalPipeBlock.WEST, true).end();
    }

    protected void wattle(StainedWattleBlock wattle, IDye dye) {
        String wattleName = getName(wattle).toString();
        String dyeName = dye.getSerializedName();
        this.wattle(wattle, models()
                .withExistingParent(wattleName, new ResourceLocation("tfc:block/cube_column_overlay"))
                .texture("all", dye.getModID()+":block/wattle/stained/"+ dyeName)
                .texture("particle", "tfc:block/wattle/wattle_sides")
                .texture("overlay", "tfc:block/wattle/wattle_sides")
                .texture("overlay_end", "tfc:block/wattle/end"),
                models().getExistingFile(new ResourceLocation("tfc:block/wattle/top")),
                models().getExistingFile(new ResourceLocation("tfc:block/wattle/bottom")),
                models().getExistingFile(new ResourceLocation("tfc:block/wattle/left")),
                models().getExistingFile(new ResourceLocation("tfc:block/wattle/right"))
        );
    }

    protected void wattle(StainedWattleBlock wattle, ModelFile part, ModelFile top, ModelFile bottom, ModelFile left, ModelFile right) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(wattle);

        builder.part().modelFile(part).addModel().end();
        builder.part().modelFile(top).addModel().condition(StainedWattleBlock.TOP, true).end();
        builder.part().modelFile(bottom).addModel().condition(StainedWattleBlock.BOTTOM, true).end();
        builder.part().modelFile(left).addModel().condition(StainedWattleBlock.LEFT, true).end();
        builder.part().modelFile(right).addModel().condition(StainedWattleBlock.RIGHT, true).end();
    }
}
