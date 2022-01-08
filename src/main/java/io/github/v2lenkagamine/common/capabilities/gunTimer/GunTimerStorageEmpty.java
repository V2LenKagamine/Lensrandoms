package io.github.v2lenkagamine.common.capabilities.gunTimer;

import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class GunTimerStorageEmpty implements IStorage<IGunTimer>{
	
	@Nullable
	@Override
	public Tag writeNBT(Capability<IGunTimer> capability, IGunTimer instance, Direction side) {
		return null;
	}

	@Override
	public void readNBT(Capability<IGunTimer> capability, IGunTimer instance, Direction side, Tag nbt) {
		
	}

}
