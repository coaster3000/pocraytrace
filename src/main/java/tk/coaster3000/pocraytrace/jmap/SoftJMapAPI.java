package tk.coaster3000.pocraytrace.jmap;

import net.minecraft.util.math.BlockPos;

interface SoftJMapAPI {

	/**
	 * Add a custom waypoint to JouryneyMap
	 * @param id The id of the waypoint.
	 * @param groupName The group the point resides in.
	 * @param pointName The points location
	 * @param position BlockPos (x,y,z) of waypoints location.
	 * @param color RGB integer encoded value for color.
	 * @param persistent Flag to allow saving of waypoint. True to save, false otherwise.
	 * @param dimensions The dimensions the waypoint is within
	 * @return true if successful, false otherwise.
	 */
	boolean addPoint(String id, String groupName, String pointName, BlockPos position, int color, boolean persistent, int...dimensions);

	/**
	 * Removes a custom waypoint
	 * @param id The id to remove
	 * @return true if successful, false otherwise.
	 */
	boolean removePoint(String id);

	/**
	 * Tells if a waypoint exists
	 * @param id The id of waypoint
	 * @return true if exists, false if failed or non existent
	 */
	boolean hasPoint(String id);

	/**
	 * Tells whether or not the implementation is hooked into JourneyMap or not.
	 * @return True if integrated, false otherwise.
	 */
	boolean isHooked();

	/**
	 * Retrieves an array of waypoints the mod manages.
	 * @return Array of waypoint ID's
	 */
	String[] getPointIDs();

}
