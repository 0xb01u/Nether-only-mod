package net.fabricmc.bolu.netheronly;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class NetherStrongholdManager {
	public static final StructureFeature<DefaultFeatureConfig> NETHER_STRONGHOLD
			= Registry.register(
			Registry.STRUCTURE_FEATURE,
			new Identifier("netheronly", "nether_stronghold"),
			new NetherStrongholdFeature(DefaultFeatureConfig.CODEC)
	);

	private NetherStrongholdManager() {
	}

	public static void addStronghold() {
		StructureFeature.STRUCTURES.put("nether_stronghold", NETHER_STRONGHOLD);

		addStrongholdToBiome(Biomes.NETHER_WASTES);
		addStrongholdToBiome(Biomes.BASALT_DELTAS);
		addStrongholdToBiome(Biomes.SOUL_SAND_VALLEY);
		addStrongholdToBiome(Biomes.CRIMSON_FOREST);
		addStrongholdToBiome(Biomes.WARPED_FOREST);
	}

	private static void addStrongholdToBiome(Biome biome) {
		biome.addStructureFeature(NETHER_STRONGHOLD.configure(DefaultFeatureConfig.INSTANCE));
	}
}
