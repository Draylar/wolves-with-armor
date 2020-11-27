package draylar.wolveswitharmor;

import draylar.wolveswitharmor.api.WolfArmorAccessor;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandlers {

    @SubscribeEvent
    public void interactWolf(PlayerInteractEvent.EntityInteractSpecific event) {
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();

        if (event.getTarget() instanceof WolfEntity) {
            WolfEntity wolf = (WolfEntity) event.getTarget();

            if (wolf.getOwnerUUID() != null) {
                if (wolf.getOwnerUUID().equals(player.getUUID())) {
                    WolfArmorAccessor wolfComponent = WolvesWithArmor.getAccessor(wolf);

                    // wolf has no armor, player has armor
                    if (wolfComponent.getArmor().isEmpty() && player.getMainHandItem().getItem() instanceof WolfArmorItem) {
                        if(!world.isClientSide) {
                            wolfComponent.setArmor(player.getMainHandItem());
                            player.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                        }

                        event.setCanceled(true);
                        event.setCancellationResult(ActionResultType.SUCCESS);
                    }

                    // wolf has armor, player has empty hand, player sneaking
                    else if (!wolfComponent.getArmor().isEmpty() && player.getMainHandItem().isEmpty() && player.isCrouching()) {
                        if(!world.isClientSide) {
                            ItemStack clonedArmor = wolfComponent.getArmor().copy();
                            wolfComponent.setArmor(ItemStack.EMPTY);
                            player.setItemInHand(Hand.MAIN_HAND, clonedArmor);
                        }

                        event.setCanceled(true);
                        event.setCancellationResult(ActionResultType.SUCCESS);
                    }
                }
            }
        }
    }
}
