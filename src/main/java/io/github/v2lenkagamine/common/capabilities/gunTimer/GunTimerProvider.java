package io.github.v2lenkagamine.common.capabilities.gunTimer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class GunTimerProvider implements ICapabilityProvider{
	
	public final LazyOptional<IGunTimer> gunContainer = LazyOptional.of(GunTimerDefault::new);
	
	
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return cap == CapabilityGunTimer.GUN_TIMER_CAPABILITY ? gunContainer.cast() : LazyOptional.empty();
	}
}
		
