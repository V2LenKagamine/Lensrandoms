package io.github.v2lenkagamine.client.util;

import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import io.github.v2lenkagamine.core.init.blocks.RGBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RgbInit {
	
	public static void rgbInit(Block block) {
	Minecraft.getInstance().getBlockColors().register((state, blockaccess, pos, tintindex) ->
    {
    	Minecraft instance = Minecraft.getInstance();
        if(instance.level == null || pos == null){
            return 0;
        }
        BlockEntity tile = instance.level.getBlockEntity(pos);
        if(tile instanceof RGBlockTE){
            return RGBlock.getColorAsInt(((RGBlockTE) tile).color);
        }else{
            return 0;
        }
    	},block);
	}
}
