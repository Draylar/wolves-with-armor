package draylar.wolveswitharmor.mixin;

import draylar.wolveswitharmor.client.feature.WolfArmorFeatureRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfRenderer.class)
public abstract class WolfEntityRendererMixin extends MobRenderer<WolfEntity, WolfModel<WolfEntity>> {

    private WolfEntityRendererMixin(EntityRendererManager manager, WolfModel<WolfEntity> model, float shadow) {
        super(manager, model, shadow);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void addWolfArmorFeature(EntityRendererManager manager, CallbackInfo ci) {
        this.addLayer(new WolfArmorFeatureRenderer(this));
    }
}
