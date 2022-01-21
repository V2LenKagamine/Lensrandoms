package io.github.v2lenkagamine.common.crafting.lensshapelessnoconsume;

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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class LRNCRecipeBuilder {
	private final Item result;
    private final int count;
    private final Item tool;
    private final List<LRNCIngredient> ingredients;
    private final List<ICondition> conditions = new ArrayList<>();
    private final Advancement.Builder advancementBuilder;
    
    
    
    private LRNCRecipeBuilder(ItemLike item, int count, ItemLike tool)
    {
        this.result = item.asItem();
        this.count = count;
		this.tool = tool.asItem();
        this.ingredients = new ArrayList<>();
        this.advancementBuilder = Advancement.Builder.advancement();
        
    }

    public static LRNCRecipeBuilder crafting(ItemLike item,ItemLike tool)
    {
        return new LRNCRecipeBuilder(item, 1, tool);
    }

    public static LRNCRecipeBuilder crafting(ItemLike item, int count,ItemLike tool)
    {
        return new LRNCRecipeBuilder(item, count, tool);
    }
    
	public LRNCRecipeBuilder addIngredient(IOptionalNamedTag<Item> tag, int count) {
		this.ingredients.add(LRNCIngredient.of(tag, count));
		return this;
	}

    public LRNCRecipeBuilder addIngredient(ItemLike item, int count)
    {
        this.ingredients.add(LRNCIngredient.of(item, count));
        return this;
    }

    public LRNCRecipeBuilder addIngredient(LRNCIngredient ingredient)
    {
        this.ingredients.add(ingredient);
        return this;
    }

    public LRNCRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn)
    {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public LRNCRecipeBuilder addCondition(ICondition condition)
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
        consumer.accept(new LRNCRecipeBuilder.Result(id, this.result, this.tool, this.count, this.ingredients, this.conditions, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
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
        private final Item tool;
        private final List<LRNCIngredient> ingredients;
        private final List<ICondition> conditions;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, ItemLike item,ItemLike tool, int count, List<LRNCIngredient> ingredients, List<ICondition> conditions, Advancement.Builder advancement, ResourceLocation advancementId)
        {
            this.id = id;
            this.item = item.asItem();
            this.tool = tool.asItem();
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
            JsonArray tools = new JsonArray();
            tools.add(LRNCIngredient.of(tool,1).toJson());
            JsonArray materials = new JsonArray();
            this.ingredients.forEach(ingredient -> materials.add(ingredient.toJson()));
            materials.add(LRNCIngredient.of(tool,1).toJson());
            JsonObject resultObject = new JsonObject();
            resultObject.addProperty("item", Registry.ITEM.getKey(this.item).toString());
            if(this.count > 1)
            {
                resultObject.addProperty("count", this.count);
            }
            json.add("tool", tools);
            json.add("ingredients", materials);
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
            return LensRecipesSerializer.LRNC.get();
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
