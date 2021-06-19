package io.github.v2lenkagamine.core.init.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ClearGlass_Glow extends Block{
	public ClearGlass_Glow() {
		super(BlockProperties.glowingGlassProps);
	}
    @Override
    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos) {
        return true;
    }
}
