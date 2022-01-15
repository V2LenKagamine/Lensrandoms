package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.entities.BeeCaseProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BeeCase extends Item{
	public BeeCase(Properties properties) {
		super(properties.stacksTo(1));
	}
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack item = playerIn.getItemInHand(handIn);
		if (!worldIn.isClientSide) {
			BeeCaseProjectile projectile = new BeeCaseProjectile(worldIn,playerIn);
			projectile.setItem(item);
			projectile.shootFromRotation(playerIn,playerIn.getXRot(), playerIn.getYRot(), 0.0f, 1.5f, 1.0f);
			worldIn.addFreshEntity(projectile);
		}
		if (!playerIn.getAbilities().instabuild) {
			item.shrink(1);
		}
		return InteractionResultHolder.sidedSuccess(item,worldIn.isClientSide);
	}
	
}
