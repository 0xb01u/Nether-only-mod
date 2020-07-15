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

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Shadow @Final
	private Map<RegistryKey<World>, ServerWorld> worlds;

	/**
	 * Change Overworld to Nether.
	 *
	 * @author B0lu
	 */
	@Overwrite
	public final ServerWorld getOverworld() {
		return (ServerWorld)this.worlds.get(World.NETHER);
	}
}
