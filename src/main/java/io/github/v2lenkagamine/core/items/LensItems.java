package io.github.v2lenkagamine.core.items;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.items.BeeCase;
import io.github.v2lenkagamine.common.items.BlunderbusItem;
import io.github.v2lenkagamine.common.items.BrEEfcase;
import io.github.v2lenkagamine.common.items.FiftyCalItem;
import io.github.v2lenkagamine.common.items.FlintlockItem;
import io.github.v2lenkagamine.common.items.LaserPistol;
import io.github.v2lenkagamine.common.items.LensRevolverItem;
import io.github.v2lenkagamine.common.items.PouchItem;
import io.github.v2lenkagamine.common.items.RGB_Inator;
import io.github.v2lenkagamine.common.items.RevolverItem;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public final class LensItems {

	//Test Image
	public static final RegistryObject<Item> TEST_ITEM = RegistryHelper.ITEMS.register("test_item", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)
			.stacksTo(1)));
	//RGB-inator
	public static final RegistryObject<RGB_Inator> RGB_INATOR = RegistryHelper.ITEMS.register("rgb_inator", () -> new RGB_Inator(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	//Test Apple
	public static final RegistryObject<Item> TEST_APPLE = RegistryHelper.ITEMS.register("test_apple", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)
			.food(new FoodProperties.Builder().saturationMod(20f).nutrition(20).alwaysEat().build())));
	
	//Funni Items(Creative)
	public static final RegistryObject<BrEEfcase> BREEFCASE = RegistryHelper.ITEMS.register("breefcase", () -> new BrEEfcase(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	
	//Dungeon Loot
	public static final RegistryObject<BeeCase> BEECASE = RegistryHelper.ITEMS.register("beecase", () -> new BeeCase(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	
	
	//Bullets and ammo
	public static final RegistryObject<Item> SIMPLE_BULLET = RegistryHelper.ITEMS.register("simple_bullet", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> PISTOL_ROUND = RegistryHelper.ITEMS.register("pistol_round", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> RIFLE_ROUND = RegistryHelper.ITEMS.register("rifle_round", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> SHOTGUN_SHELL = RegistryHelper.ITEMS.register("shotgun_shell", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> ARTILLERY_SHELL = RegistryHelper.ITEMS.register("artillery_shell", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> HE_GRENADE_SHELL = RegistryHelper.ITEMS.register("he_grenade_shell", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> SNIPER_ROUND = RegistryHelper.ITEMS.register("sniper_round", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<Item> ROCKET = RegistryHelper.ITEMS.register("rocket", () -> new Item(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	
	//GUNS
	public static final RegistryObject<BlunderbusItem> GUN_BLUNDERBUS = RegistryHelper.ITEMS.register("gun_blunderbus", () -> new BlunderbusItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<RevolverItem> GUN_REVOLVER = RegistryHelper.ITEMS.register("gun_revolver", () -> new RevolverItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<FlintlockItem> GUN_FLINTLOCK = RegistryHelper.ITEMS.register("gun_flintlock", () -> new FlintlockItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<FiftyCalItem> GUN_FIFTYCAL = RegistryHelper.ITEMS.register("gun_fiftycal", () -> new FiftyCalItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	public static final RegistryObject<LensRevolverItem> GUN_LENS_REVOLVER = RegistryHelper.ITEMS.register("gun_lens_revolver", () -> new LensRevolverItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	
	//Energy Guns
	public static final RegistryObject<LaserPistol> GUN_LASER_PISTOL = RegistryHelper.ITEMS.register("gun_laser_pistol", () -> new LaserPistol(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	
	//Tactical Pouch
	public static final RegistryObject<PouchItem> TACTICAL_POUCHES = RegistryHelper.ITEMS.register("tactical_pouches", () -> new PouchItem(new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)));
	
	
	//No touchy
	public static void register() {}
}
