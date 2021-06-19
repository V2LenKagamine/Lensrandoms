package io.github.v2lenkagamine.common.capabilities.gunTimer;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityGunTimer {
	
	@CapabilityInject(IGunTimer.class)
	public static Capability<IGunTimer> GUN_TIMER_CAPABILITY = null;
	
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IGunTimer.class, new GunTimerStorageEmpty(),GunTimerDefault::new);
	}

}
