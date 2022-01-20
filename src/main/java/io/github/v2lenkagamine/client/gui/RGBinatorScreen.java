package io.github.v2lenkagamine.client.gui;

import java.util.function.BiConsumer;
import java.util.function.Function;

import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.common.networking.messages.RGBInatorPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SliderButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
public final class RGBinatorScreen extends Screen {
	
	public Player player;
	protected double red;
    protected double blue;
    protected double green;
    protected SliderButton redval;
    protected SliderButton greenval;
    protected SliderButton blueval;
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
		redval = (SliderButton)buildSlider("Red: ", s -> red, (settings, i) -> red = i).createButton(instance.options, 0, 40, 100);
        greenval = (SliderButton)buildSlider("Green: ", s -> green, (settings, i) -> green = i).createButton(instance.options, 0, 80, 100);
        blueval = (SliderButton)buildSlider("Blue: ", s -> blue, (settings, i) -> blue = i).createButton(instance.options, 0, 120, 100);
        addRenderableWidget(redval);
        addRenderableWidget(greenval);
        addRenderableWidget(blueval);
        addRenderableWidget(new Button(relX + 10, relY + 10, 160, 20, new TextComponent("Save"), button -> this.save()));
        addRenderableWidget(new Button(relX + 10, relY + 37, 160, 20, new TextComponent("Close"), button -> this.onClose()));
    }


	public static void open(Player player) {
		Minecraft.getInstance().setScreen(new RGBinatorScreen(player));
	}

	protected void save() {
		Networking.sendToServer(new RGBInatorPacket((int)red, (int)green, (int)blue));
		this.onClose();
	}
	
	public RGBinatorScreen(Player player) {
		super(new TextComponent(""));
		this.player = player;

	}
	
	protected ProgressOption buildSlider(String key, Function<Options, Double> getter, BiConsumer<Options, Double> setter){
        return new ProgressOption(key, 1.0D, 255.0D, 1.0F, getter, setter, (settings, optionValues) -> {
            return new TextComponent(key + (int)optionValues.get(settings));
        });
       
    }

}