package tk.coaster3000.pocraytrace.tracer;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public enum Tracers {
	BOX(new BoxTracer());

	Tracer tracer;
	Tracers(Tracer tracer) {
		this.tracer = tracer;
	}

	public TracerResult trace(World world, BlockPos start) {
		return this.tracer.trace(world, start);
	}
}
