package com.plact.dei.blocks.block_entities.separator;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.plact.dei.blocks.SeparatorBlock;
import com.plact.dei.client.DeiModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SeparatorBlockEntityRenderer implements BlockEntityRenderer<SeparatorBlockEntity> {
    private final ModelPart model;
    private final BlockEntityRendererProvider.Context context;

    public SeparatorBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        context = ctx;

        model = context.bakeLayer(DeiModelLayers.SEPARATOR);
    }

    @Override
    public @NotNull AABB getRenderBoundingBox(SeparatorBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(
                (double)pos.getX(),
                (double)pos.getY(),
                (double)pos.getZ(),
                (double)(pos.getX() + 1),
                (double)(pos.getY() + 2),
                (double)(pos.getZ() + 1)
        );
    }

    @Override
    public void render(SeparatorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5, 1.501, 0.5);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
        rotateBasedOnDirectionFacing(blockEntity, poseStack);
        Material material = new Material(ResourceLocation.withDefaultNamespace("textures/atlas/blocks.png"),
                ResourceLocation.fromNamespaceAndPath("dei", "block/separator"));

        VertexConsumer vc = material.buffer(bufferSource, RenderType::entityTranslucent);
        model.render(poseStack, vc, packedLight, packedOverlay);
        poseStack.popPose();
    }

    private void rotateBasedOnDirectionFacing(SeparatorBlockEntity blockEntity, PoseStack poseStack) {
        BlockState blockState = blockEntity.getBlockState();
        switch (blockState.getValue(SeparatorBlock.FACING).getName()) {
            case "north":
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
                break;
            case "south":
                poseStack.mulPose(Axis.YN.rotationDegrees(90));
                break;
            case "east":
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
                break;
            default:
                break;
        }
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition group = partdefinition.addOrReplaceChild("group", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.001F, 8.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, -15.0F));

        PartDefinition stand = group.addOrReplaceChild("stand", CubeListBuilder.create().texOffs(42, 0).addBox(-7.0F, -8.0F, 1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-8.0F, -2.0F, 0.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 23.0F, 11.0F));

        PartDefinition spigots = group.addOrReplaceChild("spigots", CubeListBuilder.create().texOffs(20, 48).addBox(-7.0F, 2.0F, -4.001F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 47).addBox(-7.0F, 1.0F, 8.001F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 15.0F, 11.0F));

        PartDefinition spigot2 = spigots.addOrReplaceChild("spigot2", CubeListBuilder.create().texOffs(24, 39).addBox(-5.0F, -3.0F, 4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(6, 2).addBox(-2.0F, -3.0F, 12.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(6, 7).addBox(-2.0F, -3.0F, 14.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, -4.0F));

        PartDefinition spigot1 = spigots.addOrReplaceChild("spigot1", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -2.0F, 2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 7).addBox(-2.0F, -2.0F, 1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-5.0F, -2.0F, 4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, -4.0F));

        PartDefinition funnel = group.addOrReplaceChild("funnel", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 39).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 30).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(49, 51).addBox(-4.0F, -9.0F, -4.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 48).addBox(-3.0F, -9.0F, 3.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 48).addBox(3.0F, -9.0F, -4.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(48, 28).addBox(-4.0F, -9.0F, -3.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 15.0F, 15.0F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }


}
