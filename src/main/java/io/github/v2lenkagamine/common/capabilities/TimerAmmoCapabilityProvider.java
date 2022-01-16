package io.github.v2lenkagamine.common.capabilities;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TimerAmmoCapabilityProvider implements ICapabilitySerializable<CompoundTag>{
	
	public static final Capability<GunTimerData> GUN_TIMER_CAPABILITY = CapabilityManager.get(new CapabilityToken<GunTimerData>() {} );
	public static final Capability<GunAmmoData> GUN_AMMO_CAPABILITY = CapabilityManager.get(new CapabilityToken<GunAmmoData>() {} );
	
	final GunTimerData dataTimer = new GunTimerData();
	final GunAmmoData dataAmmo = new GunAmmoData();
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return getCapability(cap);
	}
	@SuppressWarnings("unchecked")
	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
		if (cap == GUN_TIMER_CAPABILITY)
			return (LazyOptional<T>) LazyOptional.of(() -> dataTimer);
		
		else if (cap == GUN_AMMO_CAPABILITY) {
			return (LazyOptional<T>) LazyOptional.of(() -> dataAmmo);
		}
		return LazyOptional.empty();
	}
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("ammo",dataAmmo.getAmmoLeft());
		tag.putInt("maxAmmo", dataAmmo.getMaxAmmo());
		//A note, TTF == Time To Fire.
		tag.putInt("ttf", dataTimer.getTimerTicks());
		return tag;
	}
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		dataAmmo.setAmmo(nbt.getInt("ammo"));
		dataTimer.setTimerTicks(nbt.getInt("ttf"));
		dataAmmo.setMaxAmmo(nbt.getInt("maxAmmo"));
	}
}
