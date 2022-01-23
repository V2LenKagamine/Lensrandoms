package io.github.v2lenkagamine.common.crafting.lensshapelessnoconsume;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import io.github.v2lenkagamine.core.init.LensRecipesSerializer;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class LRNCRecipe extends ShapelessRecipe{
	private final ItemStack item;
	private final ImmutableList<Ingredient> materials;
	private final Ingredient toolItem;

	public LRNCRecipe(ResourceLocation id,String group, Ingredient toolItem, ItemStack item, NonNullList<Ingredient> materials) {
		super(id,group,item,materials);
		this.item = item;
		this.materials = ImmutableList.copyOf(materials);
		this.toolItem = toolItem;

	}
	public ItemStack getItem() {
		return item.copy();
	}

	public ImmutableList<Ingredient> getMaterials() {
		return this.materials;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
		NonNullList<ItemStack> returned = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
		boolean hasTool = false;

		for (int i = 0; i < returned.size(); i++) {
			ItemStack stack = inv.getItem(i);

			if (!hasTool && toolItem.test(stack)) {
				returned.set(i, stack.copy());
				hasTool = true;
			} else if (stack.hasContainerItem()) {
				returned.set(i, stack.getContainerItem());
			}
		}
		return returned;
	}
	
	@Override
	public ItemStack assemble(CraftingContainer inv) {
		return this.getResultItem().copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= materials.size() + 1;
	}

	@Override
	public ItemStack getResultItem() {
		return item;
	}


	@Override
	public RecipeType<?> getType() {
		return RecipeType.CRAFTING;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return LensRecipesSerializer.LRNC.get();
	}

	public static class LRNCRecipeSerializer
			extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>>
			implements RecipeSerializer<LRNCRecipe> {

		@Override
		public LRNCRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			
			String group = GsonHelper.getAsString(json, "group","");
			NonNullList<Ingredient> ingredients = NonNullList.create();
			Ingredient toolItem = Ingredient.fromJson(json.get("tool"));
			ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			JsonArray ingredientsArray = json.getAsJsonArray("ingredients");

			for (JsonElement ingredientElement : ingredientsArray) {
				Ingredient ingredient = Ingredient.fromJson(ingredientElement);

				if (!ingredient.isEmpty())
					ingredients.add(ingredient);
			}

			if (ingredients.isEmpty())
				throw new JsonParseException(
						"No valid ingredients for tool interaction recipe: " + recipeId.toString());

			return new LRNCRecipe(recipeId,group,toolItem, result, ingredients);
		}

		@Nullable
		@Override
		public LRNCRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			int ingredientsCount = buffer.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientsCount, Ingredient.EMPTY);

			for (int i = 0; i < ingredients.size(); i++) {
				ingredients.set(i, Ingredient.fromNetwork(buffer));
			}
			String group = buffer.readUtf(Short.MAX_VALUE);
			Ingredient toolItem = Ingredient.fromNetwork(buffer);
			ItemStack result = buffer.readItem();
		
			return new LRNCRecipe(recipeId, group ,toolItem, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, LRNCRecipe recipe) {
			buffer.writeVarInt(recipe.materials.size());
			
			for (Ingredient ingredient : recipe.materials) {
				ingredient.toNetwork(buffer);
			}
			buffer.writeUtf(recipe.getGroup());
			recipe.toolItem.toNetwork(buffer);
			buffer.writeItem(recipe.item);
		}
	}

}
