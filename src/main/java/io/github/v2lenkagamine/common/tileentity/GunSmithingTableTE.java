package io.github.v2lenkagamine.common.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.containers.GunSmithingTableContainer;
import io.github.v2lenkagamine.common.interfaces.IStorageBlock;
import io.github.v2lenkagamine.core.init.TileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class GunSmithingTableTE extends BlockEntity implements IStorageBlock{

	private NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);
	
	private ItemStackHandler itemHandler = createItemHandler(9);
	
	private LazyOptional<IItemHandler> handler = LazyOptional.of(()->itemHandler);
	
	
	public GunSmithingTableTE(BlockPos pWorldPosition, BlockState pBlockState) {
		super(TileEntityTypes.GUNSMITHINGTABLETE.get(), pWorldPosition, pBlockState);
	}

	
	@Override
	public void setRemoved() {
		super.setRemoved();
		handler.invalidate();
	}
	
	@Override
	public void load(CompoundTag tag) {
			if(tag.contains("inv")) {
				itemHandler.deserializeNBT(tag.getCompound("inv"));
			}
	}
	
	public void saveAdditional(CompoundTag tag) {
		tag.put("inv", itemHandler.serializeNBT());
	}
	
	private ItemStackHandler createItemHandler(int slots) {
		return new ItemStackHandler(slots) {
			@Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
	}
	
	  @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	            return handler.cast();
	        }
	        return super.getCapability(cap, side);
	    }


	@Override
	public Component getDisplayName() {
		return new TranslatableComponent("container.lensrandoms.gunsmithingtable");
	}


	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
		return new GunSmithingTableContainer(id,inv.player.level,this.worldPosition,inv,inv.player);
	}


	@Override
	public NonNullList<ItemStack> getInventory() {
		return this.inventory;
	}
	
}
