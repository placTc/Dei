package com.plact.dei.infra;

import com.plact.dei.DeiMod;
import com.plact.dei.items.DivineSyringeItem;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = DeiMod.MODID)
public class EntityInteractSpecificSubscriber {

    @SubscribeEvent
    public static void EntityInteractEvent(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getItemStack().getItem() instanceof IEntityInteractOnEvent item) {
            if (item.EntityInteractOnEvent(event.getEntity(), event.getItemStack(), event.getTarget(), event.getHand())) {
                event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide()));
            }
        }
    }
}
