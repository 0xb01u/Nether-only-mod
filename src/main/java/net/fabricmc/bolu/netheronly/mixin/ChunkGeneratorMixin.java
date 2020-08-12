package net.fabricmc.bolu.netheronly.mixin;

import net.fabricmc.bolu.netheronly.NetherStrongholdFeature;
import net.fabricmc.bolu.netheronly.NetherStrongholdManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
 * Causes the location/tracking of a NetherStronghold to behave similar to that
 * of a regular Stronghold.
 */
@Mixin(ChunkGenerator.class)
public abstract class ChunkGeneratorMixin {

	/*
	 * CONDITIONALLY MODIFYING THE RETURN VALUE OF A METHOD
	 */

	/*
	 * This injected code is similar to the one used to locate Strongholds.
	 */
	@Inject(method = "locateStructure", at = @At("HEAD"), cancellable = true)
	public void locateStructure(ServerWorld world, StructureFeature<?> feature, BlockPos center, int radius,
	                                boolean skipExistingChunks, CallbackInfoReturnable<BlockPos> cir) {
		if (feature == NetherStrongholdManager.NETHER_STRONGHOLD) {
			BlockPos blockPos = null;
			double d = Double.MAX_VALUE;
			BlockPos.Mutable mutable = new BlockPos.Mutable();

			for (ChunkPos chunkPos : NetherStrongholdFeature.getNetherStrongholdPosList()) {
				mutable.set((chunkPos.x << 4) + 8, 32, (chunkPos.z << 4) + 8);
				double e = mutable.getSquaredDistance(center);
				if (e < d || blockPos == null) {
					blockPos = new BlockPos(mutable);
					d = e;
				}
			}

			cir.setReturnValue(blockPos);
		}
	}
}
