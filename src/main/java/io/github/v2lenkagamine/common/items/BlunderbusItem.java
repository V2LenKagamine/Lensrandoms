package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlunderbusItem extends GunItem{

	public BlunderbusItem(Properties properties) {
		super(properties);
	}
	
	public int minDmg = Math.toIntExact(Math.round(4*LensRandomsConfig.DAMAGE_MULTI.get()));
	public int maxDmg = Math.toIntExact(Math.round(10*LensRandomsConfig.DAMAGE_MULTI.get()));
	public int cooldownTicks = 100;
	public int range = 5;
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
	return fireWeapon(playerIn, worldIn, handIn, cooldownTicks, range, minDmg, maxDmg, true);	
		

	}	
}