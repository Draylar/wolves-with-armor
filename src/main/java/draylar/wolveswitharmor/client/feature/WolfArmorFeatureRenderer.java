package draylar.wolveswitharmor.client.feature;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.client.WolfArmorModel;
import draylar.wolveswitharmor.item.DyeableWolfArmorItem;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WolfArmorFeatureRenderer extends LayerRenderer<WolfEntity, WolfModel<WolfEntity>> {

    private final WolfArmorModel model = new WolfArmorModel(0.35f);

    public WolfArmorFeatureRenderer(IEntityRenderer<WolfEntity, WolfModel<WolfEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int i, WolfEntity wolf, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = WolvesWithArmor.getAccessor(wolf).getArmor();

        if (itemStack.getItem() instanceof WolfArmorItem) {
            WolfArmorItem armorItem = (WolfArmorItem) itemStack.getItem();
            this.getParentModel().copyPropertiesTo(this.model);

            this.model.prepareMobModel(wolf, f, g, h);
            this.model.setupAnim(wolf, f, g, j, k, l);

            float q;
            float r;
            float s;

            if (armorItem instanceof DyeableWolfArmorItem) {
                int m = ((DyeableWolfArmorItem) armorItem).getColor(itemStack);
                q = (float)(m >> 16 & 255) / 255.0F;
                r = (float)(m >> 8 & 255) / 255.0F;
                s = (float)(m & 255) / 255.0F;
            } else {
                q = 1.0F;
                r = 1.0F;
                s = 1.0F;
            }

            IVertexBuilder vertexConsumer = ItemRenderer.getArmorFoilBuffer(vertexConsumerProvider, RenderType.armorCutoutNoCull(armorItem.getEntityTexture()), false, itemStack.isEnchanted());
            this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, q, r, s, 1.0F);
        }
    }
}

