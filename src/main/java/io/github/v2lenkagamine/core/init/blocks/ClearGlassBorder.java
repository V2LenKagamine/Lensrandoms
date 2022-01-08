package io.github.v2lenkagamine.core.init.blocks;

import javax.annotation.Nonnull;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class ClearGlassBorder extends GlassBlock{

	public ClearGlassBorder() {
		super(BlockProperties.glassProps);
	}
    @Override
    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return true;
    }
}
