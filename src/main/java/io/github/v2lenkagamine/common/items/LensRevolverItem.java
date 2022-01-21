package io.github.v2lenkagamine.common.items;

import java.util.List;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import io.github.v2lenkagamine.core.items.LensItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class LensRevolverItem extends GunItem{

	
	public static int maxAmmo = 6;
	public static int reloadCdTicks = 30;
	public static int shotCdTicks = 5;
	public static int range = 32;
	public static int maxDmg = Math.toIntExact(Math.round(10*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static int minDmg = Math.toIntExact(Math.round(8*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static RegistryObject<Item> ammoType = LensItems.PISTOL_ROUND;
	public static Boolean pierce = true;
	public Boolean usesAmmo = true;
	public static List<? extends String> noAmmoList = LensRandomsConfig.BADMIN_NAMES.get();
	public LensRevolverItem(Properties properties) {
		super(properties, maxAmmo);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if (usesAmmo(noAmmoList, playerIn.getName().getString())) {usesAmmo=false;}
	 return fireWeapon(playerIn, worldIn, handIn, maxAmmo, reloadCdTicks, shotCdTicks, range, maxDmg, minDmg, ammoType, pierce,usesAmmo);
	}
}
