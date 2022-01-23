package io.github.v2lenkagamine.datagen;

import java.util.function.Consumer;

import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingRecipeBuilder;
import io.github.v2lenkagamine.common.crafting.lensshapelessnoconsume.LRNCRecipeBuilder;
import io.github.v2lenkagamine.core.init.blocks.LensBlocks;
import io.github.v2lenkagamine.core.items.LensItems;
import io.github.v2lenkagamine.core.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class LensRandomsRecipes extends RecipeProvider{

	public LensRandomsRecipes(DataGenerator pGenerator) {
		super(pGenerator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		
		
		//Shaped Recipes
		
		ShapedRecipeBuilder.shaped(LensBlocks.GUNSMITHING_TABLE.get())
		.pattern("ICI")
		.pattern("ASB")
		.define('I', Tags.Items.INGOTS_IRON)
		.define('C', Tags.Items.INGOTS_COPPER)
		.define('A', Tags.Items.STORAGE_BLOCKS_IRON)
		.define('S', Blocks.SMITHING_TABLE)
		.define('B', Tags.Items.BARRELS)
		.unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(LensBlocks.CHARGER.get())
		.pattern("III")
		.pattern("CRC")
		.pattern("IWI")
		.define('I', Tags.Items.INGOTS_IRON)
		.define('C', Tags.Items.INGOTS_COPPER)
		.define('R', Tags.Items.STORAGE_BLOCKS_REDSTONE)
		.define('W', Tags.Items.CHESTS_WOODEN)
		.unlockedBy("has_redstone", has(Tags.Items.STORAGE_BLOCKS_REDSTONE))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(LensItems.RGB_INATOR.get())
		.pattern("DDD")
		.pattern("RIR")
		.pattern(" I ")
		.define('D', Tags.Items.DYES)
		.define('R', Tags.Items.DUSTS_REDSTONE)
		.define('I', Tags.Items.INGOTS_IRON)
		.unlockedBy("has_dye", has(Tags.Items.DYES))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(LensItems.SIMPLE_BULLET.get(),32)
		.pattern("CG")
		.pattern("GC")
		.define('C', Tags.Items.COBBLESTONE)
		.define('G', Tags.Items.GUNPOWDER)
		.unlockedBy("has_gunpowder", has(Tags.Items.GUNPOWDER))
		.save(consumer,"simple_bullet_shapeless");
		
		ShapedRecipeBuilder.shaped(LensItems.SIMPLE_BULLET.get(),32)
		.pattern("GC")
		.pattern("CG")
		.define('C', Tags.Items.COBBLESTONE)
		.define('G', Tags.Items.GUNPOWDER)
		.unlockedBy("has_gunpowder", has(Tags.Items.GUNPOWDER))
		.save(consumer,"simple_bullet_shapeless_alt");
		
		ShapedRecipeBuilder.shaped(LensItems.GUN_FLINTLOCK.get())
		.pattern("I  ")
		.pattern(" I ")
		.pattern(" W ")
		.define('I', Tags.Items.INGOTS_IRON)
		.define('W', ItemTags.PLANKS)
		.unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(LensItems.GUN_BLUNDERBUS.get())
		.pattern("I  ")
		.pattern(" GI")
		.pattern(" WW")
		.define('I', Tags.Items.INGOTS_IRON)
		.define('G', LensItems.GUN_FLINTLOCK.get())
		.define('W', ItemTags.PLANKS)
		.unlockedBy("has_flintlock", has(LensItems.GUN_FLINTLOCK.get()))
		.save(consumer);
		
		simpleSlab(LensBlocks.RGBLOCK_BRICK.get().asItem(), LensBlocks.RGBLOCK_BRICK_SLAB.get().asItem(), consumer);
		simpleStairs(LensBlocks.RGBLOCK_BRICK.get().asItem(), LensBlocks.RGBLOCK_BRICK_STAIR.get().asItem(), consumer);
		
		simpleSlab(LensBlocks.RGBLOCK.get().asItem(),LensBlocks.RGBLOCK_SLAB.get().asItem(), consumer);
		simpleStairs(LensBlocks.RGBLOCK.get().asItem(), LensBlocks.RGBLOCK_STAIR.get().asItem(), consumer);
		
		//Shapeless Regular
		
		ShapelessRecipeBuilder.shapeless(LensBlocks.CLEARGLASS_BORDER.get().asItem())
		.requires(LensBlocks.CLEARGLASS.get().asItem())
		.unlockedBy("has_clearglass",has(LensBlocks.CLEARGLASS.get().asItem()))
		.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(LensBlocks.CLEARGLASS_GLOW.get().asItem(),8)
		.requires(Tags.Items.DUSTS_GLOWSTONE)
		.requires(LensBlocks.CLEARGLASS.get().asItem(),8)
		.unlockedBy("has_glowstone", has(Tags.Items.DUSTS_GLOWSTONE))
		.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(LensBlocks.CLEARGLASS_BORDER_GLOW.get().asItem())
		.requires(LensBlocks.CLEARGLASS_BORDER.get().asItem(),8)
		.requires(Tags.Items.DUSTS_GLOWSTONE)
		.unlockedBy("has_glowstone", has(Tags.Items.DUSTS_GLOWSTONE))
		.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(LensBlocks.RGBLOCK_GLASS_BORDER.get().asItem())
		.requires(LensBlocks.RGBLOCK_GLASS.get().asItem())
		.unlockedBy("has_glass", has(LensBlocks.RGBLOCK_GLASS.get().asItem()))
		.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(LensBlocks.RGBLOCK_GLASS_GLOW.get().asItem())
		.requires(LensBlocks.RGBLOCK_GLASS.get().asItem())
		.requires(Tags.Items.DUSTS_GLOWSTONE)
		.unlockedBy("has_glowstone", has(Tags.Items.DUSTS_GLOWSTONE))
		.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(LensBlocks.RGBLOCK_GLASS_BORDER_GLOW.get().asItem())
		.requires(LensBlocks.RGBLOCK_GLASS_BORDER.get().asItem())
		.requires(Tags.Items.DUSTS_GLOWSTONE)
		.unlockedBy("has_glowstone", has(Tags.Items.DUSTS_GLOWSTONE))
		.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(LensBlocks.RGBLOCK_GLOW.get().asItem(),8)
		.requires(Tags.Items.DUSTS_GLOWSTONE)
		.requires(LensBlocks.RGBLOCK.get().asItem(),8)
		.unlockedBy("has_glowstone",has(Tags.Items.DUSTS_GLOWSTONE))
		.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(LensBlocks.RGBLOCK_BRICK_GLOW.get().asItem(),8)
		.requires(Tags.Items.DUSTS_GLOWSTONE)
		.requires(LensBlocks.RGBLOCK_BRICK.get().asItem(),8)
		.unlockedBy("has_glowstone",has(Tags.Items.DUSTS_GLOWSTONE))
		.save(consumer);
		
		//Shapeless Tool
		
		LRNCRecipeBuilder.crafting(LensBlocks.RGBLOCK_GLASS.get().asItem(), LensItems.RGB_INATOR.get())
		.addIngredient(Tags.Items.GLASS,1)
		.addCriterion("has_glass", has(Tags.Items.GLASS))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(LensBlocks.CLEARGLASS.get().asItem(), LensItems.RGB_INATOR.get())
		.addIngredient(LensBlocks.RGBLOCK_GLASS.get().asItem(),1)
		.addCriterion("has_glass", has(Tags.Items.GLASS))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(LensBlocks.RGBLOCK.get().asItem(), LensItems.RGB_INATOR.get())
		.addIngredient(Tags.Items.STONE,1)
		.addCriterion("has_stone", has(Tags.Items.STONE))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(LensBlocks.RGBLOCK_BRICK.get().asItem(), LensItems.RGB_INATOR.get())
		.addIngredient(Items.STONE_BRICKS, 1)
		.addCriterion("has_stone_bricks", has(Items.STONE_BRICKS))
		.build(consumer);
		
		
		//Concrete BS
		LRNCRecipeBuilder.crafting(Items.BLACK_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.BLACK_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.BLUE_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.BLUE_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.BROWN_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.BROWN_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.CYAN_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.CYAN_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.GREEN_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.GREEN_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.GRAY_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.GRAY_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.LIGHT_BLUE_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.LIGHT_BLUE_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.LIGHT_GRAY_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.LIGHT_GRAY_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.LIME_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.LIME_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.MAGENTA_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.MAGENTA_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.ORANGE_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.ORANGE_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.PINK_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.PINK_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.PURPLE_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.PURPLE_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.RED_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.RED_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.WHITE_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.WHITE_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(Items.YELLOW_CONCRETE, Items.WATER_BUCKET)
		.addIngredient(Items.YELLOW_CONCRETE_POWDER,1)
		.addCriterion("has_bucket", has(Items.WATER_BUCKET))
		.build(consumer);
		
		//GunSmithing
		
		//Guns
		
		GunSmithingRecipeBuilder.crafting(LensItems.GUN_FIFTYCAL.get())
		.addIngredient(Tags.Items.INGOTS_IRON,12)
		.addIngredient(Tags.Items.GLASS,2)
		.addCriterion("has_iron", has(Tags.Items.INGOTS_IRON))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.GUN_LASER_PISTOL.get())
		.addIngredient(Tags.Items.INGOTS_IRON,3)
		.addIngredient(Tags.Items.INGOTS_COPPER,2)
		.addIngredient(Tags.Items.GLASS,1)
		.addIngredient(Tags.Items.DUSTS_REDSTONE,9)
		.addIngredient(ItemTags.PLANKS,2)
		.addCriterion("has_redstone", has(Tags.Items.DUSTS_REDSTONE))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.GUN_REVOLVER.get())
		.addIngredient(Tags.Items.INGOTS_IRON,3)
		.addIngredient(ItemTags.PLANKS,2)
		.addCriterion("has_iron", has(Tags.Items.INGOTS_IRON))
		.build(consumer);
		
		
		//Tactical Pouch
		GunSmithingRecipeBuilder.crafting(LensItems.TACTICAL_POUCHES.get())
		.addIngredient(Tags.Items.LEATHER,5)
		.addIngredient(Tags.Items.CHESTS_WOODEN,1)
		.addIngredient(Tags.Items.INGOTS_COPPER,1)
		.addIngredient(Tags.Items.STRING,3)
		.addCriterion("has_leather", has(Tags.Items.LEATHER))
		.build(consumer);
		
		//Ammo
		
		GunSmithingRecipeBuilder.crafting(LensItems.SIMPLE_BULLET.get(), 64)
		.addIngredient(Tags.Items.GUNPOWDER,2)
		.addIngredient(Tags.Items.COBBLESTONE, 2)
		.addCriterion("has_gunpowder",has(Tags.Items.GUNPOWDER))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.SHOTGUN_SHELL.get(),32)
		.addIngredient(Tags.Items.GUNPOWDER,3)
		.addIngredient(Tags.Items.INGOTS_COPPER,3)
		.addIngredient(ModTags.Items.FORGELEAD,1)
		.addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.HE_GRENADE_SHELL.get(), 24)
		.addIngredient(Items.TNT,1)
		.addIngredient(Tags.Items.GUNPOWDER,2)
		.addIngredient(Tags.Items.INGOTS_IRON,5)
		.addCriterion("has_tnt", has(Items.TNT))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.PISTOL_ROUND.get(),64)
		.addIngredient(Tags.Items.INGOTS_COPPER,5)
		.addIngredient(Tags.Items.GUNPOWDER,2)
		.addIngredient(ModTags.Items.FORGELEAD,1)
		.addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.RIFLE_ROUND.get(),32)
		.addIngredient(Tags.Items.INGOTS_COPPER,5)
		.addIngredient(Tags.Items.GUNPOWDER,3)
		.addIngredient(ModTags.Items.FORGELEAD,1)
		.addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.SNIPER_ROUND.get(),16)
		.addIngredient(Tags.Items.INGOTS_COPPER,5)
		.addIngredient(Tags.Items.GUNPOWDER,4)
		.addIngredient(ModTags.Items.FORGELEAD,1)
		.addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.ARTILLERY_SHELL.get(),16)
		.addIngredient(Items.TNT,2)
		.addIngredient(Tags.Items.GUNPOWDER,4)
		.addIngredient(Tags.Items.INGOTS_IRON,5)
		.addCriterion("has_tnt", has(Items.TNT))
		.build(consumer);
		
		GunSmithingRecipeBuilder.crafting(LensItems.ROCKET.get(),16)
		.addIngredient(Items.TNT,1)
		.addIngredient(Tags.Items.GUNPOWDER,3)
		.addIngredient(Tags.Items.INGOTS_IRON,3)
		.addCriterion("has_tnt", has(Items.TNT))
		.build(consumer);
	}
	
	
	
	//Util functions
	
	private void simpleStairs(ItemLike material,ItemLike result,Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(result)
		.pattern("X  ")
		.pattern("XX ")
		.pattern("XXX")
		.define('X', material)
		.unlockedBy("has_item", has(material))
		.save(consumer);
	}
	
	private void simpleSlab(ItemLike material,ItemLike result,Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(result)
		.pattern("XXX")
		.define('X', material)
		.unlockedBy("has_item", has(material))
		.save(consumer);
	}
	
}
