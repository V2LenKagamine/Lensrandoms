package io.github.v2lenkagamine.common.capabilities.gunTimer;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class GunTimerStorageEmpty implements IStorage<IGunTimer>{

	@Override
	public INBT writeNBT(Capability<IGunTimer> capability, IGunTimer instance, Direction side) {
		return null;
	}

	@Override
	public void readNBT(Capability<IGunTimer> capability, IGunTimer instance, Direction side, INBT nbt) {
		
	}

}
