package io.github.v2lenkagamine.datagen;

import java.util.List;

import com.google.gson.JsonObject;

import io.github.v2lenkagamine.common.loottables.DungeonLootTables;
import io.github.v2lenkagamine.core.util.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.RegistryObject;

public class LensRandomsDungeonLootGenerator extends GlobalLootModifierProvider{

	public static final RegistryObject<LensRandomsDungeonLootGenerator.LensRandomsDungeonLootModifier.Serializer> DUNGEON_LOOT = 
			RegistryHelper.GLM.register("dungeon_loot",LensRandomsDungeonLootGenerator.LensRandomsDungeonLootModifier.Serializer::new);
	
	public LensRandomsDungeonLootGenerator(DataGenerator gen, String modId) {
		super(gen, modId);
	}

	@Override
	protected void start() {
		add("dungeon_loot",DUNGEON_LOOT.get(),new LensRandomsDungeonLootModifier(new LootItemCondition[] {
				getList(new String[] {
						"chests/simple_dungeon","chests/jungle_temple", "chests/abandoned_mineshaft","chests/bastion_treasure","chests/desert_pyramid","chests/end_city_treasure",
						"chests/ruined_portal","chests/pillager_outpost", "chests/nether_bridge","chests/stronghold_corridor",  "chests/stronghold_crossing", "chests/stronghold_library",
						"chests/woodland_mansion", "chests/underwater_ruin_big", "chests/underwater_ruin_small"
				})
		}));
		
	}
	
	public LootItemCondition getList(String[] chests) {
		LootItemCondition.Builder con = null;
		
		for(String string : chests) {
			if (con==null) {
				con = LootTableIdCondition.builder(new ResourceLocation(string));
				continue;
			}
		con= con.or(LootTableIdCondition.builder(new ResourceLocation(string)));
		}
		return con.build();
	}
	
	//No touch
	public static void register() {
	}
	
	
	public static class LensRandomsDungeonLootModifier extends LootModifier{
		
        public double commonChance;
        public double uncommonChance;
        public double rareChance;
        public double ultraChance;
        
        public int commonRolls;
        public int uncommonRolls;
        public int rareRolls;
        public int ultraRolls;
        
		public LensRandomsDungeonLootModifier(final LootItemCondition[] conditionsIn,double commonChance,double uncommonChance,double rareChance,double ultraChance,
				int commonRolls,int uncommonRolls,int rareRolls,int ultraRolls) {
			
			super(conditionsIn);
			this.commonChance = commonChance;
			this.uncommonChance = uncommonChance;
			this.rareChance = rareChance;
			this.ultraChance= ultraChance;
			
			this.commonRolls = commonRolls;
			this.uncommonRolls = uncommonRolls;
			this.rareRolls = rareRolls;
			this.ultraRolls = ultraRolls;
		}
		
		public LensRandomsDungeonLootModifier(final LootItemCondition[] conditionsIn) {
			super(conditionsIn);
			this.commonChance = 0.5;
			this.uncommonChance = 0.25;
			this.rareChance = 0.1;
			this.ultraChance= 0.01;
			
			this.commonRolls = 4;
			this.uncommonRolls= 3;
			this.rareRolls= 2;
			this.ultraRolls= 1;
		}

		@Override
		protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
			generatedLoot.addAll(DungeonLootTables.getRandomRoll(this));
			return generatedLoot;
		}
		
		public static class Serializer extends GlobalLootModifierSerializer<LensRandomsDungeonLootModifier> {

			@Override
			public LensRandomsDungeonLootModifier read(ResourceLocation location, JsonObject object,LootItemCondition[] ailootcondition) {
				return new LensRandomsDungeonLootModifier(ailootcondition, 
						object.get("common_chance").getAsDouble(),
						object.get("uncommon_chance").getAsDouble(),
						object.get("rare_chance").getAsDouble(),
						object.get("ultra_chance").getAsDouble(),
						object.get("common_rolls").getAsInt(),
						object.get("uncommon_rolls").getAsInt(),
						object.get("rare_rolls").getAsInt(),
						object.get("ultra_rolls").getAsInt()
						);
				}

			@Override
			public JsonObject write(LensRandomsDungeonLootModifier instance) {
				final JsonObject obj = this.makeConditions(instance.conditions);
				obj.addProperty("common_chance",instance.commonChance);
				obj.addProperty("uncommon_chance",instance.uncommonChance);
				obj.addProperty("rare_chance",instance.rareChance);
				obj.addProperty("ultra_chance",instance.ultraChance);
				
				obj.addProperty("common_rolls",instance.commonRolls);
				obj.addProperty("uncommon_rolls",instance.uncommonRolls);
				obj.addProperty("rare_rolls",instance.rareRolls);
				obj.addProperty("ultra_rolls",instance.ultraRolls);

				return obj;
			}
			
		}
		
	}
	
}
