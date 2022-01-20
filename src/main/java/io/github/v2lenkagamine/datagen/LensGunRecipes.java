package io.github.v2lenkagamine.datagen;

import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class LensGunRecipes extends RecipeProvider{

	public LensGunRecipes(DataGenerator pGenerator) {
		super(pGenerator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		
		ShapedRecipeBuilder.shaped(io.github.v2lenkagamine.core.init.blocks.Blocks.GUNSMITHING_TABLE.get())
		.pattern("ICI")
		.pattern("ASB")
		.define('I', Tags.Items.INGOTS_IRON)
		.define('C', ItemTags.c)
		.define('A', Tags.Blocks.)
		
	}
	
}
