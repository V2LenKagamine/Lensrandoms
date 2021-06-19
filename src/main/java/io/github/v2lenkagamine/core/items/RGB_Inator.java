package io.github.v2lenkagamine.core.items;

import java.util.List;

import io.github.v2lenkagamine.client.gui.RGBinatorScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

public class RGB_Inator extends Item {	
	public RGB_Inator(Properties properties) {
		super(properties);
	}

	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslationTextComponent("tooltip.rgb_inator.rgb_inator"));
		if (stack.hasTag()) {
				tooltip.add(new StringTextComponent(("\u00A74 Red:") + (stack.getTag().getInt("Red") + ("\u00A72 Green:") + (stack.getTag().getInt("Green") + ("\u00A71 Blue:") + (stack.getTag().getInt("Blue"))))));
				
			}
		}
	@SuppressWarnings("deprecation")
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (!player.isUser()) {
			return new ActionResult<ItemStack>(ActionResultType.FAIL, player.getHeldItem(hand));
		}
		if (hand == Hand.MAIN_HAND) {
			DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> RGBinatorScreen.open(player));
		}
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	public static ItemStack RGBInatorSave (ItemStack stack,int red, int green, int blue) {
		  stack.getOrCreateTag().putInt("Red", red);
		  stack.getOrCreateTag().putInt("Green", green);
		  stack.getOrCreateTag().putInt("Blue", blue);
		return stack;
		
	}
}
