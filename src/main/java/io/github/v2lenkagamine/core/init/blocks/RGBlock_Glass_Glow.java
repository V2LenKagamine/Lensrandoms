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
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RGBlock_Glass_Glow extends GlassBlock implements EntityBlock{
	
	public RGBlock_Glass_Glow() {
		super(BlockProperties.glowingGlassProps);
	}
	 @Nullable
	    @Override
	    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
	        return new RGBlockTE(pos, state);
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
	    @Override
	    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
	        return true;
	    }
}
