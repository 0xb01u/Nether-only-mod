package net.fabricmc.bolu.netheronly.mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.fabricmc.bolu.netheronly.NetherStrongholdManager.NETHER_STRONGHOLD;

/*
 * UNUSED
 *
 * Left in the code as a future reference of how to inject Mixins into static
 * initializers.
 */
@Mixin(Biomes.class)
public abstract class BiomesMixin {
	/*
	 * INJECTING INTO A STATIC INTITIALIZER.
	 */

	// Suppress the warning caused by "Cannot resolve method '<clinit>' in
	// target class".
	@SuppressWarnings("UnresolvedMixinReference")
	/*
	 * clinit stands for 'CLass INITializer'.
	 * It must be the targeted method. Treated as a regular constructor.
	 */
	@Inject(method = "<clinit>", at = @At("RETURN"))
	private static void addStronghold(CallbackInfo info) {
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
