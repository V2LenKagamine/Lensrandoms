package io.github.v2lenkagamine.common.capabilities.powerholeNetwork;

import java.util.Set;

import com.google.common.collect.BiMap;

import net.minecraft.util.math.BlockPos;

public class DefaultPowerholeNetwork implements IPowerholeNetwork{
	private int channel;
	private BiMap<BlockPos,Integer> posmap;
	private BlockPos blockPos;

	
	
	
	@Override
	public int getchannel(BlockPos pos) {
		return posmap.get(pos);
	}

	@Override
	public void addToBlocklist(BlockPos pos,int channel) {
		posmap.put(pos,channel);
		
	}

	@Override
	public void removeFromBlocklist(BlockPos pos) {
		posmap.remove(pos);
		
	}

	@Override
	public BiMap<BlockPos, Integer> getBlocklist() {
		return posmap;
	}

	@Override
	public Set<BlockPos> getBlockPosSet() {
		return posmap.keySet();
	}

	@Override
	public BlockPos getBlockPos(int channel) {

		return posmap.inverse().get(channel);
	}

	@Override
	public void setchannel(int channel) {
		this.channel = channel;
		
	}

	@Override
	public int getchannel() {
		return channel;
	}

	@Override
	public BlockPos getBlockpos() {
		return blockPos;
	}

	@Override
	public int getSize(Set<BlockPos> set) {

		return set.size();
	}

	@Override
	public void setBlockPos(BlockPos blockpos) {
		this.blockPos = blockpos;
		
	}

}
