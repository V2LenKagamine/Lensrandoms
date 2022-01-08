package io.github.v2lenkagamine.common.networking;

import java.util.Objects;
import java.util.function.Supplier;

import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ChangeColorPacket {
    private final int color;
    private final BlockPos pos;

    public ChangeColorPacket(FriendlyByteBuf buf) {
        this.color = buf.readInt();
        this.pos = buf.readBlockPos();

    }

    public ChangeColorPacket(int color, BlockPos pos) {
        this.color = color;
        this.pos = pos;
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> ((RGBlockTE) Objects.requireNonNull(Objects.requireNonNull(ctx.get().getSender()).level.getBlockEntity(pos))).setColorFromInt(color));
        return true;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(color);
        buf.writeBlockPos(pos);
    }
}