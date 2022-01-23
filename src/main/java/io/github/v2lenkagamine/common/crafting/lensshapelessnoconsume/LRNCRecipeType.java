package io.github.v2lenkagamine.common.crafting.lensshapelessnoconsume;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class LRNCRecipeType {
	
	public static final RecipeType<LRNCRecipe> LRNC = register("lensrandoms:lrnc");

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