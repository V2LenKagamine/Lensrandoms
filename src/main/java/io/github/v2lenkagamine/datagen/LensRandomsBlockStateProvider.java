package io.github.v2lenkagamine.datagen;

import java.util.function.Supplier;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.init.blocks.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class LensRandomsBlockStateProvider extends BlockStateProvider {
	public LensRandomsBlockStateProvider(DataGenerator gen,ExistingFileHelper exFileHelper) {
		super(gen, Lensrandoms.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(Blocks.POWER_HOLE.get());
		simpleBlock(Blocks.RGBLOCK.get());
		simpleSlab(Blocks.RGBLOCK_SLAB.get(),model(Blocks.RGBLOCK.getId()));
		simpleStairs(Blocks.RGBLOCK_STAIR.get(),model(Blocks.RGBLOCK.getId()));
		simpleBlock(Blocks.RGBLOCK_BRICK.get());
		simpleSlab(Blocks.RGBLOCK_BRICK_SLAB.get(),model(Blocks.RGBLOCK_BRICK.getId()));
		simpleStairs(Blocks.RGBLOCK_BRICK_STAIR.get(),model(Blocks.RGBLOCK_BRICK.getId()));
		simpleBlock(Blocks.RGBLOCK_GLASS.get());
		simpleBlock(Blocks.RGBLOCK_GLASS_BORDER.get());
		simpleBlock(Blocks.RGBLOCK_GLOW.get(), model(Blocks.RGBLOCK.getId()));
		simpleBlock(Blocks.RGBLOCK_BRICK_GLOW.get(), model(Blocks.RGBLOCK_BRICK.getId()));
		simpleBlock(Blocks.RGBLOCK_GLASS_GLOW.get(), model(Blocks.RGBLOCK_GLASS.getId()));
		simpleBlock(Blocks.RGBLOCK_GLASS_BORDER_GLOW.get(), model(Blocks.RGBLOCK_GLASS_BORDER.getId()));
		simpleBlock(Blocks.CLEARGLASS.get());
		simpleBlock(Blocks.CLEARGLASS_BORDER.get());
		simpleBlock(Blocks.CLEARGLASS_GLOW.get(),model(Blocks.CLEARGLASS.getId()));
		simpleBlock(Blocks.CLEARGLASS_BORDER_GLOW.get(),model(Blocks.CLEARGLASS_BORDER.getId()));
	
	}

	
	
	
	
	//Make "x" block and an item form of it(models and states.)
	@Override
	public void simpleBlock(Block block, ModelFile model) {
		super.simpleBlock(block,model);
		this.simpleBlockItem(block, model);
	}	
	@Override
	public void simpleBlock(Block block) {
		super.simpleBlock(block);
		this.simpleBlockItem(block, model(block.getBlock()));
	}
	private void simpleSlab(SlabBlock block,ModelFile doubleSlabModel) {
		ResourceLocation key = block.getRegistryName();
		this.slabBlock(block, model(key), model(suffixPath(key, "_top")), doubleSlabModel);
		simpleBlockItem(block,model(key));
	}
	 private void simpleStairs(StairsBlock block,ModelFile model) {
	        ResourceLocation key = block.getRegistryName();
	        this.stairsBlock(block, model(key), model(suffixPath(key, "_inner")), model(suffixPath(key, "_outer")));
	        simpleBlockItem(block,model(key));
	    }
	
	
	
	
	
	
	//No touchy.
	public void simpleBlock(Supplier<? extends Block> blockSupplier) {
		simpleBlock(blockSupplier.get());
	}
		
    private ModelFile model(IForgeRegistryEntry<?> entry) {
        return model(entry.getRegistryName());
    }

    private ModelFile model(ResourceLocation name) {
        return new ModelFile.UncheckedModelFile(prefixPath(name, "block/"));
    }
	
    private static ResourceLocation prefixPath(ResourceLocation thing, String prefix) {
        return new ResourceLocation(thing.getNamespace(), prefix + thing.getPath());
    }

    private static ResourceLocation suffixPath(ResourceLocation thing, String suffix) {
        return new ResourceLocation(thing.getNamespace(), thing.getPath() + suffix);
    }
}
