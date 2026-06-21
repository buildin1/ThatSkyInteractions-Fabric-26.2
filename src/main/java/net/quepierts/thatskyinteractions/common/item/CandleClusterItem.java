package net.quepierts.thatskyinteractions.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.quepierts.thatskyinteractions.common.block.CandleType;

public class CandleClusterItem extends BlockItem {
    private final CandleType type;

    public CandleClusterItem(Block block, Properties properties, CandleType type) {
        super(block, properties);
        this.type = type;
    }

    public CandleType getType() {
        return type;
    }
}
