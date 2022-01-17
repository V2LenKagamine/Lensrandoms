package io.github.v2lenkagamine.common.containers;

import io.github.v2lenkagamine.common.capabilities.LensEnergyCapabilityProvider;
import io.github.v2lenkagamine.common.capabilities.LensEnergyData;
import io.github.v2lenkagamine.core.init.ContainersInit;
import io.github.v2lenkagamine.core.init.blocks.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ChargerContainer extends AbstractContainerMenu{
	
	private BlockEntity tileEntity;
	private IItemHandler playerInvHan;
	
	
	public ChargerContainer(int id,Level world,BlockPos pos,Inventory playerInv,Player player) {
		super(ContainersInit.CHARGER_CONTAINER.get(), id);
		tileEntity = world.getBlockEntity(pos);
		this.playerInvHan = new InvWrapper(playerInv);
		if (tileEntity != null) {
			tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
				addSlot(new SlotItemHandler(h,0,80,18));
				addSlot(new SlotItemHandler(h,1,80,54) {
					
				});
			});
		}
		
		layoutPInv(playerInv,8,84);
		trackPower();
	}

	public int getEnergy() {
		return tileEntity.getCapability(LensEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY).map(LensEnergyData::getEnergyStored).orElse(0);
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(stack, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                if (stack.getItem() == Items.REDSTONE) {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) {
                    if (!this.moveItemStackTo(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 38 && !this.moveItemStackTo(stack, 2, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

		return itemstack;
	}
	
	@Override
	public boolean stillValid(Player pPlayer) {
		return stillValid(ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos()),pPlayer,Blocks.CHARGER.get());
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
	
	private void trackPower() {
		
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getEnergy() & 0xffff;
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(LensEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0xffff0000;
                    ((LensEnergyData)h).setEnergy(energyStored + (value & 0xffff));
                });
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(LensEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0x0000ffff;
                    ((LensEnergyData)h).setEnergy(energyStored | (value << 16));
                });
            }
        });
    }
	
}
