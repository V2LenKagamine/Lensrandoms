package io.github.v2lenkagamine.common.networking.messages;

import java.util.function.Supplier;

import io.github.v2lenkagamine.common.networking.ServerMessageHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

//Hell I might as well have not stolen this from MrCrayfish. Packets are stupid.
public class MessageCraft
{
	
	    private ResourceLocation id;
	    private BlockPos pos;

	    public MessageCraft() {}
	    
	    public MessageCraft(ResourceLocation id, BlockPos pos)
	    {
	        this.id = id;
	        this.pos = pos;
	    }

	    public static void encode(MessageCraft message, FriendlyByteBuf buffer)
	    {
	        buffer.writeResourceLocation(message.id);
	        buffer.writeBlockPos(message.pos);
	    }

	    public static MessageCraft decode(FriendlyByteBuf buffer)
	    {
	        return new MessageCraft(buffer.readResourceLocation(), buffer.readBlockPos());
	    }

	    public static void handle(MessageCraft message, Supplier<NetworkEvent.Context> supplier)
	    {
	        supplier.get().enqueueWork(() ->
	        {
	            ServerPlayer player = supplier.get().getSender();
	            if(player != null)
	            {
	                ServerMessageHandler.handleCraft(player, message.id, message.pos);
	            }
	        });
	        supplier.get().setPacketHandled(true);
	    }

}
