package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.core.init.blocks.Blocks;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class LensRandomsBlockLootTables extends BlockLoot {
	@Override	
	protected void addTables() {
		
		
		//DropSelf
		this.dropSelf(Blocks.RGBLOCK.get());
		this.dropSelf(Blocks.RGBLOCK_GLOW.get());
		this.dropSelf(Blocks.RGBLOCK_SLAB.get());
		this.dropSelf(Blocks.RGBLOCK_STAIR.get());
		this.dropSelf(Blocks.RGBLOCK_BRICK.get());
		this.dropSelf(Blocks.RGBLOCK_BRICK_GLOW.get());
		this.dropSelf(Blocks.RGBLOCK_BRICK_SLAB.get());
		this.dropSelf(Blocks.RGBLOCK_BRICK_STAIR.get());
		this.dropSelf(Blocks.CHARGER.get());
		this.dropSelf(Blocks.GUNSMITHING_TABLE.get());

		
		//Glasslikes
		this.dropWhenSilkTouch(Blocks.RGBLOCK_GLASS.get());
		this.dropWhenSilkTouch(Blocks.RGBLOCK_GLASS_GLOW.get());
		this.dropWhenSilkTouch(Blocks.RGBLOCK_GLASS_BORDER.get());
		this.dropWhenSilkTouch(Blocks.RGBLOCK_GLASS_BORDER_GLOW.get());
		this.dropWhenSilkTouch(Blocks.CLEARGLASS.get());
		this.dropWhenSilkTouch(Blocks.CLEARGLASS_GLOW.get());
		this.dropWhenSilkTouch(Blocks.CLEARGLASS_BORDER.get());
		this.dropWhenSilkTouch(Blocks.CLEARGLASS_BORDER_GLOW.get());
		
		
	}
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return RegistryHelper.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
	
}