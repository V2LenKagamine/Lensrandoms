package io.github.v2lenkagamine.common.networking;

import java.util.function.Supplier;

import io.github.v2lenkagamine.core.items.RGB_Inator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class RGBInatorPacket {
    private final double R;
    private final double G;
    private final double B;
       public RGBInatorPacket(PacketBuffer buf) {
           R = buf.readDouble();
           G = buf.readDouble();
           B = buf.readDouble();
       }
       public RGBInatorPacket(double red, double green, double blue) {
           this.R = red;
           this.B = blue;
           this.G = green;
       }
       public void toBytes(PacketBuffer buf) {
           buf.writeDouble(R);    
           buf.writeDouble(G);    
           buf.writeDouble(B);    
       }

       public boolean handle(Supplier<NetworkEvent.Context> ctx) {
           ctx.get().enqueueWork(() -> {
        	  PlayerEntity player = ctx.get().getSender();
        	  RGB_Inator.RGBInatorSave(player.getHeldItemMainhand(), R, G, B);
        	  System.out.println(player.getHeldItemMainhand().getTag());
        	  
           });
           return true;
       }
}