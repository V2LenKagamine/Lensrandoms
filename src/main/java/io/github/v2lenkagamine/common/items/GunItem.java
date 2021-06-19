package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.gunTimer.GunTimerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class GunItem extends Item{

	public GunItem(Properties properties) {
		super(properties.maxStackSize(1));
	}
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
		
		if (this instanceof GunItem)
	         return new GunTimerProvider();
	      else
	         return super.initCapabilities(stack, nbt);
		
	}
}
