package com.plact.dei.items;

import com.plact.dei.DeiMod;
import com.plact.dei.infra.IEntityInteractOnEvent;
import com.plact.dei.utils.DeiTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.entity.PartEntity;

public class DivineSyringeItem extends Item implements IEntityInteractOnEvent {
    public DivineSyringeItem(Item.Properties props) {
        super(DivineSyringeItem.ItemProperties());

    }

    private static Item.Properties ItemProperties() {
        return new Item.Properties()
                .stacksTo(16);
    }

    public boolean EntityInteractOnEvent(Player player, ItemStack itemStack, Entity target, InteractionHand hand) {
        boolean result = false;
        if (EntityIsGod(target) && !player.getCooldowns().isOnCooldown(this)) {
            player.getCooldowns().addCooldown(this, 40);
            if (!player.level().isClientSide()) {
                target.hurt(
                        new DamageSource(
                                player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DeiMod.SYRINGE_DAMAGE),
                                player),
                        5
                );
                player.addItem(new ItemStack(DeiItems.ICHOR_SYRINGE_ITEM.asItem()));
                itemStack.consume(1, player);
            }
            result = true;
            player.swing(hand);
        }
        return result;
    }

    private boolean EntityIsGod(Entity entity) {
        EntityType<?> target = entity instanceof PartEntity<?> pe ? pe.getParent().getType() : entity.getType();
        return target.getTags().anyMatch(e -> e == DeiTags.GOD_TAG);
    }
}
