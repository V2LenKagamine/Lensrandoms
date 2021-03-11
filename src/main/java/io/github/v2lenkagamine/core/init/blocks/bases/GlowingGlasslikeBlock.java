package io.github.v2lenkagamine.core.init.blocks.bases;

import net.minecraft.block.material.MaterialColor;

public class GlowingGlasslikeBlock extends GlasslikeBlock{

	public GlowingGlasslikeBlock(Properties properties,int color,MaterialColor materialColor) {
		super(properties.setLightLevel(b -> 15),color,materialColor);

	}
	
}
