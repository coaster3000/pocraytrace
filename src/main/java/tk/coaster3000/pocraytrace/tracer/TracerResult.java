package tk.coaster3000.pocraytrace.tracer;

import net.minecraft.util.math.BlockPos;

public abstract class TracerResult {
	public abstract boolean contains(BlockPos position);

	private static TracerResult nullTracer;

	/**
	 * Retrieves a null tracer.
	 * A tracer that will has no actual results.
	 * @return An empty trace result
	 */
	static TracerResult getNullTracer() {
		if (nullTracer == null) return nullTracer = new NullTracerResult();
		return nullTracer;
	}

	private static class NullTracerResult extends TracerResult {
		@Override
		public boolean contains(BlockPos position) {
			return false;
		}
	}
}
