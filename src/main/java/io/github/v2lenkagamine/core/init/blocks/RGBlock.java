package io.github.v2lenkagamine.core.init.blocks;

import java.awt.Color;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.networking.ChangeColorPacket;
import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import io.github.v2lenkagamine.core.init.blocks.bases.StonelikeBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.state.EnumProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class RGBlock extends StonelikeBlock {
	public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);
	
	public RGBlock() {
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
	    public ActionResultType onBlockActivated(@Nonnull BlockState state, World worldIn,@Nonnull BlockPos pos,@Nonnull PlayerEntity player,@Nonnull Hand handIn,@Nonnull BlockRayTraceResult hit) {
	        if (!worldIn.isRemote) {
	            return ActionResultType.SUCCESS;
	        }
	        System.out.println("Setting new Color ");
	        int randomColor = (int) (Math.random() * Integer.MAX_VALUE);
	        ((RGBlockTE) Objects.requireNonNull(worldIn.getTileEntity(pos))).setColorFromInt(randomColor);
	        worldIn.notifyBlockUpdate(pos,state,state,3);
	        Networking.sendToServer(new ChangeColorPacket(randomColor,pos));

	        return ActionResultType.SUCCESS;
	    }

	    public static int getColorAsInt(Color color) {
	        if (color == null) {
	            return 0;
	        }
	        return ((color.getRed() & 0xFF) << 16) | ((color.getGreen() & 0xFF) << 8) | (color.getBlue() & 0xFF);
	    }
	}