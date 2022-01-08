package io.github.v2lenkagamine.common.tileentity;

import java.awt.Color;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.v2lenkagamine.core.init.TileEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RGBlockTE extends BlockEntity {
	
	public Color color = new Color(0, 0, 0);

    public void setColor(Color color) {
        this.color = color;
        this.setChanged();
    }

    public RGBlockTE() {
        super(TileEntityTypes.RGBLOCK_TE.get());
    }
   

    @Override
    @Nonnull
    public CompoundTag save(@Nonnull CompoundTag compound) {
        final CompoundTag target = super.save(compound);
        target.putInt("color", this.getColorAsInt());
        return compound;
    }

    @Override
    public void load(@Nonnull BlockState state,@Nonnull CompoundTag nbt) {
        super.load(state, nbt);
        this.setColorFromInt(nbt.getInt("color"));
        if(level != null){
            updateTile();
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        final CompoundTag nbt = new CompoundTag();
        nbt.putInt("color", this.getColorAsInt());
        return new ClientboundBlockEntityDataPacket(this.getBlockPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.setColorFromInt(pkt.getTag().getInt("color"));
    }

    @Override
    @Nonnull
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        nbt.putInt("color", this.getColorAsInt());
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundTag tag) {
        this.setColorFromInt(tag.getInt("color"));
    }

    public int getColorAsInt() {
        final Color color = this.getColor();
        return ((color.getRed() & 0xFF) << 16) | ((color.getGreen() & 0xFF) << 8) | (color.getBlue() & 0xFF);
    }

    public void setColorFromInt(final int color) {
        this.setColor(new Color(color));
        this.setChanged();
    }

    public Color getColor() {
        return color;
    }
    public void updateTile() {
        this.requestModelDataUpdate();
        this.setChanged();
        if (this.getLevel() != null) {
            this.getLevel().sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }
}