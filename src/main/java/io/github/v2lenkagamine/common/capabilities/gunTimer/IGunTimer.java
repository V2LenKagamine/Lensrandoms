package io.github.v2lenkagamine.common.capabilities.gunTimer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IGunTimer extends INBTSerializable<CompoundNBT>{

	int getTimer();
	
	int getTimerTicks();
	
	void setTimer(int time);
	
	void setTimerTicks(int time);
	
	
	
}
