package io.github.v2lenkagamine.datagen;

import java.util.function.Supplier;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.items.Items;
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
	
	@Override
	protected void registerModels() {
		simpleItem(Items.RGB_INATOR);
		simpleTool(Items.GUN_BLUNDERBUS);
		simpleItem(Items.TEST_APPLE);
		simpleItem(Items.TEST_ITEM);
		simpleItem(Items.SIMPLE_BULLET);
	}
}
