package net.quepierts.thatskyinteractions.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.quepierts.thatskyinteractions.common.ResourceLocations;
import net.quepierts.thatskyinteractions.common.block.entity.*;

public class BlockEntities {
    public static final BlockEntityType<WingOfLightBlockEntity> WING_OF_LIGHT = register("wing_of_light_entity",
            FabricBlockEntityTypeBuilder.create(WingOfLightBlockEntity::new, Blocks.WING_OF_LIGHT).build());

    public static final BlockEntityType<SimpleCloudBlockEntity> SIMPLE_CLOUD = register("simple_cloud",
            FabricBlockEntityTypeBuilder.create(SimpleCloudBlockEntity::new, Blocks.CLOUD).build());

    public static final BlockEntityType<ColoredCloudBlockEntity> COLORED_CLOUD = register("colored_cloud",
            FabricBlockEntityTypeBuilder.create(ColoredCloudBlockEntity::new, Blocks.COLORED_CLOUD).build());

    public static final BlockEntityType<MuralBlockEntity> MURAL = register("mural",
            FabricBlockEntityTypeBuilder.create(MuralBlockEntity::new, Blocks.MURAL).build());

    public static final BlockEntityType<CandleClusterBlockEntity> CANDLE_CLUSTER = register("candle_cluster",
            FabricBlockEntityTypeBuilder.create(CandleClusterBlockEntity::new, Blocks.CANDLE_CLUSTER).build());

    private static <T extends net.minecraft.world.level.block.entity.BlockEntity> BlockEntityType<T> register(
            String name, BlockEntityType<T> type) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocations.of(name), type);
    }

    public static void init() {
        // Trigger static initialization
    }
}
