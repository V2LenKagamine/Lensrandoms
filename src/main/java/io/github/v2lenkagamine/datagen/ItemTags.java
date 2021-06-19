package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.Lensrandoms;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider{
	
	public ItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, Lensrandoms.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerTags() {
		
		
	}
}
