package io.github.v2lenkagamine.common.interfaces;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IItemColorProvider {
	@OnlyIn(Dist.CLIENT)
	public IItemColor getItemColor();
}
