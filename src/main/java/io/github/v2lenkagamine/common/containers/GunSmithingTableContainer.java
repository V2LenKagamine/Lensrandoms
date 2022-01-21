package io.github.v2lenkagamine.common.containers;

import io.github.v2lenkagamine.common.tileentity.GunSmithingTableTE;
import io.github.v2lenkagamine.core.init.ContainersInit;
import io.github.v2lenkagamine.core.init.blocks.LensBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class GunSmithingTableContainer extends AbstractContainerMenu{

	private BlockEntity tileEntity;
	private IItemHandler playerInvHan;
	private GunSmithingTableTE specificTE;
	private BlockPos pos;
	
	
	public GunSmithingTableContainer(int id,Level world,BlockPos pos,Inventory playerInv,Player player) {
		super(ContainersInit.GUNSMITHINGTABLE_CONTAINER.get(),id);
		tileEntity = world.getBlockEntity(pos);
		specificTE = (GunSmithingTableTE) world.getBlockEntity(pos);
		this.pos = pos;
		this.playerInvHan = new InvWrapper(playerInv);
		if (tileEntity != null) {
			tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
				addSlotRange(h,0,116,35,3,18);
				addSlotRange(h,3,116,53,3,18);
				addSlotRange(h,6,116,71,3,18);
			});
		}
		
		
		
		layoutPInv(playerInv,8,102);
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int slotnum) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = slots.get(slotnum);
		if (slot != null && slot.hasItem()) {
			ItemStack itemStack1 = slot.getItem();
			itemStack = itemStack1.copy();
			if (slotnum < 9) {
	            if (!this.moveItemStackTo(itemStack1, 9, this.slots.size(), true)) return ItemStack.EMPTY; 
			}
			else if (!moveItemStackTo(itemStack1, 9, 36, false)) {return ItemStack.EMPTY;}
			if (itemStack1.isEmpty()) slot.set(ItemStack.EMPTY); 
			else slot.setChanged();
		}
		
		return itemStack;
	}

	@Override
	public boolean stillValid(Player pPlayer) {
		return stillValid(ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos()),pPlayer,LensBlocks.GUNSMITHING_TABLE.get());
	}
	
	public GunSmithingTableTE getTable() {
		return specificTE;
	}
	
	public BlockPos getPos() {
		return pos;
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
	
	private void layoutPInv(Inventory playerInv,int leftCol,int topRow) {
		// Player inventory
        addSlotBox(playerInvHan, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInvHan, 0, leftCol, topRow, 9, 18);
	}


}
