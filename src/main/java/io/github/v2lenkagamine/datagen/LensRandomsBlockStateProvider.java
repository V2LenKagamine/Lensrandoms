package io.github.v2lenkagamine.datagen;

import java.util.function.Supplier;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.init.blocks.LensBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
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
		simpleBlock(LensBlocks.RGBLOCK.get());
		simpleSlab(LensBlocks.RGBLOCK_SLAB.get(),model(LensBlocks.RGBLOCK.getId()));
		simpleStairs(LensBlocks.RGBLOCK_STAIR.get(),model(LensBlocks.RGBLOCK.getId()));
		simpleBlock(LensBlocks.RGBLOCK_BRICK.get());
		simpleSlab(LensBlocks.RGBLOCK_BRICK_SLAB.get(),model(LensBlocks.RGBLOCK_BRICK.getId()));
		simpleStairs(LensBlocks.RGBLOCK_BRICK_STAIR.get(),model(LensBlocks.RGBLOCK_BRICK.getId()));
		simpleBlock(LensBlocks.RGBLOCK_GLASS.get());
		simpleBlock(LensBlocks.RGBLOCK_GLASS_BORDER.get());
		simpleBlock(LensBlocks.RGBLOCK_GLOW.get(), model(LensBlocks.RGBLOCK.getId()));
		simpleBlock(LensBlocks.RGBLOCK_BRICK_GLOW.get(), model(LensBlocks.RGBLOCK_BRICK.getId()));
		simpleBlock(LensBlocks.RGBLOCK_GLASS_GLOW.get(), model(LensBlocks.RGBLOCK_GLASS.getId()));
		simpleBlock(LensBlocks.RGBLOCK_GLASS_BORDER_GLOW.get(), model(LensBlocks.RGBLOCK_GLASS_BORDER.getId()));
		simpleBlock(LensBlocks.CLEARGLASS.get());
		simpleBlock(LensBlocks.CLEARGLASS_BORDER.get());
		simpleBlock(LensBlocks.CLEARGLASS_GLOW.get(),model(LensBlocks.CLEARGLASS.getId()));
		simpleBlock(LensBlocks.CLEARGLASS_BORDER_GLOW.get(),model(LensBlocks.CLEARGLASS_BORDER.getId()));
		simplePillar(LensBlocks.CHARGER.get(), "charger");
		simplePillar(LensBlocks.GUNSMITHING_TABLE.get(), "gunsmithing_table");
	
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
		this.simpleBlockItem(block, model(block.asItem()));
	}
	private void simpleSlab(SlabBlock block,ModelFile doubleSlabModel) {
		ResourceLocation key = block.getRegistryName();
		this.slabBlock(block, model(key), model(suffixPath(key, "_top")), doubleSlabModel);
		simpleBlockItem(block,model(key));
	}
	 private void simpleStairs(StairBlock block,ModelFile model) {
	    ResourceLocation key = block.getRegistryName();
	    this.stairsBlock(block, model(key), model(suffixPath(key, "_inner")), model(suffixPath(key, "_outer")));
	    simpleBlockItem(block,model(key));
	}
	private void simplePillar(Block block,String string) {
		ResourceLocation key = block.getRegistryName();
		
		ModelFile model = models().cubeColumn(string, prefixPath(key, "block/"),suffixPath(prefixPath(key,"block/"), "_end"));
		this.simpleBlock(block,model);
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
