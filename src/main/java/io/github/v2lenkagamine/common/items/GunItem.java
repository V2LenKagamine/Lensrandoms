package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.gunTimer.CapabilityGunTimer;
import io.github.v2lenkagamine.common.capabilities.gunTimer.GunTimerProvider;
import io.github.v2lenkagamine.common.capabilities.gunTimer.IGunTimer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

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
		
	         return new GunTimerProvider();
		
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(isSelected) {
			LazyOptional<IGunTimer> lazyCap = stack.getCapability(CapabilityGunTimer.GUN_TIMER_CAPABILITY);
			if (lazyCap.isPresent())
			{
				if (lazyCap.resolve().get().getTimerTicks() > 0) 
				{
					lazyCap.resolve().get().timerDown();
				}
				
			}
		}		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
}
