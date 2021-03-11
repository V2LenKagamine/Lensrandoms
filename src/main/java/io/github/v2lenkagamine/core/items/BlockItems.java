package io.github.v2lenkagamine.core.items;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;

public class BlockItems {
	//If there's a block, make an item!
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		RegistryHelper.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().group(Lensrandoms.LENS_RANDOMS)).setRegistryName(block.getRegistryName()));
		});
	}
}
