package io.github.v2lenkagamine.datagen;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.resources.ResourceLocation;

public class LensRandomsLootTableProvider extends LootTableProvider {
	public LensRandomsLootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}
	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation,Builder>>>, LootContextParamSet>> getTables() {
		return ImmutableList.of(Pair.of(LensRandomsBlockLootTables::new, LootContextParamSets.BLOCK));
	}
	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
		//Null
	}
	
}
