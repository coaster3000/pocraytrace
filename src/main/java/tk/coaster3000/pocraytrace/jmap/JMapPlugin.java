package tk.coaster3000.pocraytrace.jmap;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Maps;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.display.Displayable;
import journeymap.client.api.display.ModWaypoint;
import journeymap.client.api.event.ClientEvent;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.coaster3000.pocraytrace.POCRayTraceMod;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
@journeymap.client.api.ClientPlugin
public class JMapPlugin implements IClientPlugin, SoftJMapAPI {

	private final static Logger LOGGER = LogManager.getLogger("POCRayTraceMod.JMapPlugin");

	// API reference
	private IClientAPI jmAPI = null;

	private Map<String, ModWaypoint> waypoints = Maps.newHashMap();

	@Override
	public void initialize(IClientAPI jmClientApi) {
		jmAPI = jmClientApi; // Our API hook into JMap
	}

	@Override
	public String getModId() {
		return POCRayTraceMod.MODID;
	}

	@Override
	public void onEvent(ClientEvent event) {
		// Not Needed
	}

	public IClientAPI getJmAPI() {
		return jmAPI;
	}

	@Override
	public boolean addPoint(String id, String groupName, String pointName, BlockPos position, int color, boolean persistent, int... dimensions) {
		ModWaypoint point = new ModWaypoint(POCRayTraceMod.MODID, id, groupName, pointName, position, null, color, persistent, dimensions);

		waypoints.put(id, point); //Store the point for tracking.

		try {
			jmAPI.show(point);
			return true;
		} catch (Exception e) {
			log("An error had occurred during the addition of waypoints within journeymap!", e);
			return false;
		}
	}

	@Override
	public boolean removePoint(String id) {
		if (hasPoint(id))
			jmAPI.remove(waypoints.remove(id)); // Removes from tracking and jmap.
		else
			return false;
		return true;
	}

	@Override
	public boolean hasPoint(String id) {
		return waypoints.containsKey(id); // Return whether we have that waypoint.
	}

	@Override
	public boolean isHooked() {
		return true; // This is a valid hook!
	}

	@Override
	public String[] getPointIDs() {
		return waypoints.keySet().toArray(new String[waypoints.size()]); //Create an array and return points we track within array.
	}

	private static void log(String msg) {
		LOGGER.log(Level.INFO, msg);
	}
	private static void log(String msg, Throwable thrown) {
		LOGGER.log(Level.ERROR, msg, thrown);
	}
}
