package com.plact.dei.items;

import com.plact.dei.DeiMod;
import com.plact.dei.utils.DeiTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class DivineSyringeItem extends Item {
    public DivineSyringeItem(Item.Properties props) {
        super(DivineSyringeItem.ItemProperties());

    }

    private static Item.Properties ItemProperties() {
        return new Item.Properties()
                .stacksTo(16);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, LivingEntity target, @NotNull InteractionHand hand) {
        if (target.getType().getTags().anyMatch(e -> e == DeiTags.GOD_TAG) && !player.getCooldowns().isOnCooldown(this)) {
            player.getCooldowns().addCooldown(this, 40);
            if (!player.level().isClientSide()) {
                target.hurt(
                        new DamageSource(
                                player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DeiMod.SYRINGE_DAMAGE),
                                player),
                        5
                );
                player.addItem(new ItemStack(DeiItems.ICHOR_SYRINGE_ITEM.asItem()));
                stack.consume(1, player);
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide());
        }
        return InteractionResult.PASS;
    }
}
