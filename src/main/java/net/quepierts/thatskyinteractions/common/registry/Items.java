package net.quepierts.thatskyinteractions.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.quepierts.thatskyinteractions.common.ResourceLocations;
import net.quepierts.thatskyinteractions.common.block.CandleType;
import net.quepierts.thatskyinteractions.common.item.*;

@SuppressWarnings("unused")
public class Items {
    public static final Item WING_OF_LIGHT = register("wing_of_light", new WingOfLightItem());
    public static final Item SIMPLE_CLOUD = register("simple_cloud", new CloudItem(Blocks.CLOUD));
    public static final Item COLORED_CLOUD = register("colored_cloud", new CloudItem(Blocks.COLORED_CLOUD));
    public static final Item CLOUD_EXPAND = register("cloud_expand", new CloudExpandItem());
    public static final Item CLOUD_REDUCE = register("cloud_reduce", new CloudReduceItem());
    public static final Item CLOUD_EDITOR = register("cloud_editor", new CloudEditorItem());
    public static final Item MURAL = register("mural", new MuralItem(false));
    public static final Item MURAL_BLOOMING = register("mural_blooming", new MuralItem(true));

    @SuppressWarnings("unchecked")
    public static final Item[] CANDLES = new Item[CandleType.values().length];

    static {
        CandleType[] values = CandleType.values();
        for (int i = 0; i < values.length; i++) {
            CandleType type = values[i];
            CANDLES[i] = register("candle_cluster_" + type.getSerializedName(),
                    new CandleClusterItem(Blocks.CANDLE_CLUSTER, type));
        }
    }

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocations.of(name), item);
    }

    public static void init() {
        // Trigger static initialization
    }
}
