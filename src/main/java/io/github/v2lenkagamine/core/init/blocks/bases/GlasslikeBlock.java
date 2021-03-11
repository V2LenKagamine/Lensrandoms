package io.github.v2lenkagamine.core.init.blocks.bases;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
// import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class GlasslikeBlock extends Block{

	private final float[] colorComponents;
	//private final Vector3d colorVector;
	
	public GlasslikeBlock(Properties properties,int color,MaterialColor materialColor) {
		super(Properties.create(Material.GLASS, materialColor)
				.notSolid()
				.setAllowsSpawn((state,world,pos,entityType) -> false)
				.setOpaque((state, world, pos) -> false)
                .setSuffocates((state, world, pos) -> false)
                .setBlocksVision((state, world, pos) -> false));
		
		float r = ((color >> 16) & 0xff) / 255f;
		float g = ((color >> 8) & 0xff) / 255f;
		float b = (color & 0xff) / 255f;
		colorComponents = new float[]{r, g, b};
	//	colorVector = new Vector3d(r, g, b);
		
	}
    @Override
    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos) {
        return true;
    }
    @Nullable
	@Override
	public float[] getBeaconColorMultiplier(BlockState state, IWorldReader world, BlockPos pos, BlockPos beaconPos) {
		return colorComponents;
	}
}
