package draylar.wolveswitharmor;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class WolvesWithArmorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), Registry.ITEM.get(WolvesWithArmor.id("leather_wolf_armor")));
    }
}
