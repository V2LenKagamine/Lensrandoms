package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.Lensrandoms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {

	public static final DeferredRegister<Item> ITEMS  = DeferredRegister.create(ForgeRegistries.ITEMS,Lensrandoms.MOD_ID);
	
	public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	
}
