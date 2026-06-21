package net.quepierts.thatskyinteractions.client.render.ber.state;

import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

@Environment(EnvType.CLIENT)
public class CandleClusterRenderState extends BlockEntityRenderState {
    public final ShortArrayList candles = new ShortArrayList();
    public boolean onSlab;
}
