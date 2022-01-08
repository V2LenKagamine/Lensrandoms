package io.github.v2lenkagamine.common.capabilities.gunTimer;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CapabilityGunTimer {
	
	public static final Capability<IGunTimer> GUN_TIMER_CAPABILITY = CapabilityManager.get(new CapabilityToken<IGunTimer>() {} );
}
