package io.github.v2lenkagamine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.v2lenkagamine.common.LensRandomsConfig;
import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.common.tileentity.RGBlockTE;
import io.github.v2lenkagamine.core.init.TileEntityTypes;
import io.github.v2lenkagamine.core.init.blocks.Blocks;
import io.github.v2lenkagamine.core.init.blocks.RGBlock;
import io.github.v2lenkagamine.core.items.BlockItems;
import io.github.v2lenkagamine.core.items.Items;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import io.github.v2lenkagamine.datagen.LensRandomsBlockStateProvider;
import io.github.v2lenkagamine.datagen.LensRandomsItemModelProvider;
import io.github.v2lenkagamine.datagen.LensRandomsLootTableProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
			bus.addListener(this::ClientStuff);
			bus.addListener(this::gatherData);
			bus.register(BlockItems.class);
			RegistryHelper.register();
			TileEntityTypes.TILE_ENTITY_TYPE.register(bus);
			MinecraftForge.EVENT_BUS.register(this);
			Networking.registerMessages();
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LensRandomsConfig.commonSpec);
			
			bus.register(ForgeConfig.class);
		}
	  private void gatherData(final GatherDataEvent event) {
		  DataGenerator gen = event.getGenerator();
		  if(event.includeClient()) {
			  ExistingFileHelper helper = event.getExistingFileHelper();
			  gen.addProvider(new LensRandomsBlockStateProvider(gen, helper));
			  gen.addProvider(new LensRandomsItemModelProvider(gen, helper));
			  gen.addProvider(new LensRandomsLootTableProvider(gen));
		  }
	  }
	  private void ClientStuff(final FMLClientSetupEvent event) {
		// RenderTypeLookup.setRenderLayer(Blocks.(Block Here).get(), RenderType.getCutout());
		  Minecraft.getInstance().getBlockColors().register((state, blockaccess, pos, tintindex) ->
	        {
	        	Minecraft instance = Minecraft.getInstance();
	            if(instance.world == null || pos == null){
	                return 0;
	            }
	            TileEntity tile = instance.world.getTileEntity(pos);
	            if(tile instanceof RGBlockTE){
	                return RGBlock.getColorAsInt(((RGBlockTE) tile).color);
	            }else{
	                System.out.println("I am useless");
	                return 0;
	            }
	        },Blocks.RGBLOCK.get());
	  }
	/*	
	private void setup(final FMLCommonSetupEvent event) {

		}
	*/
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
