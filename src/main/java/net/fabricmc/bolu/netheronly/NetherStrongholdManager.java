package net.fabricmc.bolu.netheronly;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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
}
