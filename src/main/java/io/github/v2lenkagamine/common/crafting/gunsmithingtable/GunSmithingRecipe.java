package io.github.v2lenkagamine.common.crafting.gunsmithingtable;

import com.google.common.collect.ImmutableList;

import io.github.v2lenkagamine.common.tileentity.GunSmithingTableTE;
import io.github.v2lenkagamine.core.init.LensRecipesSerializer;
import io.github.v2lenkagamine.core.util.ItemUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;


//Stolen from MrCrayfish, what a shock.
public class GunSmithingRecipe implements Recipe<GunSmithingTableTE>{
	    private final ResourceLocation id;
	    private final ItemStack item;
	    private final ImmutableList<GunSmithingIngredient> materials;

	    public GunSmithingRecipe(ResourceLocation id, ItemStack item, ImmutableList<GunSmithingIngredient> materials)
	    {
	        this.id = id;
	        this.item = item;
	        this.materials = materials;
	    }

	    public ItemStack getItem()
	    {
	        return this.item.copy();
	    }

	    public ImmutableList<GunSmithingIngredient> getMaterials()
	    {
	        return this.materials;
	    }

	    @Override
	    public boolean matches(GunSmithingTableTE inv, Level worldIn)
	    {
	        return false;
	    }

	    @Override
	    public ItemStack assemble(GunSmithingTableTE inv)
	    {
	        return ItemStack.EMPTY;
	    }

	    @Override
	    public boolean canCraftInDimensions(int width, int height)
	    {
	        return true;
	    }

	    @Override
	    public ItemStack getResultItem()
	    {
	        return this.item.copy();
	    }

	    @Override
	    public ResourceLocation getId()
	    {
	        return this.id;
	    }

	    @Override
	    public RecipeSerializer<?> getSerializer()
	    {
	        return LensRecipesSerializer.GUNSMITHING.get();
	    }

	    @Override
	    public net.minecraft.world.item.crafting.RecipeType<?> getType()
	    {
	        return GunSmithingRecipeType.GUNSMITHING;
	    }

	    public boolean hasMaterials(Player player)
	    {
	    	
	        for(GunSmithingIngredient ingredient : this.getMaterials())
	        {
	            if(!ItemUtil.findGSIContainer(player, ingredient))
	            {
	                return false;
	            }
	        }
	        return true;
	    }

	    public void consumeMaterials(Player player)
	    {
	        for(GunSmithingIngredient ingredient : this.getMaterials())
	        {
	            ItemUtil.consumeGSIContainer(player, ingredient);
	        }
	    }
}
