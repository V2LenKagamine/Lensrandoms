package io.github.v2lenkagamine.client.util;

import io.github.v2lenkagamine.Lensrandoms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

public class ModTags {
public static class Blocks {
	private static IOptionalNamedTag<Block> createTag (String name) {
		return BlockTags.createOptional(new ResourceLocation(Lensrandoms.MOD_ID,name));
	}
}


public static class Items {
	private static IOptionalNamedTag<Item> createTag (String name) {
		return ItemTags.createOptional(new ResourceLocation(Lensrandoms.MOD_ID,name));
	}
	
	public static final Tag.Named<Item> BULLETS = createTag("bullets");
	
}
}
