package io.github.v2lenkagamine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.v2lenkagamine.client.util.Clientutils;
import io.github.v2lenkagamine.common.LensRandomsConfig;
import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingIngredient;
import io.github.v2lenkagamine.common.crafting.lensshapelessnoconsume.LRNCIngredient;
import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.core.init.ContainersInit;
import io.github.v2lenkagamine.core.init.TileEntityTypes;
import io.github.v2lenkagamine.core.items.LensBlockItems;
import io.github.v2lenkagamine.core.items.LensItems;
import io.github.v2lenkagamine.core.util.CuriosCompat;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import io.github.v2lenkagamine.datagen.LensBlockTags;
import io.github.v2lenkagamine.datagen.LensItemTags;
import io.github.v2lenkagamine.datagen.LensRandomsBlockStateProvider;
import io.github.v2lenkagamine.datagen.LensRandomsDungeonLootGenerator;
import io.github.v2lenkagamine.datagen.LensRandomsItemModelProvider;
import io.github.v2lenkagamine.datagen.LensRandomsLanguage;
import io.github.v2lenkagamine.datagen.LensRandomsLootTableProvider;
import io.github.v2lenkagamine.datagen.LensRandomsRecipes;
import io.github.v2lenkagamine.datagen.LensRandomsSounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(Lensrandoms.MOD_ID)
@Mod.EventBusSubscriber(modid = Lensrandoms.MOD_ID, bus = Bus.MOD)
public class Lensrandoms {
		public static final Logger LOGGER = LogManager.getLogger();
		public static final String MOD_ID = "lensrandoms";
		public static final CreativeModeTab LENS_RANDOMS = new LensRandomsGroup("lensrandomstab");
		
		public Lensrandoms() {
			final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
			bus.addListener(Clientutils::ClientUtilsStartUp);
			bus.addListener(this::onCommonSetup);
			bus.addListener(this::gatherData);
			bus.register(LensBlockItems.class);
			bus.register(CuriosCompat.class);
			bus.register(LensRandomsConfig.class);
			RegistryHelper.register();
			TileEntityTypes.BLOCK_ENTITY_TYPE.register(bus);
			Networking.registerMessages();
			ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, LensRandomsConfig.SERVER_CONFIG);
			ContainersInit.CONTAINERS.register(bus);
			CuriosCompat.initCuriosCompat();
			
			//Always put on bottom VVV
			MinecraftForge.EVENT_BUS.register(this);
		}
		
		
		private void onCommonSetup(FMLCommonSetupEvent event) {
			CraftingHelper.register(new ResourceLocation(Lensrandoms.MOD_ID,"gunsmithing_ingredient"),GunSmithingIngredient.Serializer.INSTANCE);
			CraftingHelper.register(new ResourceLocation(Lensrandoms.MOD_ID,"lrnc_ingredient"),LRNCIngredient.Serializer.INSTANCE);
		}
		
	  private void gatherData(final GatherDataEvent event) {
		  DataGenerator gen = event.getGenerator();
		  ExistingFileHelper helper = event.getExistingFileHelper();
		  if(event.includeClient()) {
			  gen.addProvider(new LensRandomsBlockStateProvider(gen, helper));
			  gen.addProvider(new LensRandomsItemModelProvider(gen, helper));
			  gen.addProvider(new LensRandomsLanguage(gen, "en_us"));
			  gen.addProvider(new LensRandomsSounds(gen,helper));
		  }
		  if(event.includeServer()) {
			  gen.addProvider(new LensRandomsLootTableProvider(gen));
			  gen.addProvider(new LensRandomsDungeonLootGenerator(gen, MOD_ID));
			  LensBlockTags blockTags = new LensBlockTags(gen,helper);
			  gen.addProvider(blockTags);
			  gen.addProvider(new LensItemTags(gen,blockTags,helper));
			  gen.addProvider(new LensRandomsRecipes(gen));
		  }
	  }
	
		public static class LensRandomsGroup extends CreativeModeTab{ 
			
			public LensRandomsGroup(String label) {
				super(label);
			}
			
			@Override
			public ItemStack makeIcon() {
				return LensItems.TEST_ITEM.get().getDefaultInstance();
			}
			
		}
	
}
