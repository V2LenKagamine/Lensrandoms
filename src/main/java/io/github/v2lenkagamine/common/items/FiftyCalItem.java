package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class FiftyCalItem extends GunItem{

	
	public static int maxAmmo = 4;
	public static int reloadCdTicks = 100;
	public static int shotCdTicks = 30;
	public static int range = 100;
	public static int maxDmg = Math.toIntExact(Math.round(30*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static int minDmg = Math.toIntExact(Math.round(25*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static RegistryObject<Item> ammoType = Items.SNIPER_ROUND;
	public static Boolean pierce = true;
	public static Boolean usesAmmo = true;
	
	public FiftyCalItem(Properties properties) {
		super(properties, maxAmmo);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
	 return fireWeapon(playerIn, worldIn, handIn, maxAmmo, reloadCdTicks, shotCdTicks, range, maxDmg, minDmg, ammoType, pierce, usesAmmo);
	}
	
}
