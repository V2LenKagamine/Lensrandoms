package io.github.v2lenkagamine.core.init.blocks;

import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.tileentity.PowerHoleTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class PowerHole_Plug extends Block{

	public PowerHole_Plug() {
		super(BlockProperties.machineProps);
	}
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		
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
