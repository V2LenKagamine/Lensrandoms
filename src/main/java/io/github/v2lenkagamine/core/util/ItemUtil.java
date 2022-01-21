package io.github.v2lenkagamine.core.util;

import java.util.ArrayList;

import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingIngredient;
import io.github.v2lenkagamine.core.items.LensItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import top.theillusivec4.curios.api.CuriosApi;

public class ItemUtil{
	public static boolean getPlayerItemStack(LivingEntity entity, ItemStack stack, boolean consumeItem, int amount) {
		
		if (!(entity instanceof Player player)) {return false;}
		player = (Player) entity;
		
		if (stack.isEmpty())
			return false;

		if (amount <= 0 || player.isCreative())
			return true;

		if (amount == 1) {
			ItemStack checkStack;

			if (isStackSame((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else if (isStackSame((checkStack = player.getItemInHand(InteractionHand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
				if (consumeItem)
					checkStack.shrink(1);

				return true;
			}
			else {
				for (ItemStack inventoryStack : player.getInventory().items) {
					if (!inventoryStack.isEmpty() && isStackSame(stack, inventoryStack)) {
						if (consumeItem)
							inventoryStack.shrink(1);

						return true;
					}
				}

				for (ItemStack armorStack : player.getInventory().armor) {
					if (!armorStack.isEmpty() && isStackSame(stack, armorStack)) {
						if (consumeItem)
							armorStack.shrink(1);

						return true;
					}
				}
			}

			return false;
		}
		else {
			ArrayList<ItemStack> matchedStacks = new ArrayList<ItemStack>();
			int totalStack = 0;
			ItemStack checkStack;

			if (isStackSame((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND)), stack) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				totalStack += checkStack.getCount();
			}

			if (totalStack < amount && isStackSame((checkStack = player.getItemInHand(InteractionHand.OFF_HAND)), stack) && !checkStack.isEmpty()) {
				matchedStacks.add(checkStack);
				totalStack += checkStack.getCount();
			}

			if (totalStack < amount) {
				for (ItemStack mainStack : player.getInventory().items) {
					if (!mainStack.isEmpty() && isStackSame(stack, mainStack)) {
						matchedStacks.add(mainStack);
						totalStack += mainStack.getCount();

						if (totalStack >= amount)
							break;
					}
				}
			}

			if (totalStack < amount) {
				for (ItemStack armorStack : player.getInventory().armor) {
					if (!armorStack.isEmpty() && isStackSame(stack, armorStack)) {
						matchedStacks.add(armorStack);
						totalStack += armorStack.getCount();

						if (totalStack >= amount)
							break;
					}
				}
			}

			if (totalStack < amount)
				return false;

			if (!consumeItem)
				return true;

			for (ItemStack matchedStack : matchedStacks) {
				int consumeAmount = Math.min(matchedStack.getCount(), Math.min(amount, totalStack));

				matchedStack.shrink(consumeAmount);
				totalStack -= consumeAmount;
			}

			return true;
		}
	}
	
public static boolean findPlayerItem(LivingEntity entity, Item item, int amount) {
		
		if (!(entity instanceof Player player)) {return false;}
		player = (Player) entity;
		
		if (amount <= 0 || player.isCreative())
			return true;

		if (amount == 1) {
			@SuppressWarnings("unused")
			Item checkStack;

			if (isItemSame((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND).getItem()), item)) {
				return true;
			}
			else if (isItemSame((checkStack = player.getItemInHand(InteractionHand.OFF_HAND).getItem()), item)) {
				return true;
			}
			else {
				for (ItemStack inventoryStack : player.getInventory().items) {
					if (!inventoryStack.isEmpty() && isItemSame(item, inventoryStack.getItem())) {
						return true;
					}
				}

				for (ItemStack armorStack : player.getInventory().armor) {
					if (!armorStack.isEmpty() && isItemSame(item, armorStack.getItem())) {
						return true;
					}
				}
			}

			return false;
		}
		else {
			ArrayList<Item> matchedStacks = new ArrayList<Item>();
			int totalStack = 0;
			Item checkStack;

			if (isItemSame((checkStack = player.getItemInHand(InteractionHand.MAIN_HAND).getItem()), item)) {
				matchedStacks.add(checkStack);
				totalStack ++;
			}

			if (totalStack < amount && isItemSame((checkStack = player.getItemInHand(InteractionHand.OFF_HAND).getItem()), item)) {
				matchedStacks.add(checkStack);
				totalStack ++;
			}

			if (totalStack < amount) {
				for (ItemStack mainStack : player.getInventory().items) {
					if (!mainStack.isEmpty() && isItemSame(item, mainStack.getItem())) {
						matchedStacks.add(mainStack.getItem());
						totalStack += mainStack.getCount();

						if (totalStack >= amount)
							break;
					}
				}
			}

			if (totalStack < amount) {
				for (ItemStack armorStack : player.getInventory().armor) {
					if (!armorStack.isEmpty() && isItemSame(item, armorStack.getItem())) {
						matchedStacks.add(armorStack.getItem());
						totalStack += armorStack.getCount();

						if (totalStack >= amount)
							break;
					}
				}
			}

			if (totalStack < amount)
				return false;
			return true;
		}
	}
	
	
	public static boolean lookInPouches(LivingEntity entity,ItemStack item,boolean consume,int amount) {
		if (!(entity instanceof Player player)) {return false;}
		player = (Player) entity;
		
		if (item.isEmpty())
			return false;

		if (amount <= 0 || player.isCreative())
			return true;
		
		int slotNum = 0;
		var stacks = CuriosApi.getCuriosHelper().getCuriosHandler(player).orElse(null).getCurios().get("belt").getStacks();
		for (int i = 0 ; i < stacks.getSlots();i++) {
			ItemStack beltCurioItem = stacks.getStackInSlot(i);
			if (beltCurioItem.getItem() == LensItems.TACTICAL_POUCHES.get()) {
				LazyOptional<IItemHandler> beltCap = beltCurioItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
				for (int y = 0; y < 9;y++) {
					ItemStack bulletStack = beltCap.orElse(null).getStackInSlot(y);
					if(isStackSame(bulletStack,item)) {
						if (consume) bulletStack.shrink(amount);
						return true;
					}
				}
			}
		}
		for(ItemStack inventoryStack : player.getInventory().items) {
			if (inventoryStack.getItem() == LensItems.TACTICAL_POUCHES.get()) {
				LazyOptional<IItemHandler> beltCap = player.getInventory().getItem(slotNum).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
					for (int x = 0; x < 9; x++) {
						ItemStack bulletStack = beltCap.orElse(null).getStackInSlot(x);
						if (isStackSame(bulletStack,item) && consume) {
							if (consume) bulletStack.shrink(amount);
							return true;
							}
						}
					}
				slotNum++;
				}
				
		return false;
		}
	//Start MrCrayfish Code
	public static boolean findGSIContainer(Player player, GunSmithingIngredient ingredient) {
        int count = 0;
        for(ItemStack stack : player.getInventory().items)
        {
            if(!stack.isEmpty() && ingredient.test(stack))
            {
                count += stack.getCount();
            }
        }
        return ingredient.getCount() <= count;
    }
	
	public static boolean consumeGSIContainer(Player player,GunSmithingIngredient ingredient)
    {
        int amount = ingredient.getCount();
        for(int i = 0; i < player.getInventory().getContainerSize(); i++)
        {
            ItemStack stack = player.getInventory().getItem(i);
            if(!stack.isEmpty() && ingredient.test(stack))
            {
                if(amount - stack.getCount() < 0)
                {
                    stack.shrink(amount);
                    return true;
                }
                else
                {
                    amount -= stack.getCount();
                    player.getInventory().items.set(i, ItemStack.EMPTY);
                    if(amount == 0) return true;
                }
            }
        }
        return false;
    }
	
	public static int findItemStackContainer(Player player, ItemStack find)
    {
        int count = 0;
        for(ItemStack stack : player.getInventory().items)
        {
            if(!stack.isEmpty() && isStackSame(stack, find))
            {
                count += stack.getCount();
            }
        }
        return count;
    }

    public static boolean consumeItemStackContainer(Player player, ItemStack find)
    {
        int amount = find.getCount();
        for(int i = 0; i < player.getInventory().getContainerSize(); i++)
        {
            ItemStack stack = player.getInventory().getItem(i);
            if(!stack.isEmpty() && isStackSame(stack, find))
            {
                if(amount - stack.getCount() < 0)
                {
                    stack.shrink(amount);
                    return true;
                }
                else
                {
                    amount -= stack.getCount();
                    player.getInventory().items.set(i, ItemStack.EMPTY);
                    if(amount == 0)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
	//End MrCrayfish code.
	
	public static boolean isItemSame(Item a,Item b) {
		if (a != b) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean isStackSame(ItemStack a, ItemStack b) {
		if (a.getItem() != b.getItem())
			return false;
		return !a.hasTag() ? !b.hasTag() : b.hasTag() && a.getTag().equals(b.getTag());
	}
	
}
