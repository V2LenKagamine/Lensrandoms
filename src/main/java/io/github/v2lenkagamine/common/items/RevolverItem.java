package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import io.github.v2lenkagamine.core.items.LensItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class RevolverItem extends GunItem{
	
	public static int maxAmmo = 6;
	public static int reloadCdTicks = 60;
	public static int shotCdTicks = 10;
	public static int range = 16;
	public static int maxDmg = Math.toIntExact(Math.round(5*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static int minDmg = Math.toIntExact(Math.round(2*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static RegistryObject<Item> ammoType = LensItems.PISTOL_ROUND;
	public static Boolean pierce = false;
	public static Boolean usesAmmo = true;
	
	public RevolverItem(Properties properties) {
		super(properties,maxAmmo);
		}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
	 return fireWeapon(playerIn, worldIn, handIn, maxAmmo, reloadCdTicks, shotCdTicks, range, maxDmg, minDmg, ammoType, pierce,usesAmmo);
	}
}