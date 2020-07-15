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

@Mixin(LocateCommand.class)
public abstract class LocateCommandMixin {
	@Shadow
	private static int execute(ServerCommandSource source, StructureFeature<?> structureFeature) throws CommandSyntaxException {
		return 0;
	}

	@Inject(method = "register", at = @At("RETURN"))
	private static void registerNetherStronghold(CommandDispatcher<ServerCommandSource> dispatcher, CallbackInfo info) {
		dispatcher.register(literal("locate").requires(source -> source.hasPermissionLevel(2))
				.then(literal("nether_stronghold").executes(ctx -> execute(ctx.getSource(), NetherStrongholdManager.NETHER_STRONGHOLD))));
	}
}
