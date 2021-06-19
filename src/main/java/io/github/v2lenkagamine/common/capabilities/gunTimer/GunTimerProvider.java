package io.github.v2lenkagamine.common.capabilities.gunTimer;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class GunTimerProvider implements ICapabilityProvider{
	
	
	private final GunTimerDefault gunTimer = new GunTimerDefault();
	private final LazyOptional<IGunTimer> gunTimerOptional = LazyOptional.of(() -> gunTimer);
	public static final Capability<IGunTimer> INSTANCE = null;
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		return INSTANCE == cap ? gunTimerOptional.cast() : LazyOptional.empty();
	}
	public static IGunTimer getOrDefault(ItemStack stack, Direction side) {
		return stack.getCapability(INSTANCE, side).orElse(new GunTimerDefault());
	}
}
		
