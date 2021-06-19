package io.github.v2lenkagamine.core.init.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockProperties {
    public static final AbstractBlock.Properties stoneProps = AbstractBlock.Properties.create(Material.ROCK)
            .harvestLevel(0)
            .hardnessAndResistance(1.5f)
            .sound(SoundType.STONE)
            .harvestTool(ToolType.PICKAXE);
    public static final AbstractBlock.Properties glassProps = AbstractBlock.Properties.create(Material.GLASS)
				.notSolid()
				.setAllowsSpawn((state,world,pos,entityType) -> false)
				.setOpaque((state, world, pos) -> false)
                .setSuffocates((state, world, pos) -> false)
                .setBlocksVision((state, world, pos) -> false)
                .sound(SoundType.GLASS)
                .hardnessAndResistance(0.3f);
    public static final AbstractBlock.Properties machineProps = AbstractBlock.Properties.create(Material.IRON)
    		.harvestLevel(0)
    		.hardnessAndResistance(3)
    		.sound(SoundType.METAL)
    		.setRequiresTool()
    		.harvestTool(ToolType.PICKAXE);
    
    //The following is useful for glass type blocks.
    /*
    @Override
    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos) {
        return true;
    }
    @Nullable
	@Override
	public float[] getBeaconColorMultiplier(BlockState state, IWorldReader world, BlockPos pos, BlockPos beaconPos) {
		return colorComponents;
	}
     */
    public static final AbstractBlock.Properties glowingStoneProps = AbstractBlock.Properties.create(Material.ROCK)
            .harvestLevel(0)
            .hardnessAndResistance(1.5f)
            .sound(SoundType.STONE)
    		.setLightLevel(b -> 15)
    		.harvestTool(ToolType.PICKAXE);
    public static final AbstractBlock.Properties glowingGlassProps = AbstractBlock.Properties.create(Material.GLASS)
			.notSolid()
			.setAllowsSpawn((state,world,pos,entityType) -> false)
			.setOpaque((state, world, pos) -> false)
            .setSuffocates((state, world, pos) -> false)
            .setBlocksVision((state, world, pos) -> false)
            .setLightLevel(b -> 15)
            .sound(SoundType.GLASS)
            .sound(SoundType.GLASS).hardnessAndResistance(0.3f);
}
