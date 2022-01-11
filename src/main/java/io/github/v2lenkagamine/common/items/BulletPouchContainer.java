package io.github.v2lenkagamine.common.items;

import javax.annotation.Nonnull;

import io.github.v2lenkagamine.client.util.ModTags;
import io.github.v2lenkagamine.core.init.ContainersInit;
import io.github.v2lenkagamine.core.items.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class BulletPouchContainer extends AbstractContainerMenu{

	private IItemHandler playerInv;
	private ItemStackHandler bagInv;
	private ItemStack bag;
	
	public BulletPouchContainer(int id,Inventory playerInv) {
		super(ContainersInit.BULLET_POUCH_CONTAINER.get(), id);
		bag = playerInv.player.getMainHandItem();
		bagInv = PouchItem.getHandler(bag);
		this.playerInv = new InvWrapper(playerInv);
		addBagInv(bagInv,0,8,17,9,18);
		layoutPInv(8,47);
		
	}
	
	
	@Override
	public boolean stillValid(Player player) {
		return player.isHolding(Items.TACTICAL_POUCHES.get()) && !bag.isEmpty();
	}
	
	@Override	
	public void removed(Player player) {
		if (!bag.hasTag()) bag.setTag(new CompoundTag());
		bag.getTag().put("inv", bagInv.serializeNBT());
		super.removed(player);
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int slotnum) {
		ItemStack emptyStack = ItemStack.EMPTY;
		Slot slot = slots.get(slotnum);
		if (slot != null && slot.hasItem()) {
			ItemStack stack = slot.getItem();
			stack = stack.copy();
			if (slotnum <9) {
				if (!moveItemStackTo(stack, 9, 52, true)) return emptyStack;
				else { if (stack.is(ModTags.Items.BULLETS)) {
					return emptyStack;
					}
					
				}
			
			}
			if (stack.isEmpty()) slot.set(emptyStack); 
			else slot.setChanged();
			if (stack == emptyStack) return emptyStack;
			slot.onTake(player, stack);
		}
		
		return emptyStack;
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
	private int addBagInv(IItemHandler handler, int index, int x,int y,int amount,int dx) {
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
}
