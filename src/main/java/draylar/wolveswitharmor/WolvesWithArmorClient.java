package draylar.wolveswitharmor;

import draylar.wolveswitharmor.client.WolfArmorModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ExtraModelProvider;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModels;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class WolvesWithArmorClient implements ClientModInitializer {

    public static final EntityModelLayer WOLF_ARMOR = new EntityModelLayer(WolvesWithArmor.id("wolf_armor"), "wolf_armor");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(WOLF_ARMOR, () -> WolfArmorModel.getTexturedModelData(new Dilation(0.35f)));

        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), Registry.ITEM.get(WolvesWithArmor.id("leather_wolf_armor")));
    }
}
