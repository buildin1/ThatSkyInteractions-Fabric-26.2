package net.quepierts.thatskyinteractions.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleCloudBlockEntity extends BlockEntity {
    public SimpleCloudBlockEntity(BlockPos pos, BlockState state) {
        super(null, pos, state);
    }
}
