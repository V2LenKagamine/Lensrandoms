package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.tileentity.ChargerTE;
import io.github.v2lenkagamine.common.tileentity.GunSmithingTableTE;
import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import io.github.v2lenkagamine.core.init.blocks.LensBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TileEntityTypes {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Lensrandoms.MOD_ID);
	
	//RGBlocks
	public static final RegistryObject<BlockEntityType<RGBlockTE>> RGBLOCKTE = BLOCK_ENTITY_TYPE.register("rgblock",
			() -> BlockEntityType.Builder.of(RGBlockTE::new, LensBlocks.RGBLOCK.get(),LensBlocks.RGBLOCK_STAIR.get(),LensBlocks.RGBLOCK_SLAB.get(),LensBlocks.RGBLOCK_GLOW.get(),LensBlocks.RGBLOCK_BRICK.get(),
			LensBlocks.RGBLOCK_BRICK.get(),LensBlocks.RGBLOCK_BRICK_GLOW.get(),LensBlocks.RGBLOCK_BRICK_SLAB.get(),LensBlocks.RGBLOCK_BRICK_STAIR.get(),LensBlocks.RGBLOCK_GLASS.get(),LensBlocks.RGBLOCK_GLASS_BORDER.get()
			,LensBlocks.RGBLOCK_GLASS_BORDER_GLOW.get(),LensBlocks.RGBLOCK_GLASS_GLOW.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<ChargerTE>> CHARGERTE = BLOCK_ENTITY_TYPE.register("charger", () -> BlockEntityType.Builder.of(ChargerTE::new, LensBlocks.CHARGER.get()).build(null));
	public static final RegistryObject<BlockEntityType<GunSmithingTableTE>> GUNSMITHINGTABLETE = BLOCK_ENTITY_TYPE.register("gunsmithingtable", () -> BlockEntityType.Builder.of(GunSmithingTableTE::new, LensBlocks.GUNSMITHING_TABLE.get()).build(null));
	
}
