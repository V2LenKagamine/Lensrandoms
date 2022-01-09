package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.core.init.ContainersInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BulletPouchContainer extends AbstractContainerMenu{

	
	private ItemStackHandler inv;
	private ItemStack pouch;
	private IItemHandler pinv;
	
	
	public BulletPouchContainer(int id,Inventory playerInv) {
		super(ContainersInit.BULLET_POUCH_CONTAINER.get(), id);
		pouch = playerInv.player.getMainHandItem();

		inv = PouchItem.getHandler(pouch);
		layoutPInv(10,70);
		
	}
	@Override
	public boolean stillValid(Player player) {
		return player.getMainHandItem().equals(pouch, false);
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
		addSlotBox(pinv,9,leftCol,topRow,9,18,3,18);
		topRow+=58;
		addSlotRange(pinv,0,leftCol,topRow,9,18);
	}
	
}
