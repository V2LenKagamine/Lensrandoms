package io.github.v2lenkagamine.core.init.blocks;

import javax.annotation.Nullable;

import io.github.v2lenkagamine.common.containers.ChargerContainer;
import io.github.v2lenkagamine.common.tileentity.ChargerTE;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ChargeBlock extends Block implements EntityBlock{

	public ChargeBlock() {
		super(BlockProperties.machineProps);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {

		return new ChargerTE(pPos,pState);
	}
	
	 @Nullable
	    @Override
	    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
	        if (level.isClientSide()) {
	            return null;
	        }
	        return (level1, blockPos, blockState, t) -> {
	            if (t instanceof ChargerTE tile) {
	                tile.tickServer();
	            }
	        };
	    }

	 public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
	        if (!world.isClientSide) {
	            BlockEntity tileEntity = world.getBlockEntity(pos);
	            if (tileEntity instanceof ChargerTE) {
	                MenuProvider containerProvider = new MenuProvider() {
	                    @Override
	                    public Component getDisplayName() {
	                        return new TranslatableComponent("screen.lensrandoms.charger");
	                    }

	                    @Override
	                    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
	                        return new ChargerContainer(i, world, pos, playerInventory, playerEntity);
	                    }
	                };
	                NetworkHooks.openGui((ServerPlayer) player, containerProvider, tileEntity.getBlockPos());
	            } else {
	                throw new IllegalStateException("Charger Container Provider Missing!");
	            }
	        }
	        return InteractionResult.SUCCESS;
	    }


}
