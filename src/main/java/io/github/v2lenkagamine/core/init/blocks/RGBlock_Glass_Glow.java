package io.github.v2lenkagamine.core.init.blocks;

import java.awt.Color;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.networking.ChangeColorPacket;
import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class RGBlock_Glass_Glow extends GlassBlock{
	
	public RGBlock_Glass_Glow() {
		super(BlockProperties.glowingGlassProps);
	}
	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
	 @Nullable
	    @Override
	    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
	        return new RGBlockTE();
	    }
	 @Override
	    @Nonnull
	    public void onBlockPlacedBy(World worldIn,@Nonnull BlockPos pos, @Nonnull BlockState state,@Nonnull LivingEntity placer, ItemStack stack) {
	        if (placer.getHeldItemOffhand().getItem() == Items.RGB_INATOR.get()) {
	        	CompoundNBT nbt = placer.getHeldItemOffhand().getTag();
	        	int red = nbt.getInt("Red");
	        	int green = nbt.getInt("Green");
	        	int blue = nbt.getInt("Blue");
	        	int color = ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
	        	((RGBlockTE) Objects.requireNonNull(worldIn.getTileEntity(pos))).setColorFromInt(color);
		        worldIn.notifyBlockUpdate(pos,state,state,3);
		        Networking.sendToServer(new ChangeColorPacket(color,pos));
	    	
	        }
	    }

	    public static int getColorAsInt(Color color) {
	        if (color == null) {
	            return 0;
	        }
	        return ((color.getRed() & 0xFF) << 16) | ((color.getGreen() & 0xFF) << 8) | (color.getBlue() & 0xFF);
	    }
	    @Override
	    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos) {
	        return true;
	    }
}
