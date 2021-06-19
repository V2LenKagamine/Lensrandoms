package io.github.v2lenkagamine.core.items;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.items.BlunderbusItem;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class Items {

	//Test Image
	public static final RegistryObject<Item> TEST_ITEM = RegistryHelper.ITEMS.register("test_item", () -> new Item(new Item.Properties().group(Lensrandoms.LENS_RANDOMS)
			.maxStackSize(1)));
	//RGB-inator
	public static final RegistryObject<RGB_Inator> RGB_INATOR = RegistryHelper.ITEMS.register("rgb_inator", () -> new RGB_Inator(new Item.Properties().group(Lensrandoms.LENS_RANDOMS)));
	//Test Apple
	public static final RegistryObject<Item> TEST_APPLE = RegistryHelper.ITEMS.register("test_apple", () -> new Item(new Item.Properties().group(Lensrandoms.LENS_RANDOMS)
			.food(new Food.Builder().saturation(20f).hunger(20).setAlwaysEdible().build())));
	//GUN
	public static final RegistryObject<BlunderbusItem> GUN_BLUNDERBUS = RegistryHelper.ITEMS.register("gun_blunderbus", () -> new BlunderbusItem(new Item.Properties().group(Lensrandoms.LENS_RANDOMS)));
	
	public static void register() {}
}
