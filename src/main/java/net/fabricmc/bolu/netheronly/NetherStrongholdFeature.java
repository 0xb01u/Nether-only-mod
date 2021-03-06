package net.fabricmc.bolu.netheronly;

import com.mojang.serialization.Codec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/*
 * This code is almost identical to the original StrongholdFeature code.
 *
 * Some logic has been altered (shouldStartAt(), and also method_28663 has been
 * overridden), but the class' skeleton is the same.
 */
public class NetherStrongholdFeature extends
		StructureFeature<DefaultFeatureConfig> {
	private static long worldSeed;
	private static final List<ChunkPos> structPos;

	static {
		structPos = new ArrayList<>(128);
	}

	public NetherStrongholdFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public StructureFeature.StructureStartFactory<DefaultFeatureConfig>
	getStructureStartFactory() {
		return NetherStrongholdFeature.Start::new;
	}

	@Override
	protected boolean shouldStartAt(ChunkGenerator chunkGenerator,
	                                BiomeSource biomeSource, long seed,
	                                ChunkRandom chunkRandom, int i, int j,
	                                Biome biome, ChunkPos chunkPos,
	                                DefaultFeatureConfig defaultFeatureConfig) {
		worldSeed = seed;
		getNetherStrongholdPosList();
		return structPos.contains(new ChunkPos(i, j));
	}

	/* Generate NetherStronghold positions */
	public static List<ChunkPos> getNetherStrongholdPosList() {
		if (structPos.isEmpty()) {
			// Default values.
			int distance = 32;
			int count = 128;
			int spread = 3;
			Random random = new Random(worldSeed);
			double angle = random.nextDouble() * 3.141592653589793D * 2.0D;
			int l = 0;
			int m = 0;

			for (int n = 0; n < count; ++n) {
				double e = (double) (4 * distance + distance * m * 6) +
						(random.nextDouble() - 0.5D) * (double) distance * 2.5D;
				int o = (int) Math.round(Math.cos(angle) * e);
				int p = (int) Math.round(Math.sin(angle) * e);

				structPos.add(new ChunkPos(o, p));
				angle += 6.283185307179586D / (double) spread;
				++l;
				if (l == spread) {
					++m;
					l = 0;
					spread += 2 * spread / (m + 1);
					spread = Math.min(spread, count - n);
					angle += random.nextDouble() * 3.141592653589793D * 2.0D;
				}
			}
		}

		return new ArrayList<>(structPos);
	}

	@Override
	public String getName() {
		return "nether_stronghold";
	}

	@Override
	public GenerationStep.Feature getGenerationStep() {
		return GenerationStep.Feature.UNDERGROUND_DECORATION;
	}

	public static class Start extends StructureStart<DefaultFeatureConfig> {
		private final long field_24559;

		public Start(StructureFeature<DefaultFeatureConfig> structureFeature,
		             int i, int j, BlockBox blockBox, int k, long l) {
			super(structureFeature, i, j, blockBox, k, l);
			this.field_24559 = l;
		}

		@Override
		public void init(DynamicRegistryManager dynamicRegistryManager,
		                 ChunkGenerator chunkGenerator,
		                 StructureManager structureManager, int i, int j,
		                 Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
			int var7 = 0;

			NetherStrongholdGenerator.Start start;
			do {
				this.children.clear();
				this.boundingBox = BlockBox.empty();
				this.random.setCarverSeed(this.field_24559 +
						(long) (var7++), i, j);
				NetherStrongholdGenerator.init();
				start = new NetherStrongholdGenerator.Start(this.random,
						(i << 4) + 2, (j << 4) + 2);
				this.children.add(start);
				start.placeJigsaw(start, this.children, this.random);
				List list = start.field_15282;

				while (!list.isEmpty()) {
					int l = this.random.nextInt(list.size());
					StructurePiece structurePiece = (StructurePiece) list.remove(l);
					structurePiece.placeJigsaw(start, this.children, this.random);
				}

				this.setBoundingBoxFromChildren();
				this.method_14978(chunkGenerator.getSeaLevel(), this.random, 10);
			} while (this.children.isEmpty() || start.field_15283 == null);
		}
	}
}
