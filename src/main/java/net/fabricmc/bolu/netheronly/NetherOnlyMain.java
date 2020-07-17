package net.fabricmc.bolu.netheronly;

import net.fabricmc.api.ModInitializer;

/*
 * Main class.
 *
 * It doesn't do much.
 */
public class NetherOnlyMain implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Nether only loaded.");

		/* Add Stronghold to biomes. */
		NetherStrongholdManager.addStronghold();
	}
}
