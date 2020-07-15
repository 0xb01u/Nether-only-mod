package net.fabricmc.bolu.netheronly.mixin;


import org.objectweb.asm.Opcodes;
import net.minecraft.server.PlayerManager;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
	@Redirect(method = "onPlayerConnect", at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/World;OVERWORLD:Lnet/minecraft/util/registry/RegistryKey;",
			opcode = Opcodes.GETSTATIC
	))
	private RegistryKey<World> hellizeOverworld() {
		return World.NETHER;
	}
}
