package io.github.v2lenkagamine.core.util;

import io.github.v2lenkagamine.Lensrandoms;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;


@Mod.EventBusSubscriber(modid = Lensrandoms.MOD_ID, bus = Bus.MOD)
public class CuriosCompat {
	 public static void initCuriosCompat() {
		 InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
	 }
}
