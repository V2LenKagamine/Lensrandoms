package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.GunAmmoData;
import io.github.v2lenkagamine.common.capabilities.GunTimerData;
import io.github.v2lenkagamine.common.capabilities.TimerAmmoCapabilityProvider;
import io.github.v2lenkagamine.core.items.Items;
import io.github.v2lenkagamine.core.util.ItemUtil;
import io.github.v2lenkagamine.core.util.WeaponFire;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

public class GunItemReloadable extends GunItem{

	public GunItemReloadable(Properties properties) {
		super(properties);
	}
	
	public void reload(ItemStack stack,int maxAmmo,LivingEntity ent,int cooldown,ItemStack ammoType) {
		GunAmmoData gunAmmoCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY).resolve().get();
		GunTimerData gunTimerCap = stack.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY).resolve().get();
		if (gunAmmoCap.getAmmoLeft() < maxAmmo) {
			int ammoCons = maxAmmo - gunAmmoCap.getAmmoLeft();
			if(ItemUtil.lookInPouches(ent, ammoType, true, ammoCons)) {
				gunAmmoCap.addAmmo(ammoCons);
				gunTimerCap.setTimer(cooldown);
			}
			else if(ItemUtil.getPlayerItem(ent, ammoType, true, ammoCons)) {
				gunAmmoCap.addAmmo(ammoCons);
				gunTimerCap.setTimer(cooldown);
			}
		}
	}
	
	public InteractionResultHolder<ItemStack> fireReloadable(Player playerIn,Level worldIn,InteractionHand handIn,int maxAmmo,int reloadCd,int shotCdTicks,int range,int minDmg,int maxDmg,boolean pierce) {
	ItemStack item = playerIn.getItemInHand(handIn);
	LazyOptional<GunTimerData> capTimer = item.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY);
	LazyOptional<GunAmmoData> capAmmo = item.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY);
	if (playerIn.isCrouching()) {
		reload(item,maxAmmo,playerIn,reloadCd,new ItemStack(Items.PISTOL_ROUND.get()));
		return super.use(worldIn, playerIn, handIn);
	}
	else if (capTimer.isPresent() && capAmmo.isPresent())
		{
			if (capTimer.resolve().get().getTimerTicks() < 1 ){
				if (capAmmo.resolve().get().getAmmoLeft() > 0) {
					if (!pierce) {WeaponFire.fireNormal(playerIn, range, minDmg, maxDmg);}
					else {WeaponFire.firePierceAll(playerIn, range, minDmg, maxDmg);}
					if (!playerIn.isCreative()) {capAmmo.resolve().get().removeAmmo(1);}
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
	
}
