package io.github.v2lenkagamine.common.items;

import java.util.List;

import io.github.v2lenkagamine.common.capabilities.GunAmmoData;
import io.github.v2lenkagamine.common.capabilities.GunTimerData;
import io.github.v2lenkagamine.common.capabilities.LensEnergyData;
import io.github.v2lenkagamine.common.capabilities.TimerAmmoCapabilityProvider;
import io.github.v2lenkagamine.common.capabilities.TimerEnergyCapabilityProvider;
import io.github.v2lenkagamine.core.init.Sounds;
import io.github.v2lenkagamine.core.util.ItemUtil;
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
import net.minecraftforge.registries.RegistryObject;

public abstract class GunItem extends Item{

	public GunItem(Properties properties,int maxdura) {
		super(properties.stacksTo(1).defaultDurability(maxdura));
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	
	public boolean canBeDepleted() {
	      return false;
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
	         return new TimerAmmoCapabilityProvider();
	}
	
	@Override
	 public boolean isBarVisible(ItemStack pStack) {
		if (pStack.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY).isPresent()) 
		{return (pStack.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY).resolve().get().getMaxAmmo() > 1);}
		else return false;
	   }
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(isSelected) {
			LazyOptional<GunTimerData> timerCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY);
			LazyOptional<GunAmmoData> ammoCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY);
			LazyOptional<LensEnergyData> energyCap = stack.getCapability(TimerEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY);
			if (ammoCap.isPresent())
			{
				if (timerCap.resolve().get().getTimerTicks() == 1 && ammoCap.resolve().get().isAmmoFull()) {
					if (entityIn instanceof Player player && !player.isCreative()) {playReload(player,worldIn);}
					else
					playReload(entityIn, worldIn);
				}
				if (timerCap.resolve().get().getTimerTicks() > 0) 
				{
					timerCap.resolve().get().timerDown();
				}
			}
			if (energyCap.isPresent()) {
				if (timerCap.resolve().get().getTimerTicks() > 0) 
				{
					timerCap.resolve().get().timerDown();
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
	
	public void reload(ItemStack stack,LivingEntity ent,int cooldown,int maxAmmo,ItemStack ammoType,Boolean doConsume) {
		GunAmmoData gunAmmoCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY).resolve().get();
		GunTimerData gunTimerCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY).resolve().get();
		int ammoCons = gunAmmoCap.getMaxAmmo() - gunAmmoCap.getAmmoLeft();
		if (gunAmmoCap.getMaxAmmo()==0) {
			gunAmmoCap.setMaxAmmo(maxAmmo);
		}
		if (gunAmmoCap.getAmmoLeft() < gunAmmoCap.getMaxAmmo() && doConsume) {
			if(ItemUtil.lookInPouches(ent, ammoType, doConsume, ammoCons)) {
				gunAmmoCap.addAmmo(ammoCons);
				gunTimerCap.setTimerTicks(cooldown);
				stack.setDamageValue(0);
			}
			else if(ItemUtil.getPlayerItemStack(ent, ammoType, doConsume, ammoCons)) {
				gunAmmoCap.addAmmo(ammoCons);
				gunTimerCap.setTimerTicks(cooldown);
				stack.setDamageValue(0);
			}
		}
		else {
			gunAmmoCap.addAmmo(ammoCons);
			gunTimerCap.setTimerTicks(cooldown);
			stack.setDamageValue(0);
		}
	}
	
	public InteractionResultHolder<ItemStack> fireWeapon(Player playerIn,Level worldIn,InteractionHand handIn,int maxAmmo,int reloadCdTicks,int shotCdTicks,int range,int minDmg,int maxDmg,RegistryObject<Item> ammoType,boolean pierce,boolean doConsume) {
	ItemStack item = playerIn.getItemInHand(handIn);
	LazyOptional<GunTimerData> capTimer = item.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY);
	LazyOptional<GunAmmoData> capAmmo = item.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY);
	if (playerIn.isCrouching()) {
		if(doConsume){reload(item,playerIn,reloadCdTicks,maxAmmo,new ItemStack(ammoType.get()), true);}
		else reload(item,playerIn,reloadCdTicks,maxAmmo,new ItemStack(ammoType.get()), false);
		return super.use(worldIn, playerIn, handIn);
	}
	else if (capTimer.isPresent() && capAmmo.isPresent())
		{
			if (capTimer.resolve().get().getTimerTicks() < 1 ){
				if (capAmmo.resolve().get().getAmmoLeft() > 0) {
					if (!pierce) {WeaponFire.fireNormal(playerIn, range, minDmg, maxDmg);}
					else {WeaponFire.firePierceAll(playerIn, range, minDmg, maxDmg);}
					if (!playerIn.isCreative()) {capAmmo.resolve().get().removeAmmo(1);}
					item.setDamageValue(capAmmo.resolve().get().getMaxAmmo() - capAmmo.resolve().get().getAmmoLeft());
					capTimer.resolve().get().setTimerTicks(shotCdTicks);
					playShot(playerIn, worldIn);
				} 
				else {
					playEmpty(playerIn, worldIn);
				}
			}
		}
	return super.use(worldIn, playerIn, handIn);
}
	
	public boolean usesAmmo(List<? extends String> noAmmoList,String name) {
		Boolean nameMatches = false;
		for(String string : noAmmoList) {
			if (string.equals(name))  {
				nameMatches = true;
				break;
			} 
		}
	return nameMatches;
	}
}
