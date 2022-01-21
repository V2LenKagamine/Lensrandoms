package io.github.v2lenkagamine.datagen;

import java.util.function.Consumer;

import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingRecipeBuilder;
import io.github.v2lenkagamine.common.crafting.lensshapelessnoconsume.LRNCRecipeBuilder;
import io.github.v2lenkagamine.core.init.blocks.LensBlocks;
import io.github.v2lenkagamine.core.items.LensItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
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
		
		//Shapeless
		LRNCRecipeBuilder.crafting(LensBlocks.RGBLOCK_GLASS.get().asItem(), LensItems.RGB_INATOR.get())
		.addIngredient(Tags.Items.GLASS,1)
		.addCriterion("has_glass", has(Tags.Items.GLASS))
		.build(consumer);
		
		LRNCRecipeBuilder.crafting(LensBlocks.CLEARGLASS.get().asItem(), LensItems.RGB_INATOR.get())
		.addIngredient(LensBlocks.RGBLOCK_GLASS.get().asItem(),1)
		.addCriterion("has_glass", has(Tags.Items.GLASS))
		.build(consumer);
		
		//GunSmithing
		GunSmithingRecipeBuilder.crafting(LensItems.SIMPLE_BULLET.get(), 64)
		.addIngredient(Tags.Items.GUNPOWDER,2)
		.addIngredient(Tags.Items.COBBLESTONE, 2)
		.addCriterion("has_gunpowder",has(Tags.Items.GUNPOWDER))
		.build(consumer);
	}
	
}
