package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.common.capabilities.CapabilityBulletPouch;
import io.github.v2lenkagamine.common.containers.BulletPouchContainer;
import io.github.v2lenkagamine.core.util.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;

public class PouchItem extends Item implements MenuProvider{
	
	
	public PouchItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
		
	         return new CapabilityBulletPouch(27);
		
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level world,Player player,InteractionHand hand) {
		if (!world.isClientSide) NetworkHooks.openGui((ServerPlayer) player, this);
		return new InteractionResultHolder<ItemStack>(InteractionResult.PASS, player.getItemInHand(hand));
	}
	

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
		return new BulletPouchContainer(id,inv);
	}

	@Override
	public Component getDisplayName() {
		return new TranslatableComponent(this.getDescriptionId());
	}
	
	public static IItemHandler getHandler(ItemStack stack) {
		return stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
	}
	public static boolean mayPlaceIn(ItemStack item) {
		return !item.isEmpty() && item.is(ModTags.Items.BULLETS);
	}
	
}
