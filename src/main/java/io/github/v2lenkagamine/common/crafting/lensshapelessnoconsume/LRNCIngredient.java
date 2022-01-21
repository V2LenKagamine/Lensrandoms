package io.github.v2lenkagamine.common.crafting.lensshapelessnoconsume;

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

public class LRNCIngredient extends Ingredient{

	private final Value itemList;
	private final int count;
	
	
	protected LRNCIngredient(Stream<? extends Value> itemList,int count) {
		super(itemList);
		this.itemList = null;
		this.count = count;
	}

	private LRNCIngredient(Value itemList,int count) {
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
	
	public static LRNCIngredient fromJson(JsonObject object)
    {
        Ingredient.Value value = valueFromJson(object);
        int count = GsonHelper.getAsInt(object, "count", 1);
        return new LRNCIngredient(Stream.of(value), count);
    }

    @Override
    public JsonElement toJson()
    {
        JsonObject object = this.itemList.serialize();
        object.addProperty("count", this.count);
        return object;
    }
	public static LRNCIngredient of(ItemLike provider, int count)
    {
        return new LRNCIngredient(new Ingredient.ItemValue(new ItemStack(provider)), count);
    }
	
	public static LRNCIngredient of(ItemStack stack, int count)
    {
        return new LRNCIngredient(new Ingredient.ItemValue(stack), count);
    }

    public static LRNCIngredient of(Tag<Item> tag, int count)
    {
        return new LRNCIngredient(new Ingredient.TagValue(tag), count);
    }

    public static class Serializer implements IIngredientSerializer<LRNCIngredient>
    {
        public static final LRNCIngredient.Serializer INSTANCE = new LRNCIngredient.Serializer();

        @Override
        public LRNCIngredient parse(FriendlyByteBuf buffer)
        {
            int itemCount = buffer.readVarInt();
            int count = buffer.readVarInt();
            Stream<Ingredient.ItemValue> values = Stream.generate(() -> new ItemValue(buffer.readItem())).limit(itemCount);
            return new LRNCIngredient(values, count);
        }

        @Override
        public LRNCIngredient parse(JsonObject object)
        {
            return LRNCIngredient.fromJson(object);
        }

        @Override
        public void write(FriendlyByteBuf buffer, LRNCIngredient ingredient)
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
