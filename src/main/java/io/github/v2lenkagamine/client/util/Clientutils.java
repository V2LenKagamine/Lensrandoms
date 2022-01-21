package io.github.v2lenkagamine.client.util;

import io.github.v2lenkagamine.core.init.Entitytype;
import io.github.v2lenkagamine.core.init.blocks.LensBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber
public class Clientutils {
	
	public static void ClientUtilsStartUp(final FMLClientSetupEvent event) {
		RegisterBlockColors();
		SetGlassUp();
		RegisterEntityModels();
	}
	public static void RegisterBlockColors() {
				RgbInit.rgbInit(LensBlocks.RGBLOCK.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_GLOW.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_STAIR.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_SLAB.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_BRICK.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_BRICK_GLOW.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_BRICK_SLAB.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_BRICK_STAIR.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_GLASS.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_GLASS_GLOW.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_GLASS_BORDER.get());
				RgbInit.rgbInit(LensBlocks.RGBLOCK_GLASS_BORDER_GLOW.get());
	}
	public static void SetGlassUp() {
		ItemBlockRenderTypes.setRenderLayer(LensBlocks.RGBLOCK_GLASS.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(LensBlocks.RGBLOCK_GLASS_BORDER.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(LensBlocks.RGBLOCK_GLASS_GLOW.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(LensBlocks.RGBLOCK_GLASS_BORDER_GLOW.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(LensBlocks.CLEARGLASS.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(LensBlocks.CLEARGLASS_GLOW.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(LensBlocks.CLEARGLASS_BORDER.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(LensBlocks.CLEARGLASS_BORDER_GLOW.get(), RenderType.translucent());
	}	
	public static void RegisterEntityModels() {
		EntityRenderers.register(Entitytype.BREEFCASE_PROJECTILE.get(),ThrownItemRenderer::new);
		EntityRenderers.register(Entitytype.BEECASE_PROJECTILE.get(), ThrownItemRenderer::new);
	}
}
