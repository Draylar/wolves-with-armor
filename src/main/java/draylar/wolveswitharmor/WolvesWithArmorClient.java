package draylar.wolveswitharmor;

import draylar.wolveswitharmor.client.screen.WolfScreen;
import draylar.wolveswitharmor.container.WolfContainer;
import draylar.wolveswitharmor.registry.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.DyeableItem;

@Environment(EnvType.CLIENT)
public class WolvesWithArmorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(WolvesWithArmor.id("wolf_ui"), (i, identifier, playerEntity, packetByteBuf) -> {
                    WolfEntity wolf = (WolfEntity) playerEntity.world.getEntityById(packetByteBuf.readInt());

                    return new WolfScreen(
                            new WolfContainer(i, playerEntity.inventory, WolvesWithArmor.WOLF_ARMOR.get(wolf).getArmor(), wolf),
                            playerEntity.inventory,
                            wolf
                    );
                }
        );

        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), Items.LEATHER_WOLF_ARMOR);
    }
}
