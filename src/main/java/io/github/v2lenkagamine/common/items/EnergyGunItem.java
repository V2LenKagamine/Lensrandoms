package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.GunTimerData;
import io.github.v2lenkagamine.common.capabilities.LensEnergyData;
import io.github.v2lenkagamine.common.capabilities.TimerAmmoCapabilityProvider;
import io.github.v2lenkagamine.common.capabilities.TimerEnergyCapabilityProvider;
import io.github.v2lenkagamine.core.util.WeaponFire;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class EnergyGunItem extends GunItem{

	public int maxEnergy = 0;
	
	public EnergyGunItem(Properties properties, int maxEnergy) {
		super(properties, maxEnergy);
		this.maxEnergy=maxEnergy;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
	         return new TimerEnergyCapabilityProvider(maxEnergy);
	}
	
	@Override
	 public boolean isBarVisible(ItemStack pStack) {
		if (pStack.getCapability(TimerEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY).isPresent()) 
		{return (pStack.getCapability(TimerEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY).resolve().get().getEnergyStored() > 1);}
		else return false;
	   }
	
	public InteractionResultHolder<ItemStack> fireEWeapon(Player playerIn,Level worldIn,InteractionHand handIn,int energyPerShot,int shotCdTicks,int range,int minDmg,int maxDmg,boolean pierce,boolean doConsume) {
		ItemStack item = playerIn.getItemInHand(handIn);
		LazyOptional<GunTimerData> capTimer = item.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY);
		LazyOptional<LensEnergyData> capEnergy = item.getCapability(TimerEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY);
		if (capTimer.isPresent() && capEnergy.isPresent())
			{
				if (capTimer.resolve().get().getTimerTicks() < 1 ){
					if (capEnergy.resolve().get().getEnergyStored() >= energyPerShot) {
						if (!pierce) {WeaponFire.fireNormal(playerIn, range, minDmg, maxDmg);}
						else {WeaponFire.firePierceAll(playerIn, range, minDmg, maxDmg);}
						if (!playerIn.isCreative()) {capEnergy.resolve().get().removeEnergy(energyPerShot);}
						item.setDamageValue(capEnergy.resolve().get().getMaxEnergyStored() - capEnergy.resolve().get().getEnergyStored());
						capTimer.resolve().get().setTimerTicks(shotCdTicks);
						playShot(playerIn, worldIn);
					} 
					else {
						playEmpty(playerIn, worldIn);
					}
				}
			}
		return super.use(worldIn, playerIn, handIn);
	}
	
}
