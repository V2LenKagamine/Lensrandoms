package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.gunTimer.CapabilityGunTimer;
import io.github.v2lenkagamine.common.capabilities.gunTimer.GunTimerData;
import io.github.v2lenkagamine.core.items.Items;
import io.github.v2lenkagamine.core.util.ItemUtil;
import io.github.v2lenkagamine.core.util.WeaponFire;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

public class BlunderbusItem extends GunItem{

	public BlunderbusItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack item = playerIn.getItemInHand(handIn);
		LazyOptional<GunTimerData> capability = item.getCapability(CapabilityGunTimer.GUN_TIMER_CAPABILITY);
		if (capability.isPresent())
			{
				if (capability.resolve().get().getTimerTicks() < 1) 
				{
					if (ItemUtil.lookInPouches(playerIn, new ItemStack (Items.SIMPLE_BULLET.get()), true, 1)) {
						WeaponFire.firePierceAll(playerIn, 5, 4, 10);
						capability.resolve().get().setTimer(5);
					}
					else if (ItemUtil.getPlayerItem(playerIn, new ItemStack (Items.SIMPLE_BULLET.get()), true, 1)) {
						WeaponFire.firePierceAll(playerIn, 5, 4, 10);
						capability.resolve().get().setTimer(5);
					}
				}
			}
		return super.use(worldIn, playerIn, handIn);
	}
}