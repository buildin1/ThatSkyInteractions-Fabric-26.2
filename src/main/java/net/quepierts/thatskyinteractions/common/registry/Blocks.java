package net.quepierts.thatskyinteractions.common.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.quepierts.thatskyinteractions.common.ResourceLocations;
import net.quepierts.thatskyinteractions.common.block.*;

public class Blocks {
    public static final Block WING_OF_LIGHT = register("wing_of_light",
            new WingOfLightBlock(blockProps("wing_of_light")
                    .strength(-1.0F, 3600000.8F)
                    .mapColor(MapColor.COLOR_YELLOW)
                    .noLootTable()
                    .noCollision()
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .noTerrainParticles()
                    .pushReaction(PushReaction.BLOCK)
                    .lightLevel(blockState -> 15)));

    public static final Block CLOUD = register("cloud",
            new CloudBlock(blockProps("cloud")
                    .strength(-1.0F, 3600000.8F)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .noLootTable()
                    .noCollision()
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .noTerrainParticles()
                    .pushReaction(PushReaction.BLOCK)));

    public static final Block COLORED_CLOUD = register("colored_cloud",
            new ColoredCloudBlock(blockProps("colored_cloud")
                    .strength(-1.0F, 3600000.8F)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .noLootTable()
                    .noCollision()
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .noTerrainParticles()
                    .pushReaction(PushReaction.BLOCK)));

    public static final Block MURAL = register("mural",
            new MuralBlock(blockProps("mural")
                    .strength(-1.0F, 3600000.8F)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .noLootTable()
                    .noCollision()
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .noTerrainParticles()
                    .pushReaction(PushReaction.BLOCK)));

    public static final Block CANDLE_CLUSTER = register("candle_cluster",
            new CandleClusterBlock(blockProps("candle_cluster")
                    .mapColor(MapColor.COLOR_RED)
                    .noOcclusion()
                    .strength(0.1F)
                    .sound(SoundType.CANDLE)
                    .pushReaction(PushReaction.BLOCK)
                    .lightLevel(blockState -> CandleClusterBlock.LIGHT_EMISSION)
                    .isRedstoneConductor((s, g, p) -> false)
                    .isSuffocating((s, g, p) -> false)
                    .isViewBlocking((s, g, p) -> false)));

    public static final Block HUGE_CANDLE_CLUSTER = register("huge_candle_cluster",
            new HugeCandleClusterBlock(blockProps("huge_candle_cluster")
                    .mapColor(MapColor.COLOR_RED)
                    .noOcclusion()
                    .strength(0.1F)
                    .sound(SoundType.CANDLE)
                    .pushReaction(PushReaction.BLOCK)
                    .lightLevel(blockState -> HugeCandleClusterBlock.LIGHT_EMISSION)
                    .isRedstoneConductor((s, g, p) -> false)
                    .isSuffocating((s, g, p) -> false)
                    .isViewBlocking((s, g, p) -> false)));

    private static Block register(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocations.of(name), block);
    }

    private static BlockBehaviour.Properties blockProps(String name) {
        return BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, ResourceLocations.of(name)));
    }

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    public static void init() {
        // Trigger static initialization
    }
}
