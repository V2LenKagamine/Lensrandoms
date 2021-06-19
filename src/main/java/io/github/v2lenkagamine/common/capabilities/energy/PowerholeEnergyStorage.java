package io.github.v2lenkagamine.common.capabilities.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class PowerholeEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT>{

	public PowerholeEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("energy", getEnergyStored());
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		setEnergy(nbt.getInt("energy"));
		
	}
	
	public void consumeEnergy(int energy) {
	       this.energy -= energy;
	       if (this.energy < 0) {
	           this.energy = 0;
	       }
	        onEnergyChanged();
	    }
	 
	public void setEnergy(int energy) {
		this.energy = energy;
		onEnergyChanged();
		
	}

	protected void onEnergyChanged() {
		
	}
	@Override
	public boolean canExtract() {
		return true;
	}
	@Override
	public boolean canReceive() {
		return true;
	}
}
