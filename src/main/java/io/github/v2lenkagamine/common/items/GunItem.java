package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.GunAmmoData;
import io.github.v2lenkagamine.common.capabilities.GunTimerData;
import io.github.v2lenkagamine.common.capabilities.TimerAmmoCapabilityProvider;
import io.github.v2lenkagamine.core.init.Sounds;
import io.github.v2lenkagamine.core.util.WeaponFire;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public abstract class GunItem extends Item{


	public GunItem(Properties properties) {
		super(properties.stacksTo(1));
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
		
	         return new TimerAmmoCapabilityProvider();
	         
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(isSelected) {
			LazyOptional<GunTimerData> lazyCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY);
			LazyOptional<GunAmmoData> ammoCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY);
			if (lazyCap.isPresent())
			{
				if (lazyCap.resolve().get().getTimerTicks() == 1 && ammoCap.resolve().get().getAmmoLeft() == 0) {
					playReload(entityIn, worldIn);
				}
				if (lazyCap.resolve().get().getTimerTicks() > 0) 
				{
					lazyCap.resolve().get().timerDown();
				}
				
			}
		}		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	public void playEmpty(LivingEntity ent,Level world) {
		Player player = null;
		if(ent instanceof Player) {
		player = (Player) ent;
		}
		world.playSound(player, ent.blockPosition(), Sounds.GUN_CLICK.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
	}
	
	public void playReload(Entity ent,Level world) {
		Player player = null;
		if(ent instanceof Player) {
		player = (Player) ent;
		}
		world.playSound(player, ent.blockPosition(), Sounds.RELOAD_BASE.get(),SoundSource.PLAYERS, 1.0f, 1.0f);
	}

	public void playShot(Entity ent,Level world) {
		Player player = null;
		if(ent instanceof Player) {
		player = (Player) ent;
		world.playSound(player, ent.blockPosition(), Sounds.SHOT_BASE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
		}
	}
	public InteractionResultHolder<ItemStack> fireWeapon(Player playerIn, Level worldIn, InteractionHand handIn, int shotCdTicks, int range, int minDmg, int maxDmg,boolean pierce) {
		ItemStack item = playerIn.getItemInHand(handIn);
		LazyOptional<GunTimerData> capTimer = item.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY);
		if (capTimer.isPresent())
		{
			if (capTimer.resolve().get().getTimerTicks() < 1 ){
				if (!pierce) {WeaponFire.fireNormal(playerIn, range, minDmg, maxDmg);}
				else {WeaponFire.firePierceAll(playerIn, range, minDmg, maxDmg);}
				capTimer.resolve().get().setTimerTicks(shotCdTicks);
				playShot(playerIn, worldIn);
			}
			else {
				playEmpty(playerIn, worldIn);
			}
		}
	return super.use(worldIn, playerIn, handIn);
	}
}
