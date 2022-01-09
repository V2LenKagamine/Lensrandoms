package io.github.v2lenkagamine.client.gui;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.client.util.ModTags;
import io.github.v2lenkagamine.common.items.PouchItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BulletPouchContainer extends AbstractContainerMenu{

	private ResourceLocation GUI = new ResourceLocation(Lensrandoms.MOD_ID, "textures/gui/bullet_pouch_GUI.png");
	
	private ItemStackHandler inv;
	private InteractionHand handIn;
	private ItemStack pouch;
	
	
	
	public BulletPouchContainer(int id,Inventory playerInv) {
		super(, id);
		pouch = playerInv.player.getMainHandItem();
		handIn = InteractionHand.MAIN_HAND;
		if (pouch.isEmpty() || !(pouch.getItem() instanceof PouchItem)) {
			pouch = playerInv.player.getOffhandItem();
			handIn = InteractionHand.OFF_HAND;
		}

		inv = PouchItem.getHandler(pouch);
		
	}
	@Override
	public boolean stillValid(Player player) {
		return false;
	}
	private int addSlotRange(IItemHandler handler, int index, int x,int y,int amount,int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x +=dx;
			index++;
		}
		return index;
	}
	private int addSlotBox(IItemHandler handler,int index,int x, int y, int hor,int dx, int vert, int dy) {
		for (int j=0;j<vert;j++) {
			index = addSlotRange (handler,index,x,y,hor,dx);
			y+=dy;
		}
		return index;
	}

	
	private void layoutPInv(int leftCol,int topRow) {
		addSlotBox(,9,leftCol,topRow,9,18,3,18);
		topRow+=58;
		addSlotRange(,0,leftCol,topRow,9,18);
	}
	
}
