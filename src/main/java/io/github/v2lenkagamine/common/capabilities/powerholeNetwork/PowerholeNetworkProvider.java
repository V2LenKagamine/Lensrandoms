package io.github.v2lenkagamine.common.capabilities.powerholeNetwork;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PowerholeNetworkProvider implements ICapabilitySerializable<CompoundNBT>{

	private final DefaultPowerholeNetwork network = new DefaultPowerholeNetwork();
	private final LazyOptional<IPowerholeNetwork> networkOptional = LazyOptional.of(() -> network);
	
	public void invalidateNetwork() {
		networkOptional.invalidate();
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		return networkOptional.cast();
	}

	@Override
	public CompoundNBT serializeNBT() {
		if (CapabilityPowerholeNetwork.POWERHOLE_NETWORK_CAPABILITY == null) {
			return new CompoundNBT();
		} else {
			return (CompoundNBT) CapabilityPowerholeNetwork.POWERHOLE_NETWORK_CAPABILITY.writeNBT(network, null);
		}
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if (CapabilityPowerholeNetwork.POWERHOLE_NETWORK_CAPABILITY != null) {
			CapabilityPowerholeNetwork.POWERHOLE_NETWORK_CAPABILITY.readNBT(network, null, nbt);
		}
		
	}
		
}
