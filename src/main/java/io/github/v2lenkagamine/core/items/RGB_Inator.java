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
				tooltip.add(new StringTextComponent(("\u00A7f") + (stack.getTag())));
				
			}
		}
	@SuppressWarnings("deprecation")
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (player.isUser()) {
			DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> RGBinatorScreen.open(player));
		}
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	public static ItemStack RGBInatorSave (ItemStack stack,double red, double green, double blue) {
		  stack.getOrCreateTag().putDouble("Red", red);
		  stack.getOrCreateTag().putDouble("Green", green);
		  stack.getOrCreateTag().putDouble("Blue", blue);
		return stack;
		
	}
}
