package net.fabricmc.bolu.netheronly.mixin;

import net.fabricmc.bolu.netheronly.NetherStrongholdManager;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/*
 * Causes the Ender Eye to track the Nether Stronghold instead of the (regular)
 * Stronghold.
 */
@Mixin(EnderEyeItem.class)
public class EnderEyeItemMixin {
	/*
	 * MODIFYING TWO ARGS WHEN CALLING A METHOD:
	 */

	/*
	 * First, the type of structure targeted by the eye is changed to
	 * NETHER_STRONGHOLD.
	 *
	 * Injected onto method use(), when calling locateStructure().
	 * The argument to modify is specified as the only argument in the handler
	 * (this method).
	 *  - The only StructureFeature argument is modified.
	 */
	@ModifyArg(method = "use", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;locateStructure(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/gen/feature/StructureFeature;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;"
	))
	private StructureFeature targetNetherStronghold(StructureFeature stronghold) {
		return NetherStrongholdManager.NETHER_STRONGHOLD;
	}

	/*
	 * Second, the radius to search the structure on is greatly increased
	 *  (since the regular Stronghold, which we are trying to simulate, is a
	 *  special case where the location is calculated, not looked for, so the
	 *  distance it is looked for in is practically infinite).
	 *
	 * Injected onto method use(), when calling locateStructure().
	 * The argument to modify is specified as the only argument in the handler
	 * (this method).
	 *  - The only int argument is modified.
	 */
	@ModifyArg(method = "use", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;locateStructure(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/gen/feature/StructureFeature;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;"
	))
	private int extendRadius(int radius) {
		return 1000; // This is going to cause so much lag...
	}
}
