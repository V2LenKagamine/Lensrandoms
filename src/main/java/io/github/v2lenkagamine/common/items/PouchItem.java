package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.client.gui.BulletPouchContainer;
import io.github.v2lenkagamine.common.capabilities.bulletpouch.CapabilityBulletPouch;
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
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

public class PouchItem extends Item implements MenuProvider{
	
	
	public PouchItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
		
	         return new CapabilityBulletPouch(9);
		
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level world,Player player,InteractionHand hand) {
		if (!world.isClientSide) NetworkHooks.openGui((ServerPlayer) player, this);
		return new InteractionResultHolder<ItemStack>(InteractionResult.PASS, player.getItemInHand(hand));
	}
	
	public static ItemStackHandler getHandler(ItemStack stack) {
		if (stack.isEmpty())
			return null;
		ItemStackHandler handler = new ItemStackHandler(104);
		if (stack.hasTag()) handler.deserializeNBT(stack.getTag().getCompound("inv"));
		return handler;
		
		
		
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
		return new BulletPouchContainer(id,inv);
	}

	@Override
	public Component getDisplayName() {
		return new TranslatableComponent(this.getDescriptionId());
	}
	
	
}
