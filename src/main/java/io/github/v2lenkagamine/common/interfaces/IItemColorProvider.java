package io.github.v2lenkagamine.common.interfaces;

import net.minecraft.client.color.item.ItemColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IItemColorProvider {
	@OnlyIn(Dist.CLIENT)
	public ItemColor getItemColor();
}
