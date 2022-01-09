package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.items.BulletPouchContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainersInit {
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Lensrandoms.MOD_ID);
	
	public static final RegistryObject<MenuType<BulletPouchContainer>> BULLET_POUCH_CONTAINER = CONTAINERS.register("bullet_pouch", () -> IForgeMenuType.create((id,playerInv,data) -> {return new BulletPouchContainer(id, playerInv);}));
}
