package net.fabricmc.bolu.netheronly;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/*
 * Nether-Stronghold structure manager.
 *
 * Adds the Nether-Stronghold to all the Nether biomes, and to the game's
 * structure list properly.
 */
public class NetherStrongholdManager {
	/* Nether-Stronghold structure-feature constant */
	public static final StructureFeature<DefaultFeatureConfig> NETHER_STRONGHOLD
			= Registry.register(
			Registry.STRUCTURE_FEATURE,
			new Identifier("netheronly", "nether_stronghold"),
			new NetherStrongholdFeature(DefaultFeatureConfig.CODEC)
	);

	/* Empty constructor, as this is a "static" class */
	private NetherStrongholdManager() {
	}

	public static void addStronghold() {
		/* Add structure to the game's list of structures */
		StructureFeature.STRUCTURES.put("nether_stronghold", NETHER_STRONGHOLD);

		/* Add structure to biomes */
		addStrongholdToBiome(Biomes.NETHER_WASTES);
		addStrongholdToBiome(Biomes.BASALT_DELTAS);
		addStrongholdToBiome(Biomes.SOUL_SAND_VALLEY);
		addStrongholdToBiome(Biomes.CRIMSON_FOREST);
		addStrongholdToBiome(Biomes.WARPED_FOREST);
	}

	/* Adds the Nether-Stronghold structure feature to the specified biome */
	private static void addStrongholdToBiome(Biome biome) {
		biome.addStructureFeature(NETHER_STRONGHOLD.configure(DefaultFeatureConfig.INSTANCE));
	}
}
