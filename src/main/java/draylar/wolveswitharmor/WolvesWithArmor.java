package draylar.wolveswitharmor;

import draylar.wolveswitharmor.cca.WolfArmorComponent;
import draylar.wolveswitharmor.container.WolfContainer;
import draylar.wolveswitharmor.registry.Items;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class WolvesWithArmor implements ModInitializer {

    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(Items.DIAMOND_WOLF_ARMOR));
    public static final ComponentType<WolfArmorComponent> WOLF_ARMOR = ComponentRegistry.INSTANCE.registerIfAbsent(id("wolf_armor"), WolfArmorComponent.class);

    @Override
    public void onInitialize() {
        EntityComponentCallback.event(WolfEntity.class).register((wolfEntity, components) ->
                components.put(WOLF_ARMOR, new WolfArmorComponent(wolfEntity)));

        Items.init();

        ContainerProviderRegistry.INSTANCE.registerFactory(id("wolf_ui"), (i, identifier, playerEntity, packetByteBuf) -> {
            WolfEntity wolf = (WolfEntity) playerEntity.world.getEntityById(packetByteBuf.readInt());
            return new WolfContainer(i, playerEntity.inventory, WolvesWithArmor.WOLF_ARMOR.get(wolf).getArmor(), wolf);
        });

        UseEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
            if (entity instanceof WolfEntity) {
                WolfEntity wolfEntity = (WolfEntity) entity;

                if(wolfEntity.getOwnerUuid() != null) {
                    if (wolfEntity.getOwnerUuid().equals(playerEntity.getUuid())) {
                        if(!world.isClient) {
                            ContainerProviderRegistry.INSTANCE.openContainer(id("wolf_ui"), playerEntity, buf -> buf.writeInt(entity.getEntityId()));
                        }

                        return ActionResult.SUCCESS;
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
