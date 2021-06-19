package io.github.v2lenkagamine.core.init.blocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.capabilities.powerholeNetwork.CapabilityPowerholeNetwork;
import io.github.v2lenkagamine.common.networking.ChangeChannelPacket;
import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.common.tileentity.PowerHoleTE;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class PowerHole extends Block {
	public PowerHole() {
		super(BlockProperties.machineProps);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state,@Nonnull LivingEntity placer, ItemStack stack) {
		if (placer.getHeldItemOffhand().getItem() == Items.RGB_INATOR.get()) {
        	CompoundNBT nbt = placer.getHeldItemOffhand().getTag();
        	int red = nbt.getInt("Red");
        	int green = nbt.getInt("Green");
        	int blue = nbt.getInt("Blue");
        	int channel = ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
        	Networking.sendToServer(new ChangeChannelPacket(channel, pos));
		}
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
				if (te instanceof PowerHoleTE) {
					PowerHoleTE powerhole = (PowerHoleTE) te;
					BlockPos powerPos = powerhole.getPos();
					int powerChannel = powerhole.getChannel();
					worldIn.getCapability(CapabilityPowerholeNetwork.POWERHOLE_NETWORK_CAPABILITY).ifPresent(network -> network.addToBlocklist(powerPos, powerChannel));
				}
			
		}
	}
	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
	
	 @Nullable
	    @Override
	    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
	        return new PowerHoleTE();
	    }

}
