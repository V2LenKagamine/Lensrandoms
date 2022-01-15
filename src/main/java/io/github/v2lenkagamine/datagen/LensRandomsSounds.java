package io.github.v2lenkagamine.datagen;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.init.Sounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.registries.RegistryObject;

public class LensRandomsSounds extends SoundDefinitionsProvider{

	public LensRandomsSounds(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, Lensrandoms.MOD_ID, helper);

	}

	@Override
	public void registerSounds() {
		addSoundSubtitled(Sounds.GUN_CLICK,fileLoc("gun_click"));
		addSoundSubtitled(Sounds.BREEFCASE,fileLoc("breefcase"));
		addSoundSubtitled(Sounds.RELOAD_BASE,fileLoc("reload_base"));
		addSoundSubtitled(Sounds.SHOT_BASE,fileLoc("shot_base"));
	}


	protected void addSound(RegistryObject<SoundEvent> soundEventRO, SoundDefinition definition) {
        add(soundEventRO.get(), definition);
    }
	
    protected void addSoundSubtitled(RegistryObject<SoundEvent> soundEventRO, ResourceLocation location) {
        addSound(soundEventRO, definition(soundEventRO).with(sound(location)));
    }

    protected static SoundDefinition definition(RegistryObject<SoundEvent> subtitle) {
        return SoundDefinition.definition().subtitle((subtitle.get().getRegistryName().toString() + ".sub").replace(":", "."));
    }
    
    private static ResourceLocation fileLoc(String path) {
    	return new ResourceLocation(Lensrandoms.MOD_ID,path);
    }

}
