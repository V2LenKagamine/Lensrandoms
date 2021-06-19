package io.github.v2lenkagamine.common.tileentity;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import io.github.v2lenkagamine.common.capabilities.energy.PowerholeEnergyStorage;
import io.github.v2lenkagamine.common.capabilities.powerholeNetwork.CapabilityPowerholeNetwork;
import io.github.v2lenkagamine.core.init.TileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class PowerHoleTE extends TileEntity implements ITickableTileEntity{
	
	
	private PowerholeEnergyStorage storage = createStorage();
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of( () -> storage);
	
	public int channel = 0;
	
	public PowerHoleTE(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	

	public PowerHoleTE() {
		this(TileEntityTypes.POWER_HOLE_TE.get());
	}
	
	public void setChannelFromStorage(BlockPos pos, int channel) {
		if (pos == this.getPos()) {
			this.channel = channel;
		}
	}

	public void setChannelFromInt(int channel) {
		this.channel = channel;
		markDirty();
	}
	
	private PowerholeEnergyStorage createStorage() {
		return new PowerholeEnergyStorage(LensRandomsConfig.POWERHOLE_CAPACITY.get(),LensRandomsConfig.POWERHOLE_TRANSFER.get()) {
			@Override
			protected void onEnergyChanged() {
				markDirty();
			}
		};
	}
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(cap, side);
	}
	@Override
	public void remove() {
		super.remove();
		energy.invalidate();
		this.getWorld().getCapability(CapabilityPowerholeNetwork.POWERHOLE_NETWORK_CAPABILITY).ifPresent(network -> network.removeFromBlocklist(this.getPos()));
	}


	@Override
	public void tick() {
		if (!world.isRemote) {
			sendPower();
			sendToConnected();
		}
	}
	
	private void sendToConnected() {
		AtomicInteger powerStored = new AtomicInteger(storage.getEnergyStored());
		int techannel = this.channel;
		if (powerStored.get() > 0) {
			this.getWorld().getCapability(CapabilityPowerholeNetwork.POWERHOLE_NETWORK_CAPABILITY).ifPresent(network -> network.getBlocklist().forEach( (pos,channel) -> {
				if (channel == techannel) {
					TileEntity te = world.getTileEntity(pos);
					te.getCapability(CapabilityEnergy.ENERGY).ifPresent(handler -> {
						if (handler.canReceive()) {
							int received = handler.receiveEnergy(Math.min(powerStored.get(), LensRandomsConfig.POWERHOLE_CAPACITY.get()), false);
            				powerStored.addAndGet(-received);
            				storage.consumeEnergy(received);
            				markDirty();
						}
					});
				}
			}));
		}
		
	}


	private void sendPower() {
		AtomicInteger powerStored = new AtomicInteger(storage.getEnergyStored());
		if (powerStored.get() > 0) {
			for (Direction direction : Direction.values()) {
                TileEntity te = world.getTileEntity(pos.offset(direction));
                if (te != null) {
                	boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                			if (handler.canReceive()) {
                				int received = handler.receiveEnergy(Math.min(powerStored.get(), LensRandomsConfig.POWERHOLE_CAPACITY.get()), false);
                				powerStored.addAndGet(-received);
                				storage.consumeEnergy(received);
                				markDirty();
                				return powerStored.get() > 0;
                			} else {
                				return true;
                			}
                		}
                	).orElse(true);
                if (!doContinue) {
				return;
				}
            }
		}
	}
}
	
	@Override
    public void read(BlockState state,CompoundNBT tag) {
        storage.deserializeNBT(tag.getCompound("energy"));

        super.read(state, tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("energy", storage.serializeNBT());

        return super.write(tag);
    }


	public int getChannel() {
		return this.channel;
	}

}
