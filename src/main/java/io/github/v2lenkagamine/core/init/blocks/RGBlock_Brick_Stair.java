package io.github.v2lenkagamine.core.init.blocks;

import java.awt.Color;
import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockState;

public class RGBlock_Brick_Stair extends RGBlock_Stair{

	
	public RGBlock_Brick_Stair(Supplier<BlockState> state) {
		super(state);
		state = () -> io.github.v2lenkagamine.core.init.blocks.Blocks.RGBLOCK.get().defaultBlockState();
	}
    public static int getColorAsInt(Color color) {
        if (color == null) {
            return 0;
        }
        return ((color.getRed() & 0xFF) << 16) | ((color.getGreen() & 0xFF) << 8) | (color.getBlue() & 0xFF);
    }

}
