package net.quepierts.thatskyinteractions.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.quepierts.thatskyinteractions.client.render.ber.CandleClusterBlockRenderer;
import net.quepierts.thatskyinteractions.common.registry.BlockEntities;

@Environment(EnvType.CLIENT)
public class ThatSkyInteractionsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(BlockEntities.CANDLE_CLUSTER, CandleClusterBlockRenderer::new);
    }
}
