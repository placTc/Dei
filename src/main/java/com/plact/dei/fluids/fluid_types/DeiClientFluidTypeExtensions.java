package com.plact.dei.fluids.fluid_types;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class DeiClientFluidTypeExtensions implements IClientFluidTypeExtensions {
    private final int tintColor;
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final ResourceLocation overlayTexture;
    private final Vector3f fogColor;

    DeiClientFluidTypeExtensions(ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlayTexture, int tintColor, Vector3f fogColor) {
        this.tintColor = tintColor;
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.overlayTexture = overlayTexture;
        this.fogColor = fogColor;
    }

    @Override
    public int getTintColor() {
        return tintColor;
    }

    @Override
    public @NotNull ResourceLocation getStillTexture() {
        return stillTexture;
    }

    @Override
    public @NotNull ResourceLocation getFlowingTexture() {
        return flowingTexture;
    }

    @Override
    public @NotNull ResourceLocation getRenderOverlayTexture(Minecraft mc) { return overlayTexture; }

    @Override
    public void modifyFogRender(Camera camera, FogRenderer.FogMode mode,
                                float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
        RenderSystem.setShaderFogStart(0.5f);
        RenderSystem.setShaderFogEnd(0.75f);
    }

    @Override
    public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
        return fogColor;
    }
}