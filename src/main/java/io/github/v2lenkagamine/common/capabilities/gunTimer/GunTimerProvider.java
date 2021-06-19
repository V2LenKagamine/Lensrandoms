package io.github.v2lenkagamine.common.capabilities.gunTimer;

import javax.annotation.Nullable;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class GunTimerProvider implements ICapabilityProvider{
	
	public static final Capability<IGunTimer> GUN_TIMER_CAPABILITY = null;
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		return GUN_TIMER_CAPABILITY == cap ? LazyOptional.of(GunTimerDefault::new).cast() : LazyOptional.empty();
	}
}
		
