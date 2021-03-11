package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.core.init.blocks.Blocks;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraftforge.fml.RegistryObject;

public class LensRandomsBlockLootTables extends BlockLootTables {
	@Override	
	protected void addTables() {
		this.registerDropSelfLootTable(Blocks.POWER_HOLE.get());
		this.registerDropSelfLootTable(Blocks.RGBLOCK.get());
	}
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return RegistryHelper.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
	
}