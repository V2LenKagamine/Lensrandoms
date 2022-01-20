package io.github.v2lenkagamine.common.networking.messages;

import java.util.function.Supplier;

import io.github.v2lenkagamine.common.items.RGB_Inator;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class RGBInatorPacket {
    private final int R;
    private final int G;
    private final int B;
    
       public RGBInatorPacket(FriendlyByteBuf buf) {
           R = buf.readInt();
           G = buf.readInt();
           B = buf.readInt();
       }
       public RGBInatorPacket(int red, int green, int blue) {
           this.R = red;
           this.B = blue;
           this.G = green;
       }
       public void toBytes(FriendlyByteBuf buf) {
           buf.writeInt(R);    
           buf.writeInt(G);    
           buf.writeInt(B);    
       }

       public boolean handle(Supplier<NetworkEvent.Context> ctx) {
           ctx.get().enqueueWork(() -> {
        	  Player player = ctx.get().getSender();
        	  RGB_Inator.RGBInatorSave(player.getMainHandItem(), R, G, B);
           });
           return true;
       }
}