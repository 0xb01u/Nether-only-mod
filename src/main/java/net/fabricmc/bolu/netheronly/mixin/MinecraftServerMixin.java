package net.fabricmc.bolu.netheronly.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

/*
 * Changes all getOverworld() method calls to return the Nether instead.
 */
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Shadow
	@Final
	private Map<RegistryKey<World>, ServerWorld> worlds;

	/**
	 * Change Overworld to Nether.
	 *
	 * @author B0lu
	 * @reason The original method is just one line that must be invalidated.
	 */
	/*
	 * @Overwrites are supposed to be bad... But what are you supposed to do
	 * when you just need to completely modify a single-line method??
	 */
	@Overwrite
	public final ServerWorld getOverworld() {
		return this.worlds.get(World.NETHER);
	}
}
