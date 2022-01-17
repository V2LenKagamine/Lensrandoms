package io.github.v2lenkagamine.common.capabilities;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class LensEnergyCapabilityProvider implements ICapabilitySerializable<CompoundTag>{

	public static final Capability<LensEnergyData> GUN_ENERGY_CAPABILITY = CapabilityManager.get(new CapabilityToken<LensEnergyData>() {} );
	
	final LensEnergyData dataEnergy = new LensEnergyData();
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {

		return getCapability(cap);
	}
	
	@SuppressWarnings("unchecked")
	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
		if (cap == GUN_ENERGY_CAPABILITY) {
			return (LazyOptional<T>) LazyOptional.of(() -> dataEnergy);
		}
		return LazyOptional.empty();
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("capacity", dataEnergy.getMaxEnergyStored());
		tag.putInt("send", dataEnergy.getExtract());
		tag.putInt("receive", dataEnergy.getReceive());
		tag.putInt("energy", dataEnergy.getEnergyStored());
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		dataEnergy.setCapacity(nbt.getInt("capacity"));
		dataEnergy.setEnergy(nbt.getInt("energy"));
		dataEnergy.setExtractMax(nbt.getInt("send"));
		dataEnergy.setRecieveMax(nbt.getInt("receive"));
		
	}

}
