package tk.coaster3000.pocraytrace.tracer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BoxTracer extends Tracer {

	@Override
	public TracerResult trace(World world, BlockPos start) {
		if (!world.getBlockState(start).getBlock().isAir(world.getBlockState(start), world, start)) {
			return TracerResult.getNullTracer();
		}

		int north = 0, south = 0, east = 0, west = 0;
		BlockPos cursor = start.north();
		IBlockState state;
		while ((state = world.getBlockState(cursor = cursor.north())).getBlock().isAir(state, world, cursor)) {
			north++;
		}

		cursor = start.south();
		while ((state = world.getBlockState(cursor = cursor.south())).getBlock().isAir(state, world, cursor)) {
			south++;
		}

		cursor = start.east();
		while ((state = world.getBlockState(cursor = cursor.east())).getBlock().isAir(state, world, cursor)) {
			east++;
		}

		cursor = start.west();
		while ((state = world.getBlockState(cursor = cursor.west())).getBlock().isAir(state, world, cursor)) {
			west++;
		}
		BlockPos p1 = start.north(north).east(east), p2 = start.south(south).west(west);

		return new BoxTracerResult(p1, p2);
	}

	public static class BoxTracerResult extends TracerResult {

		final AxisAlignedBB boundingBox;
		BoxTracerResult(BlockPos p1, BlockPos p2) {
			boundingBox = new AxisAlignedBB(p1, p2);
		}

		public BlockPos getStartPoint() {
			return new BlockPos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
		}

		public BlockPos getEndPoint() {
			return new BlockPos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
		}

		public AxisAlignedBB getBoundingBox() {
			return boundingBox;
		}

		@Override
		public boolean contains(BlockPos position) {
			return boundingBox.isVecInside(new Vec3d(position.getX(), position.getY(), position.getZ()));
		}
	}
}
