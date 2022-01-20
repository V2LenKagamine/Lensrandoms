package io.github.v2lenkagamine.common.crafting.gunsmithingtable;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;


//Can you guess who I stole this from? Answer at bottom!
public class GunSmithingSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<GunSmithingRecipe>{
	
	    @Override
	    public GunSmithingRecipe fromJson(ResourceLocation recipeId, JsonObject parent)
	    {
	        ImmutableList.Builder<GunSmithingIngredient> builder = ImmutableList.builder();
	        JsonArray input = GsonHelper.getAsJsonArray(parent, "materials");
	        for(int i = 0; i < input.size(); i++)
	        {
	            JsonObject object = input.get(i).getAsJsonObject();
	            builder.add(GunSmithingIngredient.fromJson(object));
	        }
	        if(!parent.has("result"))
	        {
	            throw new JsonSyntaxException("Missing result item entry");
	        }
	        JsonObject resultObject = GsonHelper.getAsJsonObject(parent, "result");
	        ItemStack resultItem = ShapedRecipe.itemStackFromJson(resultObject);
	        return new GunSmithingRecipe(recipeId, resultItem, builder.build());
	    }

	    @Nullable
	    @Override
	    public GunSmithingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
	    {
	        ItemStack result = buffer.readItem();
	        ImmutableList.Builder<GunSmithingIngredient> builder = ImmutableList.builder();
	        int size = buffer.readVarInt();
	        for(int i = 0; i < size; i++)
	        {
	            builder.add((GunSmithingIngredient) Ingredient.fromNetwork(buffer));
	        }
	        return new GunSmithingRecipe(recipeId, result, builder.build());
	    }

	    @Override
	    public void toNetwork(FriendlyByteBuf buffer, GunSmithingRecipe recipe)
	    {
	        buffer.writeItem(recipe.getItem());
	        buffer.writeVarInt(recipe.getMaterials().size());
	        for(GunSmithingIngredient ingredient : recipe.getMaterials())
	        {
	            ingredient.toNetwork(buffer);
	        }
	    }
}
//If you guessed "MrCrayfish", You win exactly 1 ITEMSTACK.EMPTY!