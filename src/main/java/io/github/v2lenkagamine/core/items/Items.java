package io.github.v2lenkagamine.core.items;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.items.BlunderbusItem;
import io.github.v2lenkagamine.common.items.FlintlockItem;
import io.github.v2lenkagamine.common.items.PouchItem;
import io.github.v2lenkagamine.common.items.RGB_Inator;
import io.github.v2lenkagamine.common.items.RevolverItem;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public final class Items {

	//Test Image
	public static final RegistryObject<Item> TEST_ITEM = RegistryHelper.ITEMS.register("test_item", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)
			.stacksTo(1)));
	//RGB-inator
	public static final RegistryObject<RGB_Inator> RGB_INATOR = RegistryHelper.ITEMS.register("rgb_inator", () -> new RGB_Inator(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	//Test Apple
	public static final RegistryObject<Item> TEST_APPLE = RegistryHelper.ITEMS.register("test_apple", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)
			.food(new FoodProperties.Builder().saturationMod(20f).nutrition(20).alwaysEat().build())));
	
	//Bullets
	public static final RegistryObject<Item> SIMPLE_BULLET = RegistryHelper.ITEMS.register("simple_bullet", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> PISTOL_ROUND = RegistryHelper.ITEMS.register("pistol_round", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> RIFLE_ROUND = RegistryHelper.ITEMS.register("rifle_round", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> SHOTGUN_SHELL = RegistryHelper.ITEMS.register("shotgun_shell", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	//Tactical Pouch
	public static final RegistryObject<PouchItem> TACTICAL_POUCHES = RegistryHelper.ITEMS.register("tactical_pouches", () -> new PouchItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	
	//GUNS
	public static final RegistryObject<BlunderbusItem> GUN_BLUNDERBUS = RegistryHelper.ITEMS.register("gun_blunderbus", () -> new BlunderbusItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<RevolverItem> GUN_REVOLVER = RegistryHelper.ITEMS.register("gun_revolver", () -> new RevolverItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<FlintlockItem> GUN_FLINTLOCK = RegistryHelper.ITEMS.register("gun_flintlock", () -> new FlintlockItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	
	
	//No touchy
	public static void register() {}
}
