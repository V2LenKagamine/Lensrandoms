package io.github.v2lenkagamine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.v2lenkagamine.client.util.Clientutils;
import io.github.v2lenkagamine.common.LensRandomsConfig;
import io.github.v2lenkagamine.common.capabilities.gunTimer.CapabilityGunTimer;
import io.github.v2lenkagamine.common.capabilities.powerholeNetwork.CapabilityPowerholeNetwork;
import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.core.init.TileEntityTypes;
import io.github.v2lenkagamine.core.items.BlockItems;
import io.github.v2lenkagamine.core.items.Items;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import io.github.v2lenkagamine.datagen.BlockTags;
import io.github.v2lenkagamine.datagen.ItemTags;
import io.github.v2lenkagamine.datagen.LensRandomsBlockStateProvider;
import io.github.v2lenkagamine.datagen.LensRandomsItemModelProvider;
import io.github.v2lenkagamine.datagen.LensRandomsLangauge;
import io.github.v2lenkagamine.datagen.LensRandomsLootTableProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Lensrandoms.MOD_ID)
@Mod.EventBusSubscriber(modid = Lensrandoms.MOD_ID, bus = Bus.MOD)
public class Lensrandoms {
		public static final Logger LOGGER = LogManager.getLogger();
		public static final String MOD_ID = "lensrandoms";
		public static final ItemGroup LENS_RANDOMS = new LensRandomsGroup("lensrandomstab");
		
		public Lensrandoms() {
			final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
			bus.addListener(Clientutils::ClientUtilsStartUp);
			bus.addListener(this::gatherData);
			bus.register(BlockItems.class);
			bus.addListener(this::setupCommon);
			RegistryHelper.register();
			TileEntityTypes.TILE_ENTITY_TYPE.register(bus);
			Networking.registerMessages();
			ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, LensRandomsConfig.SERVER_CONFIG);
			bus.register(LensRandomsConfig.class);
		
			
			//Always put on bottom VVV
			MinecraftForge.EVENT_BUS.register(this);
		}
		
	  private void gatherData(final GatherDataEvent event) {
		  DataGenerator gen = event.getGenerator();
		  if(event.includeClient()) {
			  ExistingFileHelper helper = event.getExistingFileHelper();
			  gen.addProvider(new LensRandomsBlockStateProvider(gen, helper));
			  gen.addProvider(new LensRandomsItemModelProvider(gen, helper));
			  gen.addProvider(new LensRandomsLootTableProvider(gen));
			  gen.addProvider(new LensRandomsLangauge(gen, "en_us"));
			  BlockTags blockTags = new BlockTags(gen,helper);
			  gen.addProvider(blockTags);
			  gen.addProvider(new ItemTags(gen,blockTags,helper));
		  }
	  }
	  
	 
	private void setupCommon(final FMLCommonSetupEvent event) {
			CapabilityPowerholeNetwork.register();
			CapabilityGunTimer.register();
		}
	
		public static class LensRandomsGroup extends ItemGroup{ 
			
			public LensRandomsGroup(String label) {
				super(label);
			}
			
			@Override
			public ItemStack createIcon() {
				return Items.TEST_ITEM.get().getDefaultInstance();
			}
			
		}
	
}
