package io.github.v2lenkagamine.client.util;

import io.github.v2lenkagamine.core.init.blocks.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber
public class Clientutils {
	
	public static void ClientUtilsStartUp(final FMLClientSetupEvent event) {
		RegisterBlockColors();
		SetGlassUp();
	}
	public static void RegisterBlockColors() {
				RgbInit.rgbInit(Blocks.RGBLOCK.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_GLOW.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_STAIR.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_SLAB.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_BRICK.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_BRICK_GLOW.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_BRICK_SLAB.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_BRICK_STAIR.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_GLASS.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_GLASS_GLOW.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_GLASS_BORDER.get());
				RgbInit.rgbInit(Blocks.RGBLOCK_GLASS_BORDER_GLOW.get());
	}
	public static void SetGlassUp() {
		RenderTypeLookup.setRenderLayer(Blocks.RGBLOCK_GLASS.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(Blocks.RGBLOCK_GLASS_BORDER.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(Blocks.RGBLOCK_GLASS_GLOW.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(Blocks.RGBLOCK_GLASS_BORDER_GLOW.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(Blocks.CLEARGLASS.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(Blocks.CLEARGLASS_GLOW.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(Blocks.CLEARGLASS_BORDER.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(Blocks.CLEARGLASS_BORDER_GLOW.get(), RenderType.getTranslucent());
	}
}
