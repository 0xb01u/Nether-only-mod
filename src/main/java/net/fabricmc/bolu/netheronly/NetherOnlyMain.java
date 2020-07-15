package net.fabricmc.bolu.netheronly;

import net.fabricmc.api.ModInitializer;

public class NetherOnlyMain implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Nether only loaded.");

		NetherStrongholdManager.addStronghold();
	}
}
