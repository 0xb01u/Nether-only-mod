package net.fabricmc.bolu.netheronly.mixin;

import net.fabricmc.bolu.netheronly.NetherStrongholdManager;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnderEyeItem.class)
public class EnderEyeItemMixin {
	@ModifyArg(method = "use", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;locateStructure(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/gen/feature/StructureFeature;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;"
	))
	private StructureFeature targetNetherStronghold(StructureFeature stronghold) {
		return NetherStrongholdManager.NETHER_STRONGHOLD;
	}

	@ModifyArg(method = "use", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;locateStructure(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/gen/feature/StructureFeature;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;"
	))
	private int extendRadius(int radius) {
		return 1000; // This is going cause so much lag...
	}
}
