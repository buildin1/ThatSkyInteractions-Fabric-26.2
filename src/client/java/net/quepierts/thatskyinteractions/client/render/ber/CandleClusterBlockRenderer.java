package net.quepierts.thatskyinteractions.client.render.ber;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.quepierts.thatskyinteractions.client.render.ber.state.CandleClusterRenderState;
import net.quepierts.thatskyinteractions.common.block.CandleType;
import net.quepierts.thatskyinteractions.common.block.entity.CandleClusterBlockEntity;
import net.quepierts.thatskyinteractions.common.registry.Items;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class CandleClusterBlockRenderer implements BlockEntityRenderer<CandleClusterBlockEntity, CandleClusterRenderState> {
    private final ItemModelResolver itemModelResolver;

    public CandleClusterBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public CandleClusterRenderState createRenderState() {
        return new CandleClusterRenderState();
    }

    @Override
    public void extractRenderState(
            CandleClusterBlockEntity entity,
            CandleClusterRenderState state,
            float partialTicks,
            Vec3 cameraPos,
            @Nullable CrumblingOverlay breakingProgress
    ) {
        BlockEntityRenderState.extractBase(entity, state, breakingProgress);
        state.candles.clear();
        state.candles.addAll(entity.getCandles());
        state.onSlab = entity.isOnSlab();
    }

    @Override
    public void submit(
            CandleClusterRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState camera
    ) {
        ShortArrayList candles = state.candles;
        if (candles.isEmpty()) {
            return;
        }

        poseStack.translate(0.0, state.onSlab ? -0.5 : 0.0, 0.0);

        for (int i = 0; i < candles.size(); i++) {
            short candle = candles.getShort(i);
            CandleType type = CandleClusterBlockEntity.getCandleType(candle);
            float half = type.getSize() / 2.0f;
            float x = (CandleClusterBlockEntity.getCandleX(candle) + half) / 16.0f;
            float z = (CandleClusterBlockEntity.getCandleZ(candle) + half) / 16.0f;
            int rotation = CandleClusterBlockEntity.getCandleRotation(candle);

            poseStack.pushPose();
            poseStack.translate(x, 0.0f, z);

            if (rotation != 0) {
                poseStack.mulPose(Axis.YP.rotationDegrees(rotation * CandleClusterBlockEntity.UNIT_ROTATION_DEG));
            }

            poseStack.translate(-0.5f, 0.0f, -0.5f);

            ItemStackRenderState itemState = new ItemStackRenderState();
            this.itemModelResolver.updateForTopItem(
                    itemState,
                    new ItemStack(Items.CANDLES[type.ordinal()]),
                    ItemDisplayContext.NONE,
                    null,
                    null,
                    0
            );
            itemState.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

            poseStack.popPose();
        }
    }
}
