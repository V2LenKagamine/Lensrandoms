package io.github.v2lenkagamine.common.crafting.gunsmithingtable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.github.v2lenkagamine.core.init.LensRecipesSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

//Made by.... Ocelot? Not MrCrayfish? Huh, neat.
public class GunSmithingRecipeBuilder {

	 private final Item result;
	    private final int count;
	    private final List<GunSmithingIngredient> ingredients;
	    private final List<ICondition> conditions = new ArrayList<>();
	    private final Advancement.Builder advancementBuilder;
	    
	    
	    
	    private GunSmithingRecipeBuilder(ItemLike item, int count)
	    {
	        this.result = item.asItem();
	        this.count = count;
	        this.ingredients = new ArrayList<>();
	        this.advancementBuilder = Advancement.Builder.advancement();
	        
	    }

	    public static GunSmithingRecipeBuilder crafting(ItemLike item)
	    {
	        return new GunSmithingRecipeBuilder(item, 1);
	    }

	    public static GunSmithingRecipeBuilder crafting(ItemLike item, int count)
	    {
	        return new GunSmithingRecipeBuilder(item, count);
	    }

		public GunSmithingRecipeBuilder addIngredient(Named<Item> tag, int count2) {
			this.ingredients.add(GunSmithingIngredient.of(tag, count));
			return this;
		}
	    
		public GunSmithingRecipeBuilder addIngredient(IOptionalNamedTag<Item> tag, int count) {
			this.ingredients.add(GunSmithingIngredient.of(tag, count));
			return this;
		}
	    
	    public GunSmithingRecipeBuilder addIngredient(ItemLike item, int count)
	    {
	        this.ingredients.add(GunSmithingIngredient.of(item, count));
	        return this;
	    }

	    public GunSmithingRecipeBuilder addIngredient(GunSmithingIngredient ingredient)
	    {
	        this.ingredients.add(ingredient);
	        return this;
	    }

	    public GunSmithingRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn)
	    {
	        this.advancementBuilder.addCriterion(name, criterionIn);
	        return this;
	    }

	    public GunSmithingRecipeBuilder addCondition(ICondition condition)
	    {
	        this.conditions.add(condition);
	        return this;
	    }

	    @SuppressWarnings("deprecation")
		public void build(Consumer<FinishedRecipe> consumer)
	    {
	        ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
	        this.build(consumer, resourcelocation);
	    }

	    public void build(Consumer<FinishedRecipe> consumer, ResourceLocation id)
	    {
	    	this.validate(id);
	        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
	        consumer.accept(new GunSmithingRecipeBuilder.Result(id, this.result, this.count, this.ingredients, this.conditions, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
	    }

	    private void validate(ResourceLocation id)
	    {
	        if(this.advancementBuilder.getCriteria().isEmpty())
	        {
	            throw new IllegalStateException("No way of obtaining recipe " + id);
	        }
	    }
	    
	    public static class Result implements FinishedRecipe
	    {
	        private final ResourceLocation id;
	        private final Item item;
	        private final int count;
	        private final List<GunSmithingIngredient> ingredients;
	        private final List<ICondition> conditions;
	        private final Advancement.Builder advancement;
	        private final ResourceLocation advancementId;

	        public Result(ResourceLocation id, ItemLike item, int count, List<GunSmithingIngredient> ingredients, List<ICondition> conditions, Advancement.Builder advancement, ResourceLocation advancementId)
	        {
	            this.id = id;
	            this.item = item.asItem();
	            this.count = count;
	            this.ingredients = ingredients;
	            this.conditions = conditions;
	            this.advancement = advancement;
	            this.advancementId = advancementId;
	        }

	        @SuppressWarnings("deprecation")
			@Override
	        public void serializeRecipeData(JsonObject json)
	        {
	            JsonArray conditions = new JsonArray();
	            this.conditions.forEach(condition -> conditions.add(CraftingHelper.serialize(condition)));
	            if(conditions.size() > 0)
	            {
	                json.add("conditions", conditions);
	            }

	            JsonArray materials = new JsonArray();
	            this.ingredients.forEach(ingredient -> materials.add(ingredient.toJson()));
	            json.add("materials", materials);

	            JsonObject resultObject = new JsonObject();
	            resultObject.addProperty("item", Registry.ITEM.getKey(this.item).toString());
	            if(this.count > 1)
	            {
	                resultObject.addProperty("count", this.count);
	            }
	            json.add("result", resultObject);
	        }

	        @Override
	        public ResourceLocation getId()
	        {
	            return this.id;
	        }

	        @Override
	        public RecipeSerializer<?> getType()
	        {
	            return LensRecipesSerializer.GUNSMITHING.get();
	        }

	        @Override
	        public JsonObject serializeAdvancement()
	        {
	            return this.advancement.serializeToJson();
	        }

	        @Override
	        public ResourceLocation getAdvancementId()
	        {
	            return this.advancementId;
	        }
	    }
}
