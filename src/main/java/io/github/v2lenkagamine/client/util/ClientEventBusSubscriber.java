package io.github.v2lenkagamine.client.util;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.client.gui.BulletPouchScreen;
import io.github.v2lenkagamine.core.init.ContainersInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.network.NetworkEvent.RegistrationChangeType;

@Mod.EventBusSubscriber(modid = Lensrandoms.MOD_ID,bus= Bus.MOD, value= Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		MenuScreens.register(ContainersInit.BULLET_POUCH_CONTAINER.get(),BulletPouchScreen::new);
	}
}
