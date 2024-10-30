package mrthomas20121.terrafirmadatagen.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public abstract class TFCItemTagsProvider extends ItemTagsProvider {

    public <T extends TFCBlockTagsProvider> TFCItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, T blockTags, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, blockTags.contentsGetter(), modId, existingFileHelper);
    }
}
