package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

public class Sounds {

	
	public static final RegistryObject<SoundEvent> GUN_CLICK = RegistryHelper.SOUNDS.register("gun_click", () -> new SoundEvent(new ResourceLocation(Lensrandoms.MOD_ID,"gun_click")));
	public static final RegistryObject<SoundEvent> BREEFCASE = RegistryHelper.SOUNDS.register("breefcase", () -> new SoundEvent(new ResourceLocation(Lensrandoms.MOD_ID,"breefcase")));
	public static final RegistryObject<SoundEvent> RELOAD_BASE = RegistryHelper.SOUNDS.register("reload_base", () -> new SoundEvent(new ResourceLocation(Lensrandoms.MOD_ID,"reload_base")));
	public static final RegistryObject<SoundEvent> SHOT_BASE = RegistryHelper.SOUNDS.register("shot_base", () -> new SoundEvent(new ResourceLocation(Lensrandoms.MOD_ID,"shot_base")));
	public static final RegistryObject<SoundEvent> FIFTY_CAL = RegistryHelper.SOUNDS.register("fifty_cal", () -> new SoundEvent(new ResourceLocation(Lensrandoms.MOD_ID,"fifty_cal")));
	public static final RegistryObject<SoundEvent> LASER_BASE = RegistryHelper.SOUNDS.register("laser_base", () -> new SoundEvent(new ResourceLocation(Lensrandoms.MOD_ID,"laser_base")));
	
	
	//No touchy
	public static void register() {
	}
}
