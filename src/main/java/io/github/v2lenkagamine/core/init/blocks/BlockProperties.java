package io.github.v2lenkagamine.core.init.blocks;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class BlockProperties {
    public static final BlockBehaviour.Properties stoneProps = BlockBehaviour.Properties.of(Material.STONE)
            .requiresCorrectToolForDrops()
            .strength(1.5f)
            .sound(SoundType.STONE);
    public static final BlockBehaviour.Properties glassProps = BlockBehaviour.Properties.of(Material.GLASS)
				.noOcclusion()
				.isValidSpawn((state,world,pos,entityType) -> false)
				.isRedstoneConductor((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .isViewBlocking((state, world, pos) -> false)
                .sound(SoundType.GLASS)
                .strength(0.3f);
    public static final BlockBehaviour.Properties machineProps = BlockBehaviour.Properties.of(Material.METAL)
    		.strength(3)
    		.sound(SoundType.METAL)
    		.requiresCorrectToolForDrops();  
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
            .strength(1.5f)
            .sound(SoundType.STONE)
    		.lightLevel(b -> 15);
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
