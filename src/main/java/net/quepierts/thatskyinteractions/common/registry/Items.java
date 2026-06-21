package net.quepierts.thatskyinteractions.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.quepierts.thatskyinteractions.common.ResourceLocations;
import net.quepierts.thatskyinteractions.common.block.CandleType;
import net.quepierts.thatskyinteractions.common.item.*;

@SuppressWarnings("unused")
public class Items {
    public static final Item WING_OF_LIGHT = register("wing_of_light", new WingOfLightItem(itemProps("wing_of_light")));
    public static final Item SIMPLE_CLOUD = register("simple_cloud", new CloudItem(Blocks.CLOUD, itemProps("simple_cloud")));
    public static final Item COLORED_CLOUD = register("colored_cloud", new CloudItem(Blocks.COLORED_CLOUD, itemProps("colored_cloud")));
    public static final Item CLOUD_EXPAND = register("cloud_expand", new CloudExpandItem(itemProps("cloud_expand")));
    public static final Item CLOUD_REDUCE = register("cloud_reduce", new CloudReduceItem(itemProps("cloud_reduce")));
    public static final Item CLOUD_EDITOR = register("cloud_editor", new CloudEditorItem(itemProps("cloud_editor")));
    public static final Item MURAL = register("mural", new MuralItem(false, itemProps("mural")));
    public static final Item MURAL_BLOOMING = register("mural_blooming", new MuralItem(true, itemProps("mural_blooming")));

    @SuppressWarnings("unchecked")
    public static final Item[] CANDLES = new Item[CandleType.values().length];

    static {
        CandleType[] values = CandleType.values();
        for (int i = 0; i < values.length; i++) {
            CandleType type = values[i];
            String name = "candle_cluster_" + type.getSerializedName();
            CANDLES[i] = register(name, new CandleClusterItem(Blocks.CANDLE_CLUSTER, itemProps(name), type));
        }
    }

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocations.of(name), item);
    }

    private static Item.Properties itemProps(String name) {
        return new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocations.of(name)));
    }

    public static void init() {
        // Trigger static initialization
    }
}
