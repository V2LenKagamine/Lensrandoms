package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.capabilities.CapabilityBulletPouch;
import io.github.v2lenkagamine.common.capabilities.TimerAmmoCapabilityProvider;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Lensrandoms.MOD_ID, bus = Bus.MOD)
public class CommonSetupEvent {
	@SubscribeEvent
	public static void capRegistry(RegisterCapabilitiesEvent event) {
		event.register(TimerAmmoCapabilityProvider.class);
		event.register(CapabilityBulletPouch.class);
	}
}
