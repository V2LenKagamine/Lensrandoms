package io.github.v2lenkagamine.common.networking;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.networking.messages.ChangeColorPacket;
import io.github.v2lenkagamine.common.networking.messages.MessageCraft;
import io.github.v2lenkagamine.common.networking.messages.RGBInatorPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {
    private static SimpleChannel INSTANCE;
    private static int ID = 0;
    private static int nextID() {
        return ID++;
    }
    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Lensrandoms.MOD_ID + "networking"),
                () -> "1.0", s -> true, s -> true); 
        INSTANCE.messageBuilder(ChangeColorPacket.class, nextID())
        .encoder(ChangeColorPacket::toBytes)
        .decoder(ChangeColorPacket::new)
        .consumer(ChangeColorPacket::handle)
        .add();
        
        INSTANCE.messageBuilder(RGBInatorPacket.class, nextID())
        .encoder(RGBInatorPacket::toBytes)
        .decoder(RGBInatorPacket::new)
        .consumer(RGBInatorPacket::handle)
        .add();
        
        INSTANCE.messageBuilder(MessageCraft.class, nextID())
        .encoder(MessageCraft::encode)
      	.decoder(MessageCraft::decode)
        .consumer(MessageCraft::handle)
        .add();
        
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
    public static void sendToClient(Object packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}