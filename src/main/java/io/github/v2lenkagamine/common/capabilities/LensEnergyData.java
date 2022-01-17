package io.github.v2lenkagamine.common.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;

public class LensEnergyData implements IEnergyStorage,INBTSerializable<CompoundTag>{

    protected int energy;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            energy += energyReceived;
        return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
	}
	
	public void setCapacity(int newCapacity) {
		this.capacity = newCapacity;
	}
	
	public void setExtractMax(int newMax) {
		this.maxExtract = newMax;
	}

	public void setRecieveMax(int newMax) {
		this.maxReceive = newMax;
	}
	
	public void setEnergy(int newEnergy) {
		this.energy = newEnergy;
	}
	
	@Override
	public int getEnergyStored() {
		return energy;
	}

	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public boolean canExtract() {
		return this.maxExtract > 0;
	}

	@Override
	public boolean canReceive() {
		return this.maxReceive > 0;
	}

	@Override
	public CompoundTag serializeNBT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		// TODO Auto-generated method stub
		
	}

}
