package io.github.v2lenkagamine.core.init.blocks;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockProperties {
    public static final BlockBehaviour.Properties stoneProps = BlockBehaviour.Properties.of(Material.STONE)
            .harvestLevel(0)
            .strength(1.5f)
            .sound(SoundType.STONE)
            .harvestTool(ToolType.PICKAXE);
    public static final BlockBehaviour.Properties glassProps = BlockBehaviour.Properties.of(Material.GLASS)
				.noOcclusion()
				.isValidSpawn((state,world,pos,entityType) -> false)
				.isRedstoneConductor((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .isViewBlocking((state, world, pos) -> false)
                .sound(SoundType.GLASS)
                .strength(0.3f);
    public static final BlockBehaviour.Properties machineProps = BlockBehaviour.Properties.of(Material.METAL)
    		.harvestLevel(0)
    		.strength(3)
    		.sound(SoundType.METAL)
    		.requiresCorrectToolForDrops()
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
    public static final BlockBehaviour.Properties glowingStoneProps = BlockBehaviour.Properties.of(Material.STONE)
            .harvestLevel(0)
            .strength(1.5f)
            .sound(SoundType.STONE)
    		.lightLevel(b -> 15)
    		.harvestTool(ToolType.PICKAXE);
    public static final BlockBehaviour.Properties glowingGlassProps = BlockBehaviour.Properties.of(Material.GLASS)
			.noOcclusion()
			.isValidSpawn((state,world,pos,entityType) -> false)
			.isRedstoneConductor((state, world, pos) -> false)
            .isSuffocating((state, world, pos) -> false)
            .isViewBlocking((state, world, pos) -> false)
            .lightLevel(b -> 15)
            .sound(SoundType.GLASS)
            .sound(SoundType.GLASS).strength(0.3f);
}
