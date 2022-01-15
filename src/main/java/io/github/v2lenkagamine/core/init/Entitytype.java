package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.common.entities.BeeCaseProjectile;
import io.github.v2lenkagamine.common.entities.BrEEfcaseProjectile;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public class Entitytype {

	public static final RegistryObject<EntityType<BrEEfcaseProjectile>> BREEFCASE_PROJECTILE = RegistryHelper.ENTITIES.register("breefcase",() ->
	EntityType.Builder.<BrEEfcaseProjectile>of(BrEEfcaseProjectile::new,MobCategory.MISC)
	.sized(0.5f, 0.5f)
	.build("breefcase"));
	public static final RegistryObject<EntityType<BeeCaseProjectile>> BEECASE_PROJECTILE = RegistryHelper.ENTITIES.register("beecase", () ->
	EntityType.Builder.<BeeCaseProjectile>of(BeeCaseProjectile::new,MobCategory.MISC)
	.sized(0.5f,0.5f)
	.build("beecase"));
	
	
	
	
	//NO TOUCHY
	public static void register() {
	}
}
