package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.GunAmmoData;
import io.github.v2lenkagamine.common.capabilities.GunTimerData;
import io.github.v2lenkagamine.common.capabilities.TimerAmmoCapabilityProvider;
import io.github.v2lenkagamine.core.util.ItemUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
		
	         return new TimerAmmoCapabilityProvider();
	         
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(isSelected) {
			LazyOptional<GunTimerData> lazyCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY);
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
	
	public void reload(ItemStack stack,int maxAmmo,LivingEntity ent,int cooldown,ItemStack ammoType) {
		GunAmmoData gunAmmoCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY).resolve().get();
		GunTimerData gunTimerCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY).resolve().get();
		if (gunAmmoCap.getAmmoLeft() < maxAmmo) {
			int ammoCons = maxAmmo - gunAmmoCap.getAmmoLeft();
			if(ItemUtil.lookInPouches(ent, ammoType, true, ammoCons)) {
				gunAmmoCap.addAmmo(ammoCons);
				gunTimerCap.setTimer(cooldown);
			}
			else if(ItemUtil.getPlayerItem(ent, ammoType, true, ammoCons)) {
				gunAmmoCap.addAmmo(ammoCons);
				gunTimerCap.setTimer(cooldown);
			}
		}
	}
}
