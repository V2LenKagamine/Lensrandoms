package io.github.v2lenkagamine.datagen;

import java.util.function.Supplier;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.items.LensItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LensRandomsItemModelProvider extends ItemModelProvider{
	public LensRandomsItemModelProvider(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator,Lensrandoms.MOD_ID,exFileHelper);
	}

	public void simpleItem(Supplier<? extends Item> itemSupplier) {
		ResourceLocation location = itemSupplier.get().getRegistryName();
		this.getBuilder(location.getPath())
		.parent(new ModelFile.UncheckedModelFile("item/generated"))
		.texture("layer0", new ResourceLocation(location.getNamespace(), ITEM_FOLDER + "/" + location.getPath()));
	}
	
	
	public void simpleTool(Supplier<? extends Item> itemSupplier) {
		ResourceLocation location = itemSupplier.get().getRegistryName();
		this.getBuilder(location.getPath())
		.parent(new ModelFile.UncheckedModelFile("item/handheld"))
		.texture("layer0", new ResourceLocation(location.getNamespace(), ITEM_FOLDER + "/" + location.getPath()));
	}
	/*
	public void complexTool(Supplier<? extends Item>itemSupplier) {
		ResourceLocation location = itemSupplier.get().getRegistryName();
		this.getBuilder(location.getPath())
		.parent(new ModelFile.UncheckedModelFile("item/handheld"))
		.texture("layer0", new ResourceLocation(location.getNamespace(), ITEM_FOLDER + "/" + location.getPath()));
	}
	*/
	@Override
	protected void registerModels() {
		
		//Items
		simpleItem(LensItems.RGB_INATOR);
		simpleItem(LensItems.TEST_APPLE);
		simpleItem(LensItems.TEST_ITEM);
		simpleItem(LensItems.SIMPLE_BULLET);
		simpleItem(LensItems.TACTICAL_POUCHES);
		simpleItem(LensItems.PISTOL_ROUND);
		simpleItem(LensItems.RIFLE_ROUND);
		simpleItem(LensItems.SHOTGUN_SHELL);
		simpleItem(LensItems.BREEFCASE);
		simpleItem(LensItems.BEECASE);
		simpleItem(LensItems.HE_GRENADE_SHELL);
		simpleItem(LensItems.ROCKET);
		simpleItem(LensItems.SNIPER_ROUND);
		simpleItem(LensItems.ARTILLERY_SHELL);
		
		//Tools
		simpleTool(LensItems.GUN_BLUNDERBUS);
		simpleTool(LensItems.GUN_FLINTLOCK);
		simpleTool(LensItems.GUN_REVOLVER);
		simpleTool(LensItems.GUN_FIFTYCAL);
		simpleTool(LensItems.GUN_LENS_REVOLVER);
		simpleTool(LensItems.GUN_LASER_PISTOL);
	}
}
