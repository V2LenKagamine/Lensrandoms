package io.github.v2lenkagamine.common.networking;

import java.util.Objects;
import java.util.function.Supplier;

import io.github.v2lenkagamine.common.tileentity.PowerHoleTE;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class ChangeChannelPacket {
    private final int channel;
    private final BlockPos pos;

    public ChangeChannelPacket(PacketBuffer buf) {
        this.channel = buf.readInt();
        this.pos = buf.readBlockPos();

    }

    public ChangeChannelPacket(int channel, BlockPos pos) {
        this.channel = channel;
        this.pos = pos;
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> ((PowerHoleTE) Objects.requireNonNull(Objects.requireNonNull(ctx.get().getSender()).world.getTileEntity(pos))).setChannelFromInt(channel));
        return true;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(channel);
        buf.writeBlockPos(pos);
    }
}