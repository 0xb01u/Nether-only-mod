package net.fabricmc.bolu.netheronly.mixin;

import net.minecraft.block.NetherPortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin {

	/**
	 * Disable Nether portals.
	 *
	 * @author B0lu
	 */
	@Overwrite
	public static boolean createPortalAt(WorldAccess worldAccess, BlockPos blockpos) {
		return false;
	}
}
