package mrthomas20121.terrafirmadatagen.api;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;

import java.util.List;
import java.util.Set;

public class LootGen {

    public static LootTableProvider create(PackOutput output, LootTableProvider.SubProviderEntry ...entries) {
        return new LootTableProvider(output, Set.of(), List.of(
                entries
        ));
    }
}
