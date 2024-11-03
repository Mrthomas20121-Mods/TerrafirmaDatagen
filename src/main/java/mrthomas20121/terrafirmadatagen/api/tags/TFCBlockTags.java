package mrthomas20121.terrafirmadatagen.api.tags;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * Add missing tag keys for tfc block tags
 */
public class TFCBlockTags extends TFCTags.Blocks {

    public static TagKey<Block> LAMPS = tfc("lamps");

    private static TagKey<Block> tfc(String name) {
        return TagKey.create(Registries.BLOCK, Helpers.identifier(name));
    }
}
