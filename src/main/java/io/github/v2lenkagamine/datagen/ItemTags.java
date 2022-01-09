package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.client.util.ModTags;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider{
	
	public ItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, Lensrandoms.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		
		tag(ModTags.Items.BULLETS).add(Items.SIMPLE_BULLET.get());
	}
}
