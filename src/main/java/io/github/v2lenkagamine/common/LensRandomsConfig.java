package io.github.v2lenkagamine.common;

import org.apache.logging.log4j.LogManager;

import io.github.v2lenkagamine.Lensrandoms;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class LensRandomsConfig {
	
	public static ForgeConfigSpec SERVER_CONFIG;
	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec CLIENT_CONFIG;
	
	public static final String CATEGORY_SERVER = "Server";
	public static final String CATEGORY_CLIENT = "Client";
	public static final String CATEGORY_COMMON = "Common";
	
	public static ForgeConfigSpec.ConfigValue<Double> DAMAGE_MULTI;
	
	
	static {
		ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
		ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
		ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
		
		CLIENT_BUILDER.comment("Client Settings").push(CATEGORY_CLIENT);
		CLIENT_BUILDER.pop();
		
		COMMON_BUILDER.comment("Common Settings").push(CATEGORY_COMMON);		
		COMMON_BUILDER.pop();
		
		SERVER_BUILDER.comment("Server Settings").push(CATEGORY_SERVER);	
		setupServer(SERVER_BUILDER);
		SERVER_BUILDER.pop();
		
		CLIENT_CONFIG = CLIENT_BUILDER.build();
		COMMON_CONFIG = COMMON_BUILDER.build();
		SERVER_CONFIG = SERVER_BUILDER.build();
		
		
	}
	private static void setupServer(ForgeConfigSpec.Builder builder) {
		builder.push("general");
		builder.comment("These settings need not be the same on server and client, but are only loaded on world load.");
		
		DAMAGE_MULTI = builder
				.comment("Think this mod is too weak? Crank up the damage! default:1 Range: 0.5 to 100 Type: Double")
				.defineInRange("damageMulti", 1.0, 0.5, 100.0);
		
		builder.pop();
		
	}
    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    	LogManager.getLogger().debug(Lensrandoms.MOD_ID,"Loaded config file {}",configEvent.getConfig().getFileName());
    }
    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {

    }
}
