package com.plact.dei.items;

import com.plact.dei.DeiMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class DivineSyringeItem extends Item {
    public DivineSyringeItem(Item.Properties props) {
        super(DivineSyringeItem.ItemProperties());

    }

    private static Item.Properties ItemProperties() {
        return new Item.Properties()
                .stacksTo(16);
    }

    public void EntityInteraction(Player player, ItemStack itemStack, Entity target, Level level, InteractionHand hand) {
        if (target.getType().getTags().anyMatch(e -> e == DeiMod.GOD_TAG) && !player.getCooldowns().isOnCooldown(this)) {
            player.getCooldowns().addCooldown(this, 40);
            if (!level.isClientSide()) {
                target.hurt(
                    new DamageSource(
                            level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DeiMod.SYRINGE_DAMAGE),
                            player),
                    5
                );
                player.addItem(new ItemStack(DeiMod.ICHOR_SYRINGE_ITEM.asItem()));
                itemStack.consume(1, player);
            }
            player.swing(hand);
        }
    }

    @EventBusSubscriber(modid=DeiMod.MODID)
    static class EntityInteractSpecificEventHandler {
        @SubscribeEvent
        public static void entityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
            if (!event.isCanceled()) {
                ItemStack itemStack = event.getItemStack();
                if (itemStack.getItem() instanceof DivineSyringeItem) {
                    ((DivineSyringeItem) itemStack.getItem()).EntityInteraction(event.getEntity(), itemStack, event.getTarget(), event.getLevel(), event.getHand());
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            }
        }
    }
}
