package io.github.v2lenkagamine.datagen;

import java.util.function.Supplier;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.init.blocks.Blocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LensRandomsBlockStateProvider extends BlockStateProvider {
	public LensRandomsBlockStateProvider(DataGenerator gen,ExistingFileHelper exFileHelper) {
		super(gen, Lensrandoms.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(Blocks.RGBLOCK.get());
		simpleBlock(Blocks.POWER_HOLE.get());
	}
	public void simpleBlock(Supplier<? extends Block> blockSupplier) {
		simpleBlock(blockSupplier.get());
	}
	@Override
	public void simpleBlock(Block block, ModelFile model) {
		super.simpleBlock(block,model);
		this.simpleBlockItem(block, model);
	}	
	
}
