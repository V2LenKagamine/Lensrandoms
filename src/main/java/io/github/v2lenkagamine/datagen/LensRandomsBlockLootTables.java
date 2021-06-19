package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.core.init.blocks.Blocks;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraftforge.fml.RegistryObject;

public class LensRandomsBlockLootTables extends BlockLootTables {
	@Override	
	protected void addTables() {
		
		
		//DropSelf
		this.registerDropSelfLootTable(Blocks.POWER_HOLE.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK_GLOW.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK_SLAB.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK_STAIR.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK_BRICK.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK_BRICK_GLOW.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK_BRICK_SLAB.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK_BRICK_STAIR.get());

		
		//Glasslikes
		this.registerSilkTouch(Blocks.RGBLOCK_GLASS.get());
		this.registerSilkTouch(Blocks.RGBLOCK_GLASS_GLOW.get());
		this.registerSilkTouch(Blocks.RGBLOCK_GLASS_BORDER.get());
		this.registerSilkTouch(Blocks.RGBLOCK_GLASS_BORDER_GLOW.get());
		this.registerSilkTouch(Blocks.CLEARGLASS.get());
		this.registerSilkTouch(Blocks.CLEARGLASS_GLOW.get());
		this.registerSilkTouch(Blocks.CLEARGLASS_BORDER.get());
		this.registerSilkTouch(Blocks.CLEARGLASS_BORDER_GLOW.get());
		
		
	}
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return RegistryHelper.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
	
}