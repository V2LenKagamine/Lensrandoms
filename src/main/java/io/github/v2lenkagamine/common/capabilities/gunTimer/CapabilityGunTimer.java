package io.github.v2lenkagamine.common.capabilities.gunTimer;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityGunTimer implements ICapabilityProvider{
	
	public static final Capability<GunTimerData> GUN_TIMER_CAPABILITY = CapabilityManager.get(new CapabilityToken<GunTimerData>() {} );
	
	final GunTimerData data = new GunTimerData();
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return getCapability(cap);
	}
	@SuppressWarnings("unchecked")
	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
		if (cap == GUN_TIMER_CAPABILITY)
			return (LazyOptional<T>) LazyOptional.of(() -> data);
		return LazyOptional.empty();
	}
}
