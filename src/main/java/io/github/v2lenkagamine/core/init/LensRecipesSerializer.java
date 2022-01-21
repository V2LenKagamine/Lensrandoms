package io.github.v2lenkagamine.core.init;

import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingSerializer;
import io.github.v2lenkagamine.common.crafting.lensshapelessnoconsume.LRNCRecipe.LRNCRecipeSerializer;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraftforge.registries.RegistryObject;

public class LensRecipesSerializer {

	
	public static final RegistryObject<GunSmithingSerializer> GUNSMITHING = RegistryHelper.RECIPES.register("gunsmithing_bench", GunSmithingSerializer::new);
	public static final RegistryObject<LRNCRecipeSerializer> LRNC = RegistryHelper.RECIPES.register("lrnc", LRNCRecipeSerializer::new);
	
	
	//You know the drill no touch this
	public static void register() {
	}
}
