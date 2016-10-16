package tk.coaster3000.pocraytrace.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class DebugItem extends Item {
	public DebugItem(String name) {
		super();

		this.setUnlocalizedName(name).setCreativeTab(CreativeTabs.MISC).setRegistryName(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) playerIn.addChatMessage(new TextComponentString("Hello Player"));

		return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
	}
}
