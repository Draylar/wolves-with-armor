package draylar.wolveswitharmor;

import draylar.wolveswitharmor.client.screen.WolfScreen;
import draylar.wolveswitharmor.container.WolfContainer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.entity.passive.WolfEntity;

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
    }
}
