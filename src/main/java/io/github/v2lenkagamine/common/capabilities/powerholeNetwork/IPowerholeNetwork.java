package io.github.v2lenkagamine.common.capabilities.powerholeNetwork;

import java.util.Set;

import com.google.common.collect.BiMap;

import net.minecraft.util.math.BlockPos;

public interface IPowerholeNetwork {
	
	BiMap<BlockPos,Integer> getBlocklist();
	
	Set<BlockPos> getBlockPosSet();
	
	int getSize(Set<BlockPos> set);
	
	BlockPos getBlockPos(int channel);
	
	int getchannel(BlockPos pos);
	
	int getchannel();
	
	BlockPos getBlockpos();
	
	void setchannel(int channel);
	
	void setBlockPos(BlockPos pos);

	void addToBlocklist(BlockPos pos,int channel);
	
	void removeFromBlocklist(BlockPos pos);
}
