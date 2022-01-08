package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import io.github.v2lenkagamine.core.init.blocks.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TileEntityTypes {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Lensrandoms.MOD_ID);
	
	//RGBlocks
	public static final RegistryObject<BlockEntityType<RGBlockTE>> RGBLOCK_TE = BLOCK_ENTITY_TYPE.register("rgblock",
			() -> BlockEntityType.Builder.of(RGBlockTE::new, Blocks.RGBLOCK.get(),Blocks.RGBLOCK_STAIR.get(),Blocks.RGBLOCK_SLAB.get(),Blocks.RGBLOCK_GLOW.get(),Blocks.RGBLOCK_BRICK.get(),
			Blocks.RGBLOCK_BRICK.get(),Blocks.RGBLOCK_BRICK_GLOW.get(),Blocks.RGBLOCK_BRICK_SLAB.get(),Blocks.RGBLOCK_BRICK_STAIR.get(),Blocks.RGBLOCK_GLASS.get(),Blocks.RGBLOCK_GLASS_BORDER.get()
			,Blocks.RGBLOCK_GLASS_BORDER_GLOW.get(),Blocks.RGBLOCK_GLASS_GLOW.get()).build(null));
	
	
	
}
