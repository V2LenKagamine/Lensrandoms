package io.github.v2lenkagamine.common.crafting.gunsmithingtable;

import java.util.stream.Stream;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.Tag;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.IIngredientSerializer;


//Stolen almost char for char from MrCrayFish's gun mod.
public class GunSmithingIngredient extends Ingredient{
	
	private final Value itemList;
	private final int count;
	
	
	protected GunSmithingIngredient(Stream<? extends Value> itemList,int count) {
		super(itemList);
		this.itemList = null;
		this.count = count;
	}

	private GunSmithingIngredient(Value itemList,int count) {
		super(Stream.of(itemList));
		this.itemList = itemList;
		this.count = count;
	}
	
	public int getCount() {
		return this.count;
	}
	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
	        return Serializer.INSTANCE;
	}
	
	public static GunSmithingIngredient fromJson(JsonObject object)
    {
        Ingredient.Value value = valueFromJson(object);
        int count = GsonHelper.getAsInt(object, "count", 1);
        return new GunSmithingIngredient(Stream.of(value), count);
    }

    @Override
    public JsonElement toJson()
    {
        JsonObject object = this.itemList.serialize();
        object.addProperty("count", this.count);
        return object;
    }
	public static GunSmithingIngredient of(ItemLike provider, int count)
    {
        return new GunSmithingIngredient(new Ingredient.ItemValue(new ItemStack(provider)), count);
    }
	
	public static GunSmithingIngredient of(ItemStack stack, int count)
    {
        return new GunSmithingIngredient(new Ingredient.ItemValue(stack), count);
    }

    public static GunSmithingIngredient of(Tag<Item> tag, int count)
    {
        return new GunSmithingIngredient(new Ingredient.TagValue(tag), count);
    }

    public static class Serializer implements IIngredientSerializer<GunSmithingIngredient>
    {
        public static final GunSmithingIngredient.Serializer INSTANCE = new GunSmithingIngredient.Serializer();

        @Override
        public GunSmithingIngredient parse(FriendlyByteBuf buffer)
        {
            int itemCount = buffer.readVarInt();
            int count = buffer.readVarInt();
            Stream<Ingredient.ItemValue> values = Stream.generate(() -> new ItemValue(buffer.readItem())).limit(itemCount);
            return new GunSmithingIngredient(values, count);
        }

        @Override
        public GunSmithingIngredient parse(JsonObject object)
        {
            return GunSmithingIngredient.fromJson(object);
        }

        @Override
        public void write(FriendlyByteBuf buffer, GunSmithingIngredient ingredient)
        {
            buffer.writeVarInt(ingredient.getItems().length);
            buffer.writeVarInt(ingredient.count);
            for(ItemStack stack : ingredient.getItems())
            {
                buffer.writeItem(stack);
            }
        }
    }
	
	
}
