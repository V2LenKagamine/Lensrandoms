package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.client.util.ModTags;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider{
	
	public ItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, Lensrandoms.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		//Add to BULLETS
		tag(ModTags.Items.BULLETS).add(Items.SIMPLE_BULLET.get())
		.add(Items.PISTOL_ROUND.get())
		.add(Items.SHOTGUN_SHELL.get())
		.add(Items.RIFLE_ROUND.get())
		.add(Items.SNIPER_ROUND.get())
		.add(Items.ARTILLERY_SHELL.get())
		.add(Items.HE_GRENADE_SHELL.get())
		.add(Items.ROCKET.get());
		
		//Add to GUNS
		tag(ModTags.Items.GUNS).add(Items.GUN_BLUNDERBUS.get())
		.add(Items.GUN_FIFTYCAL.get())
		.add(Items.GUN_FLINTLOCK.get())
		.add(Items.GUN_LASER_PISTOL.get())
		.add(Items.GUN_LENS_REVOLVER.get())
		.add(Items.GUN_REVOLVER.get());
		
		
		tag(ModTags.Items.CURIO_BELT).add(Items.TACTICAL_POUCHES.get());
	}
}
