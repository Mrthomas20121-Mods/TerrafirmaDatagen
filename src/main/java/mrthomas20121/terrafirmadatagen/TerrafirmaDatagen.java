package mrthomas20121.terrafirmadatagen;

import net.minecraft.client.resources.model.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod(TerrafirmaDatagen.mod_id)
public class TerrafirmaDatagen {

	public static final String mod_id = "terrafirmadatagen";

	public static final Logger LOGGER = LogManager.getLogger();

	public TerrafirmaDatagen() {

		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
	}
}
