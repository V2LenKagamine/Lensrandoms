package io.github.v2lenkagamine.common.items;

import io.github.v2lenkagamine.core.init.ContainersInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class BulletPouchContainer extends AbstractContainerMenu{

	private ItemStack pouch;
	private IItemHandler playerInv;
	
	
	public BulletPouchContainer(int id,Inventory playerInv) {
		super(ContainersInit.BULLET_POUCH_CONTAINER.get(), id);
		this.playerInv = new InvWrapper(playerInv);
		
		
		
		
		
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
		addSlotBox(playerInv,9,leftCol,topRow,9,18,3,18);
		topRow+=58;
		addSlotRange(playerInv,0,leftCol,topRow,9,18);
	}
	
}
