package tk.coaster3000.pocraytrace.tracer;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

class BoxTracer extends Tracer {

	@Override
	public TracerResult trace(World world, BlockPos start) {
//		if (!world.getBlockState(start).getBlock().isAir(world.getBlockState(start), world, start)) {
			return TracerResult.getNullTracer();
//		}


	}
}
