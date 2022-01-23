package io.github.v2lenkagamine.common.networking;

import io.github.v2lenkagamine.common.containers.GunSmithingTableContainer;
import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingRecipe;
import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//Stolen. You know who (MrCrayfish)
public class ServerMessageHandler{
	
	public static void handleCraft(ServerPlayer player, ResourceLocation id, BlockPos pos)
    {
        Level world = player.level;

        if(player.containerMenu instanceof GunSmithingTableContainer)
        {
            GunSmithingTableContainer GunSmithing = (GunSmithingTableContainer) player.containerMenu;
            if(GunSmithing.getPos().equals(pos))
            {
                GunSmithingRecipe recipe = GunSmithingRecipes.getRecipeById(world, id);
                if(recipe == null || !recipe.hasMaterials(player))
                    return;

                recipe.consumeMaterials(player);

                /* Gets the color based on the dye */
                ItemStack stack = recipe.getItem();
                
                Containers.dropItemStack(world, player.getX(), player.getY(),player.getZ(), stack);
            }
        }
    }
}
