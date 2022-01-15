package io.github.v2lenkagamine.common.items;

import java.util.List;

import io.github.v2lenkagamine.common.entities.BrEEfcaseProjectile;
import io.github.v2lenkagamine.core.init.Sounds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BrEEfcase extends Item{

	public BrEEfcase(Properties properties) {
		super(properties.stacksTo(1));
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslatableComponent("tooltip.breefcase.breefcase"));
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack item =playerIn.getItemInHand(handIn);
		worldIn.playSound(playerIn, playerIn.blockPosition(), Sounds.BREEFCASE.get(), null, 1.0f, 1.0f);
		if (!worldIn.isClientSide) {
			BrEEfcaseProjectile projectile = new BrEEfcaseProjectile(worldIn,playerIn);
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
