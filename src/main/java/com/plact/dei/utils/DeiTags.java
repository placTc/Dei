package com.plact.dei.utils;

import com.plact.dei.DeiMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class DeiTags {
    public static final TagKey<EntityType<?>> GOD_TAG = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(DeiMod.MODID, "gods"));

}
