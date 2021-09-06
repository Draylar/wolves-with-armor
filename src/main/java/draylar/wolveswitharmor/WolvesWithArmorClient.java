package draylar.wolveswitharmor;

import draylar.wolveswitharmor.client.WolfArmorModel;
import draylar.wolveswitharmor.impl.client.LeatherHueProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class WolvesWithArmorClient implements ClientModInitializer {

    public static final EntityModelLayer WOLF_ARMOR = new EntityModelLayer(WolvesWithArmor.id("wolf_armor"), "wolf_armor");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(WOLF_ARMOR, () -> WolfArmorModel.getTexturedModelData(new Dilation(0.35f)));
        ColorProviderRegistry.ITEM.register(new LeatherHueProvider(), Registry.ITEM.get(WolvesWithArmor.id("leather_wolf_armor")));
    }
}
