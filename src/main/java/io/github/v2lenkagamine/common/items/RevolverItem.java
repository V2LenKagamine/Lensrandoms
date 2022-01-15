package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RevolverItem extends GunItemReloadable{
	public RevolverItem(Properties properties) {
		super(properties);
	}
	public int minDmg = Math.toIntExact(Math.round(3*LensRandomsConfig.DAMAGE_MULTI.get()));
	public int maxDmg = Math.toIntExact(Math.round(6*LensRandomsConfig.DAMAGE_MULTI.get()));
	public int maxAmmo = 6;
	public int shotCdTicks = 10;
	public int reloadCd = 3;
	public int range = 16;
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		return fireReloadable(playerIn, worldIn, handIn, maxAmmo, reloadCd, shotCdTicks, range, minDmg, maxDmg,false);
	}
}