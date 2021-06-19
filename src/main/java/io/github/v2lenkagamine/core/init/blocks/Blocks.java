package io.github.v2lenkagamine.core.init.blocks;

import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraftforge.fml.RegistryObject;

public class Blocks {
	
	//Misc Blocks
	public static final RegistryObject<Block> POWER_HOLE = RegistryHelper.BLOCKS.register("powerhole", PowerHole::new);
	
	
	//RGBlocks
	public static final RegistryObject<Block> RGBLOCK = RegistryHelper.BLOCKS.register("rgblock",RGBlock::new);
	public static final RegistryObject<Block> RGBLOCK_GLOW = RegistryHelper.BLOCKS.register("rgblock_glow",RGBlock_Glow::new);
	public static final RegistryObject<StairsBlock> RGBLOCK_STAIR = RegistryHelper.BLOCKS.register("rgblock_stair", () -> new RGBlock_Stair(() -> RGBLOCK.get().getDefaultState()));
	public static final RegistryObject<SlabBlock> RGBLOCK_SLAB = RegistryHelper.BLOCKS.register("rgblock_slab", RGBlock_Slab::new);
	public static final RegistryObject<Block> RGBLOCK_BRICK = RegistryHelper.BLOCKS.register("rgblock_brick",RGBlock_Brick::new);
	public static final RegistryObject<Block> RGBLOCK_BRICK_GLOW = RegistryHelper.BLOCKS.register("rgblock_brick_glow",RGBlock_Brick_Glow::new);
	public static final RegistryObject<StairsBlock> RGBLOCK_BRICK_STAIR = RegistryHelper.BLOCKS.register("rgblock_brick_stair", () -> new RGBlock_Brick_Stair(() -> RGBLOCK_BRICK.get().getDefaultState()));
	public static final RegistryObject<SlabBlock> RGBLOCK_BRICK_SLAB = RegistryHelper.BLOCKS.register("rgblock_brick_slab", RGBlock_Brick_Slab::new);
	public static final RegistryObject<Block> RGBLOCK_GLASS = RegistryHelper.BLOCKS.register("rgblock_glass", RGBlock_Glass::new);
	public static final RegistryObject<Block> RGBLOCK_GLASS_GLOW = RegistryHelper.BLOCKS.register("rgblock_glass_glow", RGBlock_Glass_Glow::new);
	public static final RegistryObject<Block> RGBLOCK_GLASS_BORDER = RegistryHelper.BLOCKS.register("rgblock_glass_border", RGBlock_Glass_Border::new);
	public static final RegistryObject<Block> RGBLOCK_GLASS_BORDER_GLOW = RegistryHelper.BLOCKS.register("rgblock_glass_border_glow", RGBlock_Glass_Border_Glow::new);
	
	//Clearglass
	public static final RegistryObject<Block> CLEARGLASS = RegistryHelper.BLOCKS.register("clearglass", ClearGlass::new);
	public static final RegistryObject<Block> CLEARGLASS_BORDER = RegistryHelper.BLOCKS.register("clearglass_border", ClearGlass_Border::new);
	public static final RegistryObject<Block> CLEARGLASS_GLOW = RegistryHelper.BLOCKS.register("clearglass_glow", ClearGlass_Glow::new);
	public static final RegistryObject<Block> CLEARGLASS_BORDER_GLOW = RegistryHelper.BLOCKS.register("clearglass_border_glow", ClearGlassBorder_Glow::new);
	
	//No touchy
	public static void register() {}
}
