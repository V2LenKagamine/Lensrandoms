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

public class FlintlockItem extends GunItem{
	
	public static int maxAmmo = 1;
	public static int reloadCdTicks = 50;
	public static int shotCdTicks = 50;
	public static int range = 5;
	public static int maxDmg = Math.toIntExact(Math.round(5*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static int minDmg = Math.toIntExact(Math.round(2*LensRandomsConfig.DAMAGE_MULTI.get()));
	public static RegistryObject<Item> ammoType = Items.SIMPLE_BULLET;
	public static Boolean pierce = false;
	public static Boolean usesAmmo = true;
	
	public FlintlockItem(Properties properties) {
		super(properties,1);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		return fireWeapon(playerIn, worldIn, handIn, maxAmmo, reloadCdTicks, shotCdTicks, range, maxDmg, minDmg, ammoType, pierce, usesAmmo);
	}
}
