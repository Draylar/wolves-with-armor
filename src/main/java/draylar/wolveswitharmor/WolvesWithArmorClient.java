package draylar.wolveswitharmor;

import draylar.wolveswitharmor.registry.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.DyeableItem;

@Environment(EnvType.CLIENT)
public class WolvesWithArmorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), Items.LEATHER_WOLF_ARMOR);
    }
}
