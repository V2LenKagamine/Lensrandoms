package io.github.v2lenkagamine.common.networking;

import java.util.function.Supplier;

import io.github.v2lenkagamine.core.items.RGB_Inator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class RGBInatorPacket {
    private final int R;
    private final int G;
    private final int B;
       public RGBInatorPacket(PacketBuffer buf) {
           R = buf.readInt();
           G = buf.readInt();
           B = buf.readInt();
       }
       public RGBInatorPacket(int red, int green, int blue) {
           this.R = red;
           this.B = blue;
           this.G = green;
       }
       public void toBytes(PacketBuffer buf) {
           buf.writeInt(R);    
           buf.writeInt(G);    
           buf.writeInt(B);    
       }

       public boolean handle(Supplier<NetworkEvent.Context> ctx) {
           ctx.get().enqueueWork(() -> {
        	  PlayerEntity player = ctx.get().getSender();
        	  RGB_Inator.RGBInatorSave(player.getHeldItemMainhand(), R, G, B);
           });
           return true;
       }
}