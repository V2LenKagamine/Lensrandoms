package io.github.v2lenkagamine.core.init.blocks;

import io.github.v2lenkagamine.common.containers.GunSmithingTableContainer;
import io.github.v2lenkagamine.common.tileentity.GunSmithingTableTE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class GunSmithingTable extends Block implements EntityBlock{

	public GunSmithingTable() {
		super(BlockProperties.machineProps);
	}

	
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {

		return new GunSmithingTableTE(pPos,pState);
	}

	 public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
	        if (!world.isClientSide) {
	            BlockEntity tileEntity = world.getBlockEntity(pos);
	            if (tileEntity instanceof GunSmithingTableTE) {
	                MenuProvider containerProvider = new MenuProvider() {
	                    @Override
	                    public Component getDisplayName() {
	                        return new TranslatableComponent("screen.lensrandoms.gunsmithingtable");
	                    }

	                    @Override
	                    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
	                        return new GunSmithingTableContainer(i, world, pos, playerInventory, playerEntity);
	                    }
	                };
	                NetworkHooks.openGui((ServerPlayer) player, containerProvider, tileEntity.getBlockPos());
	            } else {
	                throw new IllegalStateException("Gun Smithing Table Container Provider Missing!");
	            }
	        }
	        return InteractionResult.SUCCESS;
	    }


}
