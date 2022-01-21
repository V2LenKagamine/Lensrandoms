package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.core.init.blocks.LensBlocks;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class LensRandomsBlockLootTables extends BlockLoot {
	@Override	
	protected void addTables() {
		
		
		//DropSelf
		this.dropSelf(LensBlocks.RGBLOCK.get());
		this.dropSelf(LensBlocks.RGBLOCK_GLOW.get());
		this.dropSelf(LensBlocks.RGBLOCK_SLAB.get());
		this.dropSelf(LensBlocks.RGBLOCK_STAIR.get());
		this.dropSelf(LensBlocks.RGBLOCK_BRICK.get());
		this.dropSelf(LensBlocks.RGBLOCK_BRICK_GLOW.get());
		this.dropSelf(LensBlocks.RGBLOCK_BRICK_SLAB.get());
		this.dropSelf(LensBlocks.RGBLOCK_BRICK_STAIR.get());
		this.dropSelf(LensBlocks.CHARGER.get());
		this.dropSelf(LensBlocks.GUNSMITHING_TABLE.get());

		
		//Glasslikes
		this.dropWhenSilkTouch(LensBlocks.RGBLOCK_GLASS.get());
		this.dropWhenSilkTouch(LensBlocks.RGBLOCK_GLASS_GLOW.get());
		this.dropWhenSilkTouch(LensBlocks.RGBLOCK_GLASS_BORDER.get());
		this.dropWhenSilkTouch(LensBlocks.RGBLOCK_GLASS_BORDER_GLOW.get());
		this.dropWhenSilkTouch(LensBlocks.CLEARGLASS.get());
		this.dropWhenSilkTouch(LensBlocks.CLEARGLASS_GLOW.get());
		this.dropWhenSilkTouch(LensBlocks.CLEARGLASS_BORDER.get());
		this.dropWhenSilkTouch(LensBlocks.CLEARGLASS_BORDER_GLOW.get());
		
		
	}
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return RegistryHelper.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
	
}