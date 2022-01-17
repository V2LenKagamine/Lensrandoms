package io.github.v2lenkagamine.common.containers;

import javax.annotation.Nonnull;

import io.github.v2lenkagamine.client.util.ModTags;
import io.github.v2lenkagamine.common.items.PouchItem;
import io.github.v2lenkagamine.common.util.LockedSlot;
import io.github.v2lenkagamine.core.init.ContainersInit;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class BulletPouchContainer extends AbstractContainerMenu{

	private IItemHandler playerInvHan;
	private IItemHandler bagInv;
	private ItemStack bag;
	
	public BulletPouchContainer(int id,Inventory playerInv) {
		super(ContainersInit.BULLET_POUCH_CONTAINER.get(), id);
		bag = playerInv.player.getMainHandItem();
		bagInv = PouchItem.getHandler(bag);
		this.playerInvHan = new InvWrapper(playerInv);
		addBagInv(bagInv,0,8,18,9,18,3,18);
		addHotbarLocked(playerInvHan, 0, 8, 142, 9, 18, playerInv);
		layoutPInv(playerInv,8,84);
	}
	
	
	@Override
	public boolean stillValid(Player player) {
		return player.isHolding(Items.TACTICAL_POUCHES.get()) && !bag.isEmpty();
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int slotnum) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = slots.get(slotnum);
		if (slot != null && slot.hasItem()) {
			ItemStack itemStack1 = slot.getItem();
			itemStack = itemStack1.copy();
			if (slotnum < 27) {
	            if (!this.moveItemStackTo(itemStack1, 27, this.slots.size(), true)) return ItemStack.EMPTY; 
	            else { if (itemStack.is(ModTags.Items.BULLETS)){
	            	return ItemStack.EMPTY;
	            }
	            return ItemStack.EMPTY;}
			}
			else if (!moveItemStackTo(itemStack1, 0, 27, false)) {return ItemStack.EMPTY;}
				else { if (itemStack.is(ModTags.Items.BULLETS)) {
					return ItemStack.EMPTY;
					}
				}
			if (itemStack1.isEmpty()) slot.set(ItemStack.EMPTY); 
			else slot.setChanged();
		}
		
		return itemStack;
	}
	
	private int addSlotRange(IItemHandler handler, int index, int x,int y,int amount,int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x +=dx;
			index++;
		}
		return index;
	}
	
	private int addSlotRangeBag(IItemHandler handler, int index, int x,int y,int amount,int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y) {
				@Override
				public boolean mayPlace(@Nonnull ItemStack stack) {
					return PouchItem.mayPlaceIn(stack);
				}
			});
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
	
	private int addHotbarLocked(IItemHandler handler, int index, int x,int y,int amount,int dx,Inventory playerInv) {
		for (int k=0;k<amount;k++) {
			if (playerInv.selected == k) {
				addSlot(new LockedSlot(playerInv,index,x,y));
			}
			else {
				addSlot(new SlotItemHandler(handler,index,x,y));
			}
			x+=dx;
			index++;
		}
		
		return index;
	}
	private void layoutPInv(Inventory playerInv,int leftCol,int topRow) {
		addSlotBox(playerInvHan,9,leftCol,topRow,9,18,3,18);
	}
	private int addBagInv(IItemHandler handler,int index,int x, int y, int hor,int dx, int vert, int dy) {
		for (int j=0;j<vert;j++) {
			index = addSlotRangeBag (handler,index,x,y,hor,dx);
			y+=dy;
		}
		return index;
	}
}
