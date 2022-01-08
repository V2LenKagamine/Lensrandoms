package io.github.v2lenkagamine.core.init.blocks;

import java.awt.Color;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.networking.ChangeColorPacket;
import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RGBlock extends Block {
	
	public RGBlock() {
		super(BlockProperties.stoneProps);
	}
	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
	 @Nullable
	    @Override
	    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
	        return new RGBlockTE();
	    }
	 @Override
	    @Nonnull
	    public void setPlacedBy(Level worldIn,@Nonnull BlockPos pos, @Nonnull BlockState state,@Nonnull LivingEntity placer, ItemStack stack) {
	        if (placer.getOffhandItem().getItem() == Items.RGB_INATOR.get()) {
	        	CompoundTag nbt = placer.getOffhandItem().getTag();
	        	int red = nbt.getInt("Red");
	        	int green = nbt.getInt("Green");
	        	int blue = nbt.getInt("Blue");
	        	int color = ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
	        	((RGBlockTE) Objects.requireNonNull(worldIn.getBlockEntity(pos))).setColorFromInt(color);
		        worldIn.sendBlockUpdated(pos,state,state,3);
		        Networking.sendToServer(new ChangeColorPacket(color,pos));
	    	
	        }
	    }

	    public static int getColorAsInt(Color color) {
	        if (color == null) {
	            return 0;
	        }
	        return ((color.getRed() & 0xFF) << 16) | ((color.getGreen() & 0xFF) << 8) | (color.getBlue() & 0xFF);
	    }
}