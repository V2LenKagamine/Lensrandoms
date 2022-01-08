package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.gunTimer.CapabilityGunTimer;
import io.github.v2lenkagamine.common.capabilities.gunTimer.GunTimerProvider;
import io.github.v2lenkagamine.common.capabilities.gunTimer.IGunTimer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public abstract class GunItem extends Item{


	public GunItem(Properties properties) {
		super(properties.stacksTo(1));
	}
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
		
	         return new GunTimerProvider();
		
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
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
