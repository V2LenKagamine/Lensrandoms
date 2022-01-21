package io.github.v2lenkagamine.common.items;

import java.util.List;

import io.github.v2lenkagamine.client.gui.RGBinatorScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

public class RGB_Inator extends Item {	
	public RGB_Inator(Properties properties) {
		super(properties.stacksTo(1));
	}

	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslatableComponent("tooltip.rgb_inator.rgb_inator"));
		if (stack.hasTag()) {
				tooltip.add(new TextComponent(("\u00A74 Red:") + (stack.getTag().getInt("Red") + ("\u00A72 Green:") + (stack.getTag().getInt("Green") + ("\u00A71 Blue:") + (stack.getTag().getInt("Blue"))))));
				
			}
		}
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (!player.isLocalPlayer()) {
			return new InteractionResultHolder<ItemStack>(InteractionResult.FAIL, player.getItemInHand(hand));
		}
		if (hand == InteractionHand.MAIN_HAND) {
			DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> RGBinatorScreen.open(player));
		}
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(hand));
	}
	public static ItemStack RGBInatorSave (ItemStack stack,int red, int green, int blue) {
		  stack.getOrCreateTag().putInt("Red", red);
		  stack.getOrCreateTag().putInt("Green", green);
		  stack.getOrCreateTag().putInt("Blue", blue);
		return stack;
		
	}
}
