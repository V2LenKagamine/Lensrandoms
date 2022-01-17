package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LaserPistol extends EnergyGunItem{

	
	public static int maxEnergy = 12000;
	public static int shotCdTicks = 10;
	public static int range = 16;	
	public static int maxDmg = Math.toIntExact(Math.round(5*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static int minDmg = Math.toIntExact(Math.round(4*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static int energyPerShot = 200;
	public static Boolean pierce = true;
	public static Boolean usesAmmo = true;
	
	public LaserPistol(Properties properties) {
		super(properties, maxEnergy);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		return fireEWeapon(playerIn, worldIn, handIn, energyPerShot, shotCdTicks, range, minDmg, maxDmg, pierce, usesAmmo);
	}
	
}
