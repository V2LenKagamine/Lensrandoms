package io.github.v2lenkagamine.core.init.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ClearGlass_Border extends GlassBlock{

	public ClearGlass_Border() {
		super(BlockProperties.glassProps);
	}
    @Override
    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos) {
        return true;
    }
}
