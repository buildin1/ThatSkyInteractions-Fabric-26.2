package net.quepierts.thatskyinteractions.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.quepierts.thatskyinteractions.common.ResourceLocations;

public class CreativeModeTabs {
    public static final CreativeModeTab TSI = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocations.of("tsi"),
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("tab.thatskyinteractions"))
                    .icon(() -> new ItemStack(Items.SIMPLE_CLOUD))
                    .displayItems((parameters, output) -> {
                        output.accept(Items.WING_OF_LIGHT);
                        output.accept(Items.SIMPLE_CLOUD);
                        output.accept(Items.COLORED_CLOUD);
                        output.accept(Items.CLOUD_EXPAND);
                        output.accept(Items.CLOUD_REDUCE);
                        output.accept(Items.CLOUD_EDITOR);
                        output.accept(Items.MURAL);
                        output.accept(Items.MURAL_BLOOMING);
                        for (Item candle : Items.CANDLES) {
                            output.accept(candle);
                        }
                    })
                    .build()
    );

    public static void init() {
        // Trigger static initialization
    }
}
