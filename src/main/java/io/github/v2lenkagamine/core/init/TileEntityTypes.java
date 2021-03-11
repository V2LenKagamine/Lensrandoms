package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.tileentity.PowerHoleTE;
import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import io.github.v2lenkagamine.core.init.blocks.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypes {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Lensrandoms.MOD_ID);
	//Powerhole
	public static final RegistryObject<TileEntityType<PowerHoleTE>> POWER_HOLE_TE = TILE_ENTITY_TYPE.register("powerhole", 
			() -> TileEntityType.Builder.create(PowerHoleTE::new, Blocks.POWER_HOLE.get()).build(null));
	//RGBlock
	public static final RegistryObject<TileEntityType<RGBlockTE>> RGBLOCK_TE = TILE_ENTITY_TYPE.register("rgblock", 
			() -> TileEntityType.Builder.create(RGBlockTE::new, Blocks.RGBLOCK.get()).build(null));
	
	
}
