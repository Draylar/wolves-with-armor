package draylar.wolveswitharmor.client.feature;

import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.item.DyeableWolfArmorItem;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class WolfArmorFeatureRenderer extends FeatureRenderer<WolfEntity, WolfEntityModel<WolfEntity>> {

    private final WolfEntityModel<WolfEntity> model = new WolfEntityModel<>();

    public WolfArmorFeatureRenderer(FeatureRendererContext<WolfEntity, WolfEntityModel<WolfEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, WolfEntity wolfEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = WolvesWithArmor.WOLF_ARMOR.get(wolfEntity).getArmor();

        if (itemStack.getItem() instanceof WolfArmorItem) {
            WolfArmorItem WolfArmorItem = (WolfArmorItem) itemStack.getItem();
            this.getContextModel().copyStateTo(this.model);

            this.model.animateModel(wolfEntity, f, g, h);
            this.model.setAngles(wolfEntity, f, g, j, k, l);

            float q;
            float r;
            float s;

            if (WolfArmorItem instanceof DyeableWolfArmorItem) {
                int m = ((DyeableWolfArmorItem) WolfArmorItem).getColor(itemStack);
                q = (float)(m >> 16 & 255) / 255.0F;
                r = (float)(m >> 8 & 255) / 255.0F;
                s = (float)(m & 255) / 255.0F;
            } else {
                q = 1.0F;
                r = 1.0F;
                s = 1.0F;
            }

            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(WolfArmorItem.getEntityTexture()));
            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, q, r, s, 1.0F);
        }
    }
}

