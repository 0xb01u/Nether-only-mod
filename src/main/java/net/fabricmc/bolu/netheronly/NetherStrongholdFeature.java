package net.fabricmc.bolu.netheronly;

import com.mojang.serialization.Codec;

import java.util.List;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
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
	                                BiomeSource biomeSource, long l,
	                                ChunkRandom chunkRandom, int i, int j,
	                                Biome biome, ChunkPos chunkPos,
	                                DefaultFeatureConfig defaultFeatureConfig) {
		return (Math.abs(chunkPos.x) >= 50 || Math.abs(chunkPos.z) >= 50)
				&& chunkRandom.nextInt(100) < 1;
	}

	@Override
	public String getName() {
		return "nether_stronghold";
	}

	@Override
	public GenerationStep.Feature method_28663() {
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
		public void init(ChunkGenerator chunkGenerator,
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
