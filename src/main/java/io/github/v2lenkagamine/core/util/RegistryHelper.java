package io.github.v2lenkagamine.core.util;

import io.github.v2lenkagamine.Lensrandoms;
import io.github.v2lenkagamine.core.init.Entitytype;
import io.github.v2lenkagamine.core.init.LensRecipesSerializer;
import io.github.v2lenkagamine.core.init.Sounds;
import io.github.v2lenkagamine.core.init.blocks.Blocks;
import io.github.v2lenkagamine.core.items.Items;
import io.github.v2lenkagamine.datagen.LensRandomsDungeonLootGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHelper {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Lensrandoms.MOD_ID);
	public static final DeferredRegister<Item> ITEMS  = DeferredRegister.create(ForgeRegistries.ITEMS,Lensrandoms.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,Lensrandoms.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,Lensrandoms.MOD_ID);
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Lensrandoms.MOD_ID);
	public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Lensrandoms.MOD_ID);
	
	public static void register() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
		RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		
		Entitytype.register();
		Blocks.register();
		Items.register();
		Sounds.register();
		LensRandomsDungeonLootGenerator.register();
		LensRecipesSerializer.register();
	}
	
}
