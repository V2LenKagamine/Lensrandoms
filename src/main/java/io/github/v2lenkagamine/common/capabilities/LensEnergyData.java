package io.github.v2lenkagamine.common.capabilities;

import net.minecraftforge.energy.IEnergyStorage;

public class LensEnergyData implements IEnergyStorage{

    protected int energy;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;
	
    public LensEnergyData() {
    	this(0,0,0,0);
    }
    
    public LensEnergyData(int capacity) {
    	this(capacity,0,0,0);
    }
    
    public LensEnergyData(int capacity,int energy) {
    	this(capacity,energy,0,0);
    }
    
    public LensEnergyData(int capacity,int energy,int transfer) {
    	this(capacity,energy,transfer,transfer);
    }

   public LensEnergyData(int capacity,int energy,int maxReceive,int maxExtract) {
    	this.energy = energy;
    	this.capacity = capacity;
    	this.maxReceive = maxReceive;
    	this.maxExtract = maxExtract;
    }
    
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
	
	public void removeEnergy(int amount) {
		this.energy = getEnergyStored()-amount;
	}
	
	public void addEnergy(int amount) {
		this.energy = getEnergyStored()+amount;
	}
	
	public int getReceive() {
		return this.maxReceive;
	}
	
	public int getExtract() {
		return this.maxExtract;
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
}
