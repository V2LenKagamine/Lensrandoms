package io.github.v2lenkagamine.client.util;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;

//Shamelessly stolen from MrCrayfish
public class RenderUtil {

	public static void scissor(int x, int y, int width, int height)
    {
		Minecraft mc = Minecraft.getInstance();
        int scale = (int) mc.getWindow().getGuiScale();
        GL11.glScissor(x * scale, mc.getWindow().getScreenHeight() - y * scale - height * scale, Math.max(0, width * scale), Math.max(0, height * scale));
	}
	
	 public static void rotateZ(PoseStack poseStack, float xOffset, float yOffset, float rotation)
	{
		 poseStack.translate(xOffset, yOffset, 0);
		 poseStack.mulPose(Vector3f.ZN.rotationDegrees(rotation));
		 poseStack.translate(-xOffset, -yOffset, 0);
	}	
	
	public static boolean isMouseWithin(int mouseX, int mouseY, int x, int y, int width, int height)
	{
		return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
	}
}
