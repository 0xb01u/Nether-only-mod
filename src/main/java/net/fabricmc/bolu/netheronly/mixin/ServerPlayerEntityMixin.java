package net.fabricmc.bolu.netheronly.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
 * Sets the player's respawn dimension to the Nether.
 */
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
	/*
	 * INJECTING INTO A CONSTRUCTOR:
	 */
	@Shadow
	private RegistryKey<World> spawnPointDimension;

	/*
	 * The constructor method is referenced as "<init>".
	 * To ensure the object is properly created, injects to constructors can
	 * only target RETURN and TAIL.
	 */
	@Inject(method = "<init>", at = @At("RETURN"))
	private void respawnNether(CallbackInfo info) {
		this.spawnPointDimension = World.NETHER;
	}
}
