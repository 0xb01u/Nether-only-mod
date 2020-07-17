package net.fabricmc.bolu.netheronly.mixin;

import net.minecraft.block.NetherPortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/*
 * Disables Nether Portal lighting.
 */
@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin {

	/**
	 * Disable Nether portals.
	 *
	 * @author B0lu
	 * @reason Nether portal creation is completely disabled.
	 */
	/*
	 * Another @Overwrite. All checks for lighting portals should return false,
	 * regardless of what the player is trying to do.
	 */
	@Overwrite
	public static boolean createPortalAt(WorldAccess worldAccess, BlockPos blockpos) {
		return false;
	}
}
