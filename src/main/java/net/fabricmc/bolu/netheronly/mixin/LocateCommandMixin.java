package net.fabricmc.bolu.netheronly.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.bolu.netheronly.NetherStrongholdManager;
import net.minecraft.server.command.LocateCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.server.command.CommandManager.literal;

/*
 * Adds the /locate command capability to find Nether-Strongholds.
 *
 * Copied from the tutorial on Fabric Wiki.
 */
@Mixin(LocateCommand.class)
public abstract class LocateCommandMixin {
	/* When shadowing a static method, as it can't be abstract-ed, it is just
	   defined as returning a placeholder value of the corresponding type.
	   The exception is detected as 'never thrown' because of that. */
	@Shadow
	private static int execute(ServerCommandSource source, StructureFeature<?> structureFeature)
			throws CommandSyntaxException {
		return 0;
	}

	@Inject(method = "register", at = @At("RETURN"))
	private static void registerNetherStronghold(CommandDispatcher<ServerCommandSource> dispatcher, CallbackInfo info) {
		dispatcher.register(literal("locate").requires(source -> source.hasPermissionLevel(2))
				.then(literal("nether_stronghold")
						.executes(ctx -> execute(ctx.getSource(), NetherStrongholdManager.NETHER_STRONGHOLD))));
	}
}
