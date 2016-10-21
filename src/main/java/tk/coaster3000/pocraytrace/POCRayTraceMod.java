package tk.coaster3000.pocraytrace;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.coaster3000.pocraytrace.item.DebugItem;

@Mod(modid = POCRayTraceMod.MODID)
public class POCRayTraceMod {

	public static final String MODID = "pocraytrace";

	public static DebugItem debugItem;

	@EventHandler
	public void init(FMLPreInitializationEvent event) {
		GameRegistry.register(debugItem = new DebugItem(this, "debug_item"));
	}
}
