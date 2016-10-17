package tk.coaster3000.pocraytrace.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import tk.coaster3000.pocraytrace.tracer.BoxTracer;
import tk.coaster3000.pocraytrace.tracer.TracerResult;
import tk.coaster3000.pocraytrace.tracer.Tracers;

public class DebugItem extends Item {
	public DebugItem(String name) {
		super();

		this.setUnlocalizedName(name).setCreativeTab(CreativeTabs.MISC).setRegistryName(name);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		item.clearCustomName();
		player.addChatComponentMessage(new TextComponentString("Cleared Tracer Mode"));
		return false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		Tracers tracer = Tracers.BOX;
		if (itemStackIn.hasDisplayName()) tracer = Tracers.valueOf(itemStackIn.getDisplayName());

		if (playerIn.isSneaking()) {
			int o = tracer.ordinal() + 1; //Get Ordinal and increment
			if (o >= Tracers.values().length) o = 0;
			tracer = Tracers.values()[o];
			if (worldIn.isRemote) playerIn.addChatMessage(new TextComponentString("Changed to tracer mode: " + tracer.toString()));
			return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn.setStackDisplayName(tracer.name()));
		} else {
			TracerResult trace = tracer.trace(worldIn, playerIn.getPosition());
			StringBuilder sb = new StringBuilder();

			if (trace instanceof BoxTracer.BoxTracerResult) {
				BoxTracer.BoxTracerResult btr = (BoxTracer.BoxTracerResult)trace;

				sb.append("Box Tracer Log").append('\n');
				sb.append("Player Block Pos: (X) ").append(playerIn.getPosition().getX()).append(" (Y) ").append(playerIn.getPosition().getY()).append(" (Z) ").append(playerIn.getPosition().getZ()).append("\n\n");
				BlockPos point = btr.getStartPoint();
				sb.append("Start X: ").append(point.getX()).append('\n').append("Start Y: ").append(point.getY()).append('\n').append("Start Z: ").append(point.getZ());
				sb.append('\n').append('\n');
				point = btr.getEndPoint();
				sb.append("End X: ").append(point.getX()).append('\n').append("End Y: ").append(point.getY()).append('\n').append("End Z: ").append(point.getZ());
			} else {
				sb.append("Tracer Not Implemented");
			}

			FMLCommonHandler.instance().getFMLLogger().info(sb.toString());
			if (worldIn.isRemote) playerIn.addChatComponentMessage(new TextComponentString(sb.toString()));


		}

		return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn.setStackDisplayName(tracer.name()));

	}
}
