package net.fabricmc.bolu.netheronly.mixin;


import org.objectweb.asm.Opcodes;
import net.minecraft.server.PlayerManager;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/*
 * Makes the player spawn on the Nether.
 */
@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
	/*
	 * REDIRECTING A FIELD ACCESS.
	 */

	/*
	 * Every attempt to use/access (get the value of) World.OVERWORLD
	 * in method onPlayerConnect() will be redirected to this handler (method),
	 * that returns World.NETHER, instead.
	 *
	 * As World.OVERWORLD is (public) static (final), its corresponding opcode
	 * is GETSTATIC. The handler (this method) must not be static, however.
	 */
	@Redirect(method = "onPlayerConnect", at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/World;OVERWORLD:Lnet/minecraft/util/registry/RegistryKey;",
			opcode = Opcodes.GETSTATIC
	))
	private RegistryKey<World> hellizeOverworld() {
		return World.NETHER;
	}
}
