package io.github.v2lenkagamine.core.init.blocks.bases;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class StonelikeBlock extends Block{
	
	
	public StonelikeBlock() {
		
		super(Properties.create(Material.ROCK)
				.harvestLevel(0)
				.hardnessAndResistance(1.5f)
				.sound(SoundType.STONE));
	}

}
