package draylar.wolveswitharmor;

import draylar.staticcontent.StaticContent;
import draylar.wolveswitharmor.cca.WolfArmorComponent;
import draylar.wolveswitharmor.data.WolfArmorData;
import draylar.wolveswitharmor.item.WolfArmorItem;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WolvesWithArmor implements ModInitializer {

    public static final ComponentType<WolfArmorComponent> WOLF_ARMOR = ComponentRegistry.INSTANCE.registerIfAbsent(id("wolf_armor"), WolfArmorComponent.class);

    @Override
    public void onInitialize() {
        StaticContent.load(id("wolf_armor"), WolfArmorData.class);

        EntityComponentCallback.event(WolfEntity.class).register((wolfEntity, components) ->
                components.put(WOLF_ARMOR, new WolfArmorComponent(wolfEntity)));

        UseEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
            if (entity instanceof WolfEntity) {
                WolfEntity wolfEntity = (WolfEntity) entity;

                if (wolfEntity.getOwnerUuid() != null) {
                    if (wolfEntity.getOwnerUuid().equals(playerEntity.getUuid())) {
                        WolfArmorComponent wolfComponent = WOLF_ARMOR.get(wolfEntity);

                        // wolf has no armor, player has armor
                        if (wolfComponent.getArmor().isEmpty() && playerEntity.getMainHandStack().getItem() instanceof WolfArmorItem) {
                            if(!world.isClient) {
                                wolfComponent.setArmor(playerEntity.getMainHandStack());

                                if(!playerEntity.isCreative()) {
                                    playerEntity.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                                }
                            }

                            return ActionResult.SUCCESS;
                        }

                        // wolf has armor, player has empty hand, player sneaking
                        else if (!wolfComponent.getArmor().isEmpty() && playerEntity.getMainHandStack().isEmpty() && playerEntity.isSneaking()) {
                            if(!world.isClient) {
                                ItemStack clonedArmor = wolfComponent.getArmor().copy();
                                wolfComponent.setArmor(ItemStack.EMPTY);
                                playerEntity.setStackInHand(Hand.MAIN_HAND, clonedArmor);
                            }

                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }

            return ActionResult.PASS;
        });
    }

    public static Identifier id(String name) {
        return new Identifier("wolveswitharmor", name);
    }
}
