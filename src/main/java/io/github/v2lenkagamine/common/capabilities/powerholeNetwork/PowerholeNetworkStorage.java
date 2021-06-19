package io.github.v2lenkagamine.common.capabilities.powerholeNetwork;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PowerholeNetworkStorage implements IStorage<IPowerholeNetwork> {
	
	@Nullable
	@Override
	public INBT writeNBT(Capability<IPowerholeNetwork> capability, IPowerholeNetwork instance, Direction side) {
		ListNBT list = new ListNBT();
		for (BlockPos pos : instance.getBlockPosSet()) {
			CompoundNBT tag = NBTUtil.writeBlockPos(pos);
			int channel = instance.getchannel(pos);
			tag.putInt("Channel", channel);
			list.add(tag);
			}
		return list;
	}

	@Override
	public void readNBT(Capability<IPowerholeNetwork> capability, IPowerholeNetwork instance, Direction side,
			INBT nbt) {
		ListNBT nbtlist = ((ListNBT) nbt).getList(0);

		nbtlist.forEach((iscnbt) -> {
				if (iscnbt instanceof CompoundNBT) {
					BlockPos pos = NBTUtil.readBlockPos((CompoundNBT) iscnbt);
					int channel = ((CompoundNBT) iscnbt).getInt("Channel");
					instance.addToBlocklist(pos, channel);
				}
		});
	}

}
