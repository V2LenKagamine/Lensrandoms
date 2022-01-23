package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.items.LensItems;
import io.github.v2lenkagamine.core.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LensItemTags extends ItemTagsProvider{
	
	public LensItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, Lensrandoms.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		//Add to BULLETS
		tag(ModTags.Items.BULLETS).add(LensItems.SIMPLE_BULLET.get())
		.add(LensItems.PISTOL_ROUND.get())
		.add(LensItems.SHOTGUN_SHELL.get())
		.add(LensItems.RIFLE_ROUND.get())
		.add(LensItems.SNIPER_ROUND.get())
		.add(LensItems.ARTILLERY_SHELL.get())
		.add(LensItems.HE_GRENADE_SHELL.get())
		.add(LensItems.ROCKET.get());
		
		//Add to GUNS
		tag(ModTags.Items.GUNS).add(LensItems.GUN_BLUNDERBUS.get())
		.add(LensItems.GUN_FIFTYCAL.get())
		.add(LensItems.GUN_FLINTLOCK.get())
		.add(LensItems.GUN_LASER_PISTOL.get())
		.add(LensItems.GUN_LENS_REVOLVER.get())
		.add(LensItems.GUN_REVOLVER.get());
		
		//Add to CURIOS belt slot
		tag(ModTags.Items.CURIO_BELT).add(LensItems.TACTICAL_POUCHES.get());
		
		//Add to FORGE LEAD INGOTS
		tag(ModTags.Items.FORGELEAD);
	}
}
