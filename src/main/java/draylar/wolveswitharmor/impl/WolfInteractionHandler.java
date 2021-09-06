package draylar.wolveswitharmor.impl;

import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.cca.WolfArmorComponent;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WolfInteractionHandler implements UseEntityCallback {

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (entity instanceof WolfEntity wolf) {
            if (wolf.getOwnerUuid() != null) {
                if (wolf.getOwnerUuid().equals(player.getUuid())) {
                    WolfArmorComponent wolfComponent = WolvesWithArmor.WOLF_ARMOR.get(wolf);

                    // wolf has no armor, player has armor
                    if (wolfComponent.getArmor().isEmpty() && player.getMainHandStack().getItem() instanceof WolfArmorItem) {
                        if(!world.isClient) {
                            wolfComponent.setArmor(player.getMainHandStack());

                            // play SFX for equipping wolf with armor
                            wolf.playSound(SoundEvents.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);

                            // If the player is not in creative, clear their active slot.
                            if(!player.getAbilities().creativeMode) {
                                player.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }

                        return ActionResult.SUCCESS;
                    }

                    // wolf has armor, player has empty hand, player sneaking
                    else if (!wolfComponent.getArmor().isEmpty() && player.getMainHandStack().isEmpty() && player.isSneaking()) {
                        if(!world.isClient) {
                            ItemStack clonedArmor = wolfComponent.getArmor().copy();
                            wolfComponent.setArmor(ItemStack.EMPTY);
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
