package io.github.v2lenkagamine.common.capabilities.powerholeNetwork;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityPowerholeNetwork {

	@CapabilityInject(IPowerholeNetwork.class)
	public static Capability<IPowerholeNetwork> POWERHOLE_NETWORK_CAPABILITY = null;
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IPowerholeNetwork.class, new PowerholeNetworkStorage(), DefaultPowerholeNetwork::new);
	}
	
}
