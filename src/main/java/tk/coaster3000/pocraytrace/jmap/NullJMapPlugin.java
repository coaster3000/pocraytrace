package tk.coaster3000.pocraytrace.jmap;

import net.minecraft.util.math.BlockPos;

public class NullJMapPlugin implements SoftJMapAPI {
	private static final String[] EMPTY = new String[0];

	@Override
	public boolean addPoint(String id, String groupName, String pointName, BlockPos position, int color, boolean persistent, int... dimensions) {
		return false;
	}

	@Override
	public boolean removePoint(String id) {
		return false;
	}

	@Override
	public boolean hasPoint(String id) {
		return false;
	}

	@Override
	public boolean isHooked() {
		return false;
	}

	@Override
	public String[] getPointIDs() {
		return EMPTY;
	}
}
