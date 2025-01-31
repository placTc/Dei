package com.plact.dei.items;

import com.plact.dei.DeiMod;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class IchorSyringeItem extends Item {
    public IchorSyringeItem(Properties props) {
        super(IchorSyringeItem.ItemProperties());
    }

    private static Properties ItemProperties() {
        return new Properties().stacksTo(1);
    }

    public void EntityInteractionOnEvent(Player player, ItemStack itemStack, Entity target, Level level, InteractionHand hand) {
    }
}
