package io.github.v2lenkagamine.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.v2lenkagamine.Lensrandoms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class BulletPouchScreen extends Screen{
	
	private static final int WIDTH = 179;
	private static final int HIGHT = 151;
	
	
	private ResourceLocation GUI = new ResourceLocation(Lensrandoms.MOD_ID, "textures/gui/bullet_pouch_GUI.png");
	
	protected BulletPouchScreen() {
		super(new TranslatableComponent("screen.lensrandoms.bulletpouch"));
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
	@Override
	public void render(PoseStack matrixStack,int mouseX,int mouseY,float partialTicks) {
		RenderSystem.setShaderTexture(0, GUI);
		RenderSystem.setShaderColor(1.0F, 1.0f, 1.0f, 1.0f);
		int relX = (this.width - WIDTH) /2;
		int relY = (this.height - HIGHT)/2;
		this.blit(matrixStack,relX,relY,0,0,WIDTH,HIGHT);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		
	}
	public static void open() {
		Minecraft.getInstance().setScreen(new BulletPouchScreen());
	}
}
