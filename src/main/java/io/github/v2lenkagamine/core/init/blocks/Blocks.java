package io.github.v2lenkagamine.core.init.blocks;

import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

public class Blocks {
	
	//Misc Blocks
	public static final RegistryObject<Block> POWER_HOLE = RegistryHelper.BLOCKS.register("powerhole", PowerHole::new);
	public static final RegistryObject<Block> RGBLOCK = RegistryHelper.BLOCKS.register("rgblock",RGBlock::new);
	public static void register() {}
}
