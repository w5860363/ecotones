package supercoder79.ecotones.world.biome.base;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import supercoder79.ecotones.api.Climate;
import supercoder79.ecotones.api.SimpleTreeDecorationData;
import supercoder79.ecotones.api.TreeType;
import supercoder79.ecotones.client.particle.EcotonesParticles;
import supercoder79.ecotones.world.biome.BiomeUtil;
import supercoder79.ecotones.world.biome.EcotonesBiome;
import supercoder79.ecotones.world.decorator.EcotonesDecorators;
import supercoder79.ecotones.world.decorator.ShrubDecoratorConfig;
import supercoder79.ecotones.world.features.EcotonesFeatures;
import supercoder79.ecotones.world.features.config.FeatureConfigHolder;
import supercoder79.ecotones.world.features.config.SimpleTreeFeatureConfig;
import supercoder79.ecotones.world.surface.EcotonesSurfaces;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class HotBiomes {

    public static Biome DESERT_BIOME;
    public static Biome SCRUBLAND_BIOME;
    public static Biome STEPPE_BIOME;
    public static Biome TROPICAL_GRASSLAND_BIOME;
    public static Biome LUSH_SAVANNAH_BIOME;
    public static Biome DRY_FOREST_BIOME;
    public static Biome LUSH_FOREST_BIOME;
    public static Biome TROPICAL_RAINFOREST_BIOME;

    private static EcotonesBiome.Template template = new EcotonesBiome.Template(EcotonesBiome.builder()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.SAND_CONFIG)
            .precipitation(Biome.Precipitation.NONE).category(Biome.Category.FOREST)
            .depth(0.5F)
            .scale(0.075F)
            .temperature(2F)
            .downfall(0F)
            .waterColor(4159204)
            .waterFogColor(329011)
            .addDefaultFeatures(LAND_CARVERS, STRUCTURES, DUNGEONS, MINEABLES, ORES, DISKS,
                    DEFAULT_FLOWERS, DEFAULT_MUSHROOMS, FOREST_GRASS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
            .addStructureFeature(DefaultBiomeFeatures.NORMAL_MINESHAFT)
            .addStructureFeature(DefaultBiomeFeatures.STRONGHOLD)
            .addStructureFeature(DefaultBiomeFeatures.STANDARD_RUINED_PORTAL)

            .addCustomFeature(GenerationStep.Feature.RAW_GENERATION,
                    EcotonesFeatures.DRAINAGE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(EcotonesDecorators.DRAINAGE_DECORATOR.configure(DecoratorConfig.DEFAULT)))

            .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                    Feature.RANDOM_PATCH.configure(FeatureConfigHolder.SURFACE_ROCKS).createDecoratedFeature(EcotonesDecorators.ROCKINESS.configure(DecoratorConfig.DEFAULT)))

            .addTreeFeature(Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.DEAD_BUSH_CONFIG), 2)

            .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                    Feature.RANDOM_PATCH.configure(FeatureConfigHolder.DESERT_GRASS_CONFIG).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_32.configure(new CountDecoratorConfig(2))))

            .addDefaultSpawnEntries());

    public static void init() {
        DESERT_BIOME = BiomeUtil.register(new Identifier("ecotones", "desert"), template.builder()
                .hilliness(1.6)
                .particleConfig(new BiomeParticleConfig(EcotonesParticles.SAND, 0.00325F))
                .addStructureFeature(DefaultBiomeFeatures.DESERT_PYRAMID)
                .addStructureFeature(DefaultBiomeFeatures.PILLAGER_OUTPOST)
                .addStructureFeature(StructureFeature.VILLAGE.configure(new StructurePoolFeatureConfig(new Identifier("village/desert/town_centers"), 4)))
                .addTreeFeature(Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.DEAD_BUSH_CONFIG), 3)
                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.CACTUS_CONFIG).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(10)))));
        SCRUBLAND_BIOME = BiomeUtil.register(new Identifier("ecotones", "scrubland"), template.builder()
                .configureSurfaceBuilder(EcotonesSurfaces.DESERT_SCRUB_BUILDER, SurfaceBuilder.GRASS_CONFIG)
                .particleConfig(new BiomeParticleConfig(EcotonesParticles.SAND, 0.00125F))
                .temperature(1.9F)
                .downfall(0.2F)
                .hilliness(1.6)
                .addDefaultFeature(PLAINS_TALL_GRASS)
                .addStructureFeature(DefaultBiomeFeatures.PILLAGER_OUTPOST)
                .addStructureFeature(StructureFeature.VILLAGE.configure(new StructurePoolFeatureConfig(new Identifier("village/desert/town_centers"), 5)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.CACTUS_CONFIG)
                                .createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(40))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.DESERTIFY_SOIL.configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(3, 0.5f, 2))))

                .addCustomFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS,
                        Feature.FOREST_ROCK.configure(new ForestRockFeatureConfig(Blocks.COBBLESTONE.getDefaultState(), 0))
                                .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(12))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(5))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.CACTI.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(2))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(FeatureConfigHolder.SCRUBLAND_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 5, 10))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(FeatureConfigHolder.DESERT_GRASS_CONFIG).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_32.configure(new CountDecoratorConfig(3))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BIG_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.ABOVE_QUALITY.configure(DecoratorConfig.DEFAULT)))

                .addTreeFeature(Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.DEAD_BUSH_CONFIG), 3));
        STEPPE_BIOME = BiomeUtil.register( new Identifier("ecotones", "steppe"), template.builder()
                .temperature(1.8F)
                .downfall(0.3F)
                .hilliness(2.8)
                .volatility(0.88)
                .addDefaultFeature(PLAINS_TALL_GRASS)
                .addStructureFeature(DefaultBiomeFeatures.PILLAGER_OUTPOST)
                .addStructureFeature(StructureFeature.VILLAGE.configure(new StructurePoolFeatureConfig(new Identifier("village/savanna/town_centers"), 4)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.DESERTIFY_SOIL.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(7, 0.5f, 3))))

                .addCustomFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS,
                        Feature.FOREST_ROCK.configure(new ForestRockFeatureConfig(Blocks.COBBLESTONE.getDefaultState(), 0))
                                .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(13))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(4))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BIG_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.ABOVE_QUALITY.configure(DecoratorConfig.DEFAULT)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.WIDE_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(0.05))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 5, 10))))

                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG));
        TROPICAL_GRASSLAND_BIOME = BiomeUtil.register(new Identifier("ecotones", "tropical_grassland"), template.builder()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
                .temperature(1.7F)
                .downfall(0.4F)
                .scale(0.12f)
                .hilliness(1.8)

                .addStructureFeature(DefaultBiomeFeatures.PILLAGER_OUTPOST)
                .addStructureFeature(StructureFeature.VILLAGE.configure(new StructurePoolFeatureConfig(new Identifier("village/savanna/town_centers"), 8)))

                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)

                .addCustomFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS,
                        Feature.FOREST_ROCK.configure(new ForestRockFeatureConfig(Blocks.COBBLESTONE.getDefaultState(), 0))
                                .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(14))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.DESERTIFY_SOIL.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.WIDE_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(0.25))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(2))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BIG_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.ABOVE_QUALITY.configure(DecoratorConfig.DEFAULT)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SMALL_ACACIA.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(EcotonesDecorators.SIMPLE_TREE_DECORATOR.configure(new SimpleTreeDecorationData(1.25))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(FeatureConfigHolder.MOSTLY_SHORT_GRASS_CONFIG)
                                .createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 12, 12)))));
        LUSH_SAVANNAH_BIOME = BiomeUtil.register( new Identifier("ecotones", "lush_savannah"), template.builder()
                .temperature(1.6F)
                .downfall(0.5F)
                .scale(0.15f)
                .hilliness(1.4)
                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
                .addStructureFeature(DefaultBiomeFeatures.PILLAGER_OUTPOST)
                .addStructureFeature(StructureFeature.VILLAGE.configure(new StructurePoolFeatureConfig(new Identifier("village/savanna/town_centers"), 12)))

                .addCustomFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS,
                        Feature.FOREST_ROCK.configure(new ForestRockFeatureConfig(Blocks.COBBLESTONE.getDefaultState(), 0))
                                .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(14))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.DESERTIFY_SOIL.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(3))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG)
                                .createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 12, 20))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.WIDE_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(0.45))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BRANCHING_ACACIA.configure(TreeType.ACACIA.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.ACACIA.config.decorationData)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BRANCHING_OAK.configure(TreeType.RARE_LARGE_OAK.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.RARE_LARGE_OAK.config.decorationData)))
        );

        DRY_FOREST_BIOME = BiomeUtil.register(new Identifier("ecotones", "dry_forest"), template.builder()
                .temperature(1.6F)
                .downfall(0.6F)
                .scale(0.3f)
                .hilliness(2.1)
                .volatility(0.98)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(3))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 12, 20))))


                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BRANCHING_ACACIA.configure(TreeType.ACACIA.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.ACACIA.config.decorationData)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BRANCHING_OAK.configure(TreeType.DRY_OAK.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.DRY_OAK.config.decorationData)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.IMPROVED_BIRCH.configure(TreeType.DRY_BIRCH.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.DRY_BIRCH.config.decorationData)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.WIDE_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(0.65))))
        );

        LUSH_FOREST_BIOME = BiomeUtil.register(new Identifier("ecotones", "lush_forest"), template.builder()
                .temperature(1.6F)
                .downfall(0.8F)
                .scale(0.4f)
                .hilliness(2.3)
                .volatility(0.94)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(4))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG)
                                .createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 16, 20))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.WIDE_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(0.65))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BRANCHING_OAK.configure(TreeType.LUSH_OAK.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.LUSH_OAK.config.decorationData)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.IMPROVED_BIRCH.configure(TreeType.LUSH_BIRCH.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.LUSH_BIRCH.config.decorationData)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BRANCHING_OAK.configure(TreeType.LUSH_JUNGLE.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.LUSH_JUNGLE.config.decorationData))));

        TROPICAL_RAINFOREST_BIOME = BiomeUtil.register( new Identifier("ecotones", "tropical_rainforest"), template.builder()
                .temperature(1.6F)
                .downfall(1F)
                .scale(0.4f)
                .hilliness(3.4)
                .volatility(0.88)
                .addStructureFeature(DefaultBiomeFeatures.JUNGLE_PYRAMID)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(2))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.JUNGLE_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(4))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG)
                                .createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 20, 20))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.WIDE_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(EcotonesDecorators.SHRUB_PLACEMENT_DECORATOR.configure(new ShrubDecoratorConfig(1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BRANCHING_OAK.configure(TreeType.DRY_OAK.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.DRY_OAK.config.decorationData)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BRANCHING_OAK.configure(TreeType.LUSH_JUNGLE.config)
                                .createDecoratedFeature(EcotonesDecorators.TREE_DECORATOR.configure(TreeType.LUSH_JUNGLE.config.decorationData)))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.JUNGLE_PALM_TREE.configure(DefaultBiomeFeatures.JUNGLE_TREE_CONFIG)
                                .createDecoratedFeature(EcotonesDecorators.SIMPLE_TREE_DECORATOR.configure(new SimpleTreeDecorationData(3.25, false))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BANANA_TREE.configure(DefaultBiomeFeatures.JUNGLE_TREE_CONFIG)
                                .createDecoratedFeature(EcotonesDecorators.SIMPLE_TREE_DECORATOR.configure(new SimpleTreeDecorationData(2.25, false))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.TREE.configure(DefaultBiomeFeatures.MEGA_JUNGLE_TREE_CONFIG)
                                .createDecoratedFeature(EcotonesDecorators.SIMPLE_TREE_DECORATOR.configure(new SimpleTreeDecorationData(1.25, false)))));

        Climate.HOT_DESERT.add(DESERT_BIOME, 1);
        Climate.HOT_VERY_DRY.add(SCRUBLAND_BIOME, 1);
        Climate.HOT_DRY.add(STEPPE_BIOME, 1);
        Climate.HOT_MODERATE.add(TROPICAL_GRASSLAND_BIOME, 1);
        Climate.HOT_MILD.add(LUSH_SAVANNAH_BIOME, 1);
        Climate.HOT_HUMID.add(DRY_FOREST_BIOME, 1);
        Climate.HOT_VERY_HUMID.add(LUSH_FOREST_BIOME, 1);
        Climate.HOT_RAINFOREST.add(TROPICAL_RAINFOREST_BIOME, 1);
    }
}
