package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.Lensrandoms;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LensBlockTags extends BlockTagsProvider{
	
	public LensBlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn,Lensrandoms.MOD_ID,existingFileHelper);
	}
	@Override
	protected void addTags() {

	}
}
