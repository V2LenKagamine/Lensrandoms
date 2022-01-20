package io.github.v2lenkagamine.common.crafting.gunsmithingtable;

import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

//Guess whooOOOOO (Its MrCrayfish.)
public class GunSmithingRecipes {
	 public static boolean isEmpty(Level world)
	    {
	        return world.getRecipeManager().getRecipes().stream().noneMatch(recipe -> recipe.getType() == GunSmithingRecipeType.GUNSMITHING);
	    }

	    public static NonNullList<GunSmithingRecipe> getAll(Level world)
	    {
	        return world.getRecipeManager().getRecipes().stream()
	                .filter(recipe -> recipe.getType() == GunSmithingRecipeType.GUNSMITHING)
	                .map(recipe -> (GunSmithingRecipe) recipe)
	                .collect(Collectors.toCollection(NonNullList::create));
	    }

	    @Nullable
	    public static GunSmithingRecipe getRecipeById(Level world, ResourceLocation id)
	    {
	        return world.getRecipeManager().getRecipes().stream()
	                .filter(recipe -> recipe.getType() == GunSmithingRecipeType.GUNSMITHING)
	                .map(recipe -> (GunSmithingRecipe) recipe)
	                .filter(recipe -> recipe.getId().equals(id))
	                .findFirst().orElse(null);
	    }
}
