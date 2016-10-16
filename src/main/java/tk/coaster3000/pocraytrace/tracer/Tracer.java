package tk.coaster3000.pocraytrace.tracer;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

abstract class Tracer {

	public abstract TracerResult trace(World world, BlockPos start);
}
