package io.github.v2lenkagamine.common.capabilities.gunTimer;

import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class GunTimerStorageEmpty implements ICapabilitySerializable<CompoundTag>{
	
	@Nullable
	@Override
	public CompoundTag serializeNBT() {
		return null;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return getCapability(cap);
	}
}
