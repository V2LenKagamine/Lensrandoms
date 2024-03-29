package io.github.v2lenkagamine.common.capabilities;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TimerEnergyCapabilityProvider implements ICapabilitySerializable<CompoundTag>{

	public static final Capability<LensEnergyData> GUN_ENERGY_CAPABILITY = CapabilityManager.get(new CapabilityToken<LensEnergyData>() {} );
	public static final Capability<GunTimerData> GUN_TIMER_CAPABILITY = CapabilityManager.get(new CapabilityToken<GunTimerData>() {} );
	
	
	final GunTimerData dataTimer = new GunTimerData();
	final LensEnergyData dataEnergy = new LensEnergyData();

	
	
	public TimerEnergyCapabilityProvider(int maxEnergy) {
		dataEnergy.setCapacity(maxEnergy);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return getCapability(cap);
	}
	
	@SuppressWarnings("unchecked")
	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
		if (cap == GUN_TIMER_CAPABILITY)
			return (LazyOptional<T>) LazyOptional.of(() -> dataTimer);
		
		else if (cap == GUN_ENERGY_CAPABILITY) {
			return (LazyOptional<T>) LazyOptional.of(() -> dataEnergy);
		}
		return LazyOptional.empty();
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("ttf",dataTimer.getTimerTicks());
		tag.putInt("capacity", dataEnergy.getMaxEnergyStored());
		tag.putInt("send", dataEnergy.getExtract());
		tag.putInt("receive", dataEnergy.getReceive());
		tag.putInt("energy", dataEnergy.getEnergyStored());
		return tag;
	}
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		dataTimer.setTimerTicks(nbt.getInt("ttf"));
		dataEnergy.setCapacity(nbt.getInt("capacity"));
		dataEnergy.setEnergy(nbt.getInt("energy"));
		dataEnergy.setExtractMax(nbt.getInt("send"));
		dataEnergy.setRecieveMax(nbt.getInt("receive"));
	}
	
}
