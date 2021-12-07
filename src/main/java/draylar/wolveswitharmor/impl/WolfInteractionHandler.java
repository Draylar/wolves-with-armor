package draylar.wolveswitharmor.impl;

import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.item.WolfArmorItem;
import draylar.wolveswitharmor.registry.WWASounds;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WolfInteractionHandler implements UseEntityCallback {

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if(entity instanceof WolfEntity wolf) {
            if(wolf.getOwnerUuid() != null) {
                if(wolf.getOwnerUuid().equals(player.getUuid())) {
                    WolfDataAccessor data = WolvesWithArmor.getData(wolf);

                    // wolf has no armor, player has armor
                    if(data.getWolfArmor().isEmpty() && player.getMainHandStack().getItem() instanceof WolfArmorItem) {
                        if(!world.isClient) {
                            data.setWolfArmor(player.getMainHandStack());

                            // play SFX for equipping wolf with armor
                            wolf.playSound(WWASounds.WOLF_ARMOR_EQUIP, 0.5F, 1.0F);

                            // increase item use statistic - #23
                            player.incrementStat(Stats.USED.getOrCreateStat(player.getMainHandStack().getItem()));

                            // If the player is not in creative, clear their active slot.
                            if(!player.getAbilities().creativeMode) {
                                player.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }

                        return ActionResult.SUCCESS;
                    }

                    // wolf has armor, player has empty hand, player sneaking
                    else if(!data.getWolfArmor().isEmpty() && player.getMainHandStack().isEmpty() && player.isSneaking()) {
                        if(!world.isClient) {
                            ItemStack clonedArmor = data.getWolfArmor().copy();
                            data.setWolfArmor(ItemStack.EMPTY);
                            player.setStackInHand(Hand.MAIN_HAND, clonedArmor);
                        }

                        return ActionResult.SUCCESS;
                    }
                }
            }
        }

        return ActionResult.PASS;
    }
}
