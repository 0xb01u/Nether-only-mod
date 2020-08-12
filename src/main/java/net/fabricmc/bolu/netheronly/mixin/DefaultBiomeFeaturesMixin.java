package net.fabricmc.bolu.netheronly.mixin;

import net.fabricmc.bolu.netheronly.NetherStrongholdManager;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

	@Inject(method = "addAncientDebris", at = @At("HEAD"))
	private static void addNetherStronghold(GenerationSettings.Builder builder, CallbackInfo ci) {
		builder.structureFeature(NetherStrongholdManager.NETHER_STRONGHOLD.configure(DefaultFeatureConfig.INSTANCE));
	}
}
