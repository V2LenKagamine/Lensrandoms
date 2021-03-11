package io.github.v2lenkagamine.client.gui;

import java.util.function.BiConsumer;
import java.util.function.Function;

import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.common.networking.RGBInatorPacket;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.OptionSlider;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
public final class RGBinatorScreen extends Screen {
	
	public PlayerEntity player;
	protected double red;
    protected double blue;
    protected double green;
    protected OptionSlider redval;
    protected OptionSlider greenval;
    protected OptionSlider blueval;
    protected Button buttonSave;
    protected Button buttonClose;
    private static final int WIDTH = 179;
    private static final int HEIGHT = 151;
    @Override
    protected void init() {
        super.init();
        final int relX = (this.width - WIDTH) / 2;
        final int relY = (this.height - HEIGHT) / 2;
        Minecraft instance = Minecraft.getInstance();
		redval = (OptionSlider)buildSlider("Red: ", s -> red, (settings, i) -> red = i).createWidget(instance.gameSettings, 0, 40, 100);
        greenval = (OptionSlider)buildSlider("Green: ", s -> green, (settings, i) -> green = i).createWidget(instance.gameSettings, 0, 80, 100);
        blueval = (OptionSlider)buildSlider("Blue: ", s -> blue, (settings, i) -> blue = i).createWidget(instance.gameSettings, 0, 120, 100);
        addButton(redval);
        addButton(greenval);
        addButton(blueval);
        addButton(new Button(relX + 10, relY + 10, 160, 20, new StringTextComponent("Save"), button -> Networking.sendToServer(new RGBInatorPacket(red, green, blue))));
        addButton(new Button(relX + 10, relY + 37, 160, 20, new StringTextComponent("Close"), button -> this.closeScreen()));
    }


	public static void open(PlayerEntity player) {
		Minecraft.getInstance().displayGuiScreen(new RGBinatorScreen(player));
	}

	public RGBinatorScreen(PlayerEntity player) {
		super(new StringTextComponent(""));
		this.player = player;

	}
	
	protected SliderPercentageOption buildSlider(String key, Function<GameSettings, Double> getter, BiConsumer<GameSettings, Double> setter){
        return new SliderPercentageOption(key, 1.0D, 255.0D, 1.0F, getter, setter, (settings, optionValues) -> {
            return new StringTextComponent(key + (int)optionValues.get(settings));
        });
       
    }

}