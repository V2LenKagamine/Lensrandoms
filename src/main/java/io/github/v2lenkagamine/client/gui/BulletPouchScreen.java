package io.github.v2lenkagamine.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.common.items.BulletPouchContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class BulletPouchScreen extends AbstractContainerScreen<BulletPouchContainer>{
	

	private ResourceLocation GUI = new ResourceLocation(Lensrandoms.MOD_ID, "textures/gui/bullet_pouch_GUI.png");
	
	protected BulletPouchScreen(BulletPouchContainer container,Inventory inv,Component name) {
		super(container,inv,name);
		this.imageHeight = 256;
		this.imageWidth = 256;
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
	@Override
	public void render(PoseStack matrixStack,int mouseX,int mouseY,float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
		
	}
	

	@Override
	protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderTexture(0, GUI);
		int relX = (this.width - this.imageWidth) /2;
		int relY = (this.height - this.imageHeight) /2;
		this.blit(matrixStack,relX,relY,0,0,this.imageWidth,this.imageHeight);
		
	}
}
