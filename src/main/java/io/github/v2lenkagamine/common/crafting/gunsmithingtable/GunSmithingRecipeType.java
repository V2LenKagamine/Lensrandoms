package io.github.v2lenkagamine.common.crafting.gunsmithingtable;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

//This is actually just stolen from MrCrayfish. Thx :B:
public class GunSmithingRecipeType {
	
	public static final RecipeType<GunSmithingRecipe> GUNSMITHING = register("lensrandoms:gunsmithingtable");

    static <T extends Recipe<?>> RecipeType<T> register(final String key)
    {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new RecipeType<T>()
        {
            @Override
            public String toString()
            {
                return key;
            }
        });
    }
}
