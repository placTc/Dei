package com.plact.dei.client;

import com.google.common.collect.Sets;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class DeiModelLayers {
    private static final String DEFAULT_LAYER = "main";
    public static final Set<ModelLayerLocation> DEI_ALL_MODELS = Sets.newHashSet();
    public static final ModelLayerLocation SEPARATOR = register("separator");

    private static ModelLayerLocation register(String path) {
        return register(path, DEFAULT_LAYER);
    }

    private static ModelLayerLocation register(String path, String model) {
        ModelLayerLocation modellayerlocation = createLocation(path, model);
        if (!DEI_ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(ResourceLocation.withDefaultNamespace(path), model);
    }

}
