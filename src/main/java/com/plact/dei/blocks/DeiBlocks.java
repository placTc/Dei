package com.plact.dei.blocks;

import com.plact.dei.DeiMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeiBlocks {
    public static final DeferredRegister.Blocks DEI_BLOCKS = DeferredRegister.createBlocks(DeiMod.MODID);

    public static void register(IEventBus modEventBus) {
        DEI_BLOCKS.register(modEventBus);
    }
}
