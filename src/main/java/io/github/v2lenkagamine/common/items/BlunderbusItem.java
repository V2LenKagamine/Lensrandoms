package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.gunTimer.CapabilityGunTimer;
import io.github.v2lenkagamine.common.capabilities.gunTimer.IGunTimer;
import io.github.v2lenkagamine.core.util.WeaponFire;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class BlunderbusItem extends GunItem{

	public BlunderbusItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		LazyOptional<IGunTimer> capability = item.getCapability(CapabilityGunTimer.GUN_TIMER_CAPABILITY);
		if (capability.isPresent())
			{
			
			WeaponFire.fireShotgun(playerIn, 5, 8);
			
			}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
