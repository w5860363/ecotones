package supercoder79.ecotones.compat;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import supercoder79.ecotones.api.BiomeRegistries;
import supercoder79.ecotones.api.Climate;
import supercoder79.ecotones.world.biome.BiomeUtil;

public class TerrestriaCompat {
    public static void init() {
        // cypress forest
        Biome cypressForest = Registry.BIOME.get(new Identifier("terrestria:cypress_forest"));
        Climate.WARM_MILD.add(cypressForest, 0.2);

        // cypress swamp
        Biome cypressSwamp = Registry.BIOME.get(new Identifier("terrestria:cypress_swamp"));
        Climate.WARM_HUMID.add(cypressSwamp, 0.2);

        // hemlock rainforest
        Biome hemlockRainforest = Registry.BIOME.get(new Identifier("terrestria:hemlock_rainforest"));
        Climate.WARM_VERY_HUMID.add(hemlockRainforest, 0.2);
        BiomeRegistries.registerBiomeVariantChance(hemlockRainforest, 2);
        BiomeRegistries.registerBiomeVariants(hemlockRainforest, hemlockRainforest, Registry.BIOME.get(new Identifier("terrestria:hemlock_clearing")));

        // japanese maple forest
        Biome japaneseMapleForest = Registry.BIOME.get(new Identifier("terrestria:japanese_maple_forest"));
        Climate.WARM_HUMID.add(japaneseMapleForest, 0.2);
        BiomeRegistries.registerBiomeVariantChance(japaneseMapleForest, 2);
        BiomeRegistries.registerBiomeVariants(japaneseMapleForest, japaneseMapleForest, Registry.BIOME.get(new Identifier("terrestria:wooded_japanese_maple_hills")));

        // lush redwood forest
        Biome lushRedwoodForest = Registry.BIOME.get(new Identifier("terrestria:lush_redwood_forest"));
        Climate.WARM_HUMID.add(lushRedwoodForest, 0.2);
        BiomeRegistries.registerBiomeVariantChance(lushRedwoodForest, 2);
        BiomeRegistries.registerBiomeVariants(lushRedwoodForest, lushRedwoodForest, Registry.BIOME.get(new Identifier("terrestria:lush_redwood_clearing")));

        // rainbow rainforest
        Biome rainbowRainforest = Registry.BIOME.get(new Identifier("terrestria:rainbow_rainforest"));
        Climate.WARM_RAINFOREST.add(rainbowRainforest, 0.2);
        BiomeRegistries.registerBiomeVariantChance(rainbowRainforest, 3);
        BiomeRegistries.registerBiomeVariants(rainbowRainforest, Registry.BIOME.get(new Identifier("terrestria:rainbow_rainforest_mountains")), Registry.BIOME.get(new Identifier("terrestria:rainbow_rainforest_lake")));

        // redwood forest
        Biome redwoodForest = Registry.BIOME.get(new Identifier("terrestria:redwood_forest"));
        Climate.WARM_VERY_HUMID.add(redwoodForest, 0.2);
        BiomeRegistries.registerBiomeVariantChance(redwoodForest, 2);
        BiomeRegistries.registerBiomeVariants(redwoodForest, redwoodForest, Registry.BIOME.get(new Identifier("terrestria:redwood_clearing")));

        // sakura forest
        Biome sakuraForest = Registry.BIOME.get(new Identifier("terrestria:sakura_forest"));
        Climate.WARM_MILD.add(sakuraForest, 0.2);
        BiomeRegistries.registerBiomeVariantChance(sakuraForest, 2);
        BiomeRegistries.registerBiomeVariants(sakuraForest, sakuraForest, Registry.BIOME.get(new Identifier("terrestria:wooded_sakura_hills")));

    }
}
