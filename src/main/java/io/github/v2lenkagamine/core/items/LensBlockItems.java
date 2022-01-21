package io.github.v2lenkagamine.core.items;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

public class LensBlockItems {
	//If there's a block, make an item!
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		RegistryHelper.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(Lensrandoms.LENS_RANDOMS)).setRegistryName(block.getRegistryName()));
		});
	}
}
