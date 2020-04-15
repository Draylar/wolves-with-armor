package draylar.wolveswitharmor.mixin;

import draylar.wolveswitharmor.client.feature.WolfArmorFeatureRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.entity.passive.WolfEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfEntityRenderer.class)
public abstract class WolfEntityRendererMixin extends MobEntityRenderer<WolfEntity, WolfEntityModel<WolfEntity>> {

    public WolfEntityRendererMixin(EntityRenderDispatcher renderManager, WolfEntityModel<WolfEntity> model, float f) {
        super(renderManager, model, f);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void addWolfArmorFeature(EntityRenderDispatcher entityRenderDispatcher, CallbackInfo ci) {
        this.addFeature(new WolfArmorFeatureRenderer(this));
    }
}
