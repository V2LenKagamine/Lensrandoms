package io.github.v2lenkagamine.common.capabilities.gunTimer;

import net.minecraft.nbt.CompoundNBT;

public class GunTimerDefault implements IGunTimer{

	private int reloadTime;
	
	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putInt("ReloadTime", getTimerTicks());
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		this.setTimerTicks(nbt.getInt("ReloadTime"));
	}

	@Override
	public int getTimer() {
		int timer = getTimerTicks() * 20;
		return timer;
	}

	@Override
	public int getTimerTicks() {
		
		return reloadTime;
	}

	@Override
	public void setTimer(int time) {

		reloadTime = (time * 20);
		
	}

	@Override
	public void setTimerTicks(int time) {
		reloadTime = time;
		
	}

}
