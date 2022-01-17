package io.github.v2lenkagamine.common.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.capabilities.LensEnergyCapabilityProvider;
import io.github.v2lenkagamine.common.capabilities.LensEnergyData;
import io.github.v2lenkagamine.core.init.TileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ChargerTE extends BlockEntity{

	private ItemStackHandler itemHandler = createItemHandler(2);
	private LensEnergyData energyStorage = new LensEnergyData(100000,0,10,0);
	
	private LazyOptional<IItemHandler> handler = LazyOptional.of(()->itemHandler);
	private LazyOptional<LensEnergyData> energy = LazyOptional.of(()->energyStorage);
	
	public ChargerTE(BlockPos pWorldPosition, BlockState pBlockState) {
		super(TileEntityTypes.CHARGERTE.get(), pWorldPosition, pBlockState);
	}

	private ItemStackHandler createItemHandler(int slots) {

		return new ItemStackHandler(slots) {
			@Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            	if (slot == 1) {return stack.getItem() == Items.REDSTONE;}
            	return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            	if (slot == 1) {
            		if (stack.getItem() != Items.REDSTONE) {
            			return stack;
            		}
            	}
            return super.insertItem(slot, stack, simulate);
            }
        };
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		handler.invalidate();
		energy.invalidate();
	}
	
	public void tickServer() {
		
		if (energyStorage.getEnergyStored() <= (energyStorage.getMaxEnergyStored()-1000)) {
			ItemStack stack = itemHandler.getStackInSlot(1);
			if (stack.getItem() == Items.REDSTONE) {
				itemHandler.extractItem(1, 1, false);
				energyStorage.addEnergy(1000);
				setChanged();
			}
		}
		
		if (energyStorage.getEnergyStored() >= 10) {
			ItemStack charge = itemHandler.getStackInSlot(0);
			if(charge.getCapability(LensEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY).isPresent()) {
				charge.getCapability(LensEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY).resolve().get().addEnergy(10);
				energyStorage.removeEnergy(10);
				setChanged();
			}
		}
		
	}
	
	
	@Override
	public void load(CompoundTag tag) {
			if(tag.contains("inv")) {
				itemHandler.deserializeNBT(tag.getCompound("inv"));
			}
			if (tag.contains("energy")) {
				energyStorage.setEnergy(tag.getInt("energy"));
			}
			if (tag.contains("capacity")) {
				energyStorage.setCapacity(tag.getInt("capacity"));
			}
			if (tag.contains("send")) {
				energyStorage.setExtractMax(tag.getInt("send"));
			}
			if (tag.contains("receive")) {
				energyStorage.setRecieveMax(tag.getInt("receive"));
			}
	}
	public void saveAdditional(CompoundTag tag) {
		tag.putInt("energy", energyStorage.getEnergyStored());
		tag.putInt("capacity", energyStorage.getMaxEnergyStored());
		tag.putInt("send", energyStorage.getExtract());
		tag.putInt("receive", energyStorage.getReceive());
		tag.put("inv", itemHandler.serializeNBT());
	}
	
	  @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	            return handler.cast();
	        }
	        if (cap == LensEnergyCapabilityProvider.GUN_ENERGY_CAPABILITY) {
	            return energy.cast();
	        }
	        return super.getCapability(cap, side);
	    }
}
