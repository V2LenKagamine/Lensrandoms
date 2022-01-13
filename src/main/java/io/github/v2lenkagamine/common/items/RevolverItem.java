package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import io.github.v2lenkagamine.common.capabilities.GunAmmoData;
import io.github.v2lenkagamine.common.capabilities.GunTimerData;
import io.github.v2lenkagamine.common.capabilities.TimerAmmoCapabilityProvider;
import io.github.v2lenkagamine.core.items.Items;
import io.github.v2lenkagamine.core.util.WeaponFire;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

public class RevolverItem extends GunItem{
	public RevolverItem(Properties properties) {
		super(properties);
	}
	public int minDmg = Math.toIntExact(Math.round(3*LensRandomsConfig.DAMAGE_MULTI.get()));
	public int maxDmg = Math.toIntExact(Math.round(6*LensRandomsConfig.DAMAGE_MULTI.get()));
	public int maxAmmo = 6;
	public int shotCdTicks = 10;
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack item = playerIn.getItemInHand(handIn);
		LazyOptional<GunTimerData> capTimer = item.getCapability(TimerAmmoCapabilityProvider.GUN_TIMER_CAPABILITY);
		LazyOptional<GunAmmoData> capAmmo = item.getCapability(TimerAmmoCapabilityProvider.GUN_AMMO_CAPABILITY);
		if (playerIn.isCrouching()) {
			reload(item,maxAmmo,playerIn,3,new ItemStack(Items.PISTOL_ROUND.get()));
			return super.use(worldIn, playerIn, handIn);
		}
		else if (capTimer.isPresent() && capAmmo.isPresent())
			{
				if (capTimer.resolve().get().getTimerTicks() < 1){
					if (capAmmo.resolve().get().getAmmoLeft() > 0) {
						WeaponFire.fireNormal(playerIn, 16, minDmg, maxDmg);
						capAmmo.resolve().get().removeAmmo(1);
						capTimer.resolve().get().setTimerTicks(shotCdTicks);
					}
				}
				else {
					reload(item, maxAmmo, playerIn, 3,new ItemStack(Items.PISTOL_ROUND.get()));
				}
			}
		return super.use(worldIn, playerIn, handIn);
	}
}
