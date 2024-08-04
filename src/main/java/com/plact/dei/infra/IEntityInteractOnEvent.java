package com.plact.dei.infra;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IEntityInteractOnEvent {
        boolean EntityInteractOnEvent(Player player, ItemStack itemStack, Entity target, InteractionHand hand);
}
