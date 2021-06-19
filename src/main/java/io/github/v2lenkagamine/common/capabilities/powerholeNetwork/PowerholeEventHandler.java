package io.github.v2lenkagamine.common.capabilities.powerholeNetwork;

import io.github.v2lenkagamine.Lensrandoms;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class PowerholeEventHandler {
		
	public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<World> event) {

		if (event.getObject().getCapability(CapabilityPowerholeNetwork.POWERHOLE_NETWORK_CAPABILITY) == null) {
			PowerholeNetworkProvider provider = new PowerholeNetworkProvider();
			event.addCapability(new ResourceLocation(Lensrandoms.MOD_ID, "network" ), provider);
			event.addListener(provider::invalidateNetwork);
		}
		
	}
	
	
}
