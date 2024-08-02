package com.plact.dei.blocks.block_entities;

import com.plact.dei.DeiMod;
import com.plact.dei.blocks.DeiBlocks;
import com.plact.dei.blocks.block_entities.separator.SeparatorBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeiBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> DEI_BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DeiMod.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SeparatorBlockEntity>> SEPARATOR_BLOCK_ENTITY_TYPE = DEI_BLOCK_ENTITY_TYPES.register(
            "separator_block_entity",
            () -> BlockEntityType.Builder.of(SeparatorBlockEntity::new, DeiBlocks.SEPARATOR_BLOCK.get()).build(null)
    );

    public static void register(IEventBus eventBus) {
        DEI_BLOCK_ENTITY_TYPES.register(eventBus);
    }

}
