package io.github.v2lenkagamine.common.loottables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import io.github.v2lenkagamine.core.items.Items;
import io.github.v2lenkagamine.datagen.LensRandomsDungeonLootGenerator;
import net.minecraft.world.item.ItemStack;

public class DungeonLootTables {

	public static List<Supplier<ItemStack>> BASIC_LOOT = new ArrayList<>();
	public static List<Supplier<ItemStack>> UNCOMMON_LOOT = new ArrayList<>();
	public static List<Supplier<ItemStack>> RARE_LOOT = new ArrayList<>();
	public static List<Supplier<ItemStack>> ULTRA_LOOT = new ArrayList<>();
	
	public static Random rand = new Random();
	static {
		//Basic Loot
		
		//Uncommon Loot
		
		//Rare Loot
		
		RARE_LOOT.add(() -> new ItemStack(Items.BEECASE.get(),1));
		
		//Ultra Rare Loot
		
		ULTRA_LOOT.add(() -> new ItemStack(Items.GUN_LENS_REVOLVER.get(),1));
		
	}
	
	
	
	
	
	
	
	public static ItemStack getRandItem(List<Supplier<ItemStack>> pool) {
		return pool.isEmpty() ? ItemStack.EMPTY : pool.get(rand.nextInt(pool.size())).get();
	}
	
	public static List<ItemStack> getRandomRoll(LensRandomsDungeonLootGenerator.LensRandomsDungeonLootModifier mod) {
		List<ItemStack> stacks = new ArrayList<>();
		
		for(int i = 0;i < mod.commonRolls;i++) {
			if(rand.nextDouble() <= mod.commonChance) {
				stacks.add(getRandItem(BASIC_LOOT));
			}
		}
		for(int i = 0;i < mod.uncommonRolls;i++) {
			if(rand.nextDouble() <= mod.uncommonChance) {
				stacks.add(getRandItem(UNCOMMON_LOOT));
			}
		}
		for(int i = 0;i < mod.rareRolls;i++) {
			if(rand.nextDouble() <= mod.rareChance) {
				stacks.add(getRandItem(RARE_LOOT));
			}
		}
		for(int i = 0;i < mod.ultraRolls;i++) {
			if(rand.nextDouble() <= mod.ultraChance) {
				stacks.add(getRandItem(ULTRA_LOOT));
			}
		}
		return stacks;
	}
}
