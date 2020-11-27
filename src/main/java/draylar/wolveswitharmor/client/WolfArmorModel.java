package draylar.wolveswitharmor.client;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WolfArmorModel<T extends WolfEntity> extends TintedAgeableModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer realHead;
    private final ModelRenderer body;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer tail;
    private final ModelRenderer realTail;
    private final ModelRenderer upperBody;

    public WolfArmorModel(float extra) {
        float f = 0.0F;
        float f1 = 13.5F;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(-1.0F, 13.5F, -7.0F);
        this.realHead = new ModelRenderer(this, 0, 0);
        this.realHead.addBox(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, extra);
        this.head.addChild(this.realHead);
        this.body = new ModelRenderer(this, 18, 14);
        this.body.addBox(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, extra);
        this.body.setPos(0.0F, 14.0F, 2.0F);
        this.upperBody = new ModelRenderer(this, 21, 0);
        this.upperBody.addBox(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, extra);
        this.upperBody.setPos(-1.0F, 14.0F, 2.0F);
        this.leg0 = new ModelRenderer(this, 0, 18);
        this.leg0.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.leg0.setPos(-2.5F, 16.0F, 7.0F);
        this.leg1 = new ModelRenderer(this, 0, 18);
        this.leg1.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.leg1.setPos(0.5F, 16.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 18);
        this.leg2.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.leg2.setPos(-2.5F, 16.0F, -4.0F);
        this.leg3 = new ModelRenderer(this, 0, 18);
        this.leg3.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.leg3.setPos(0.5F, 16.0F, -4.0F);
        this.tail = new ModelRenderer(this, 9, 18);
        this.tail.setPos(-1.0F, 12.0F, 8.0F);
        this.realTail = new ModelRenderer(this, 9, 18);
        this.realTail.addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.tail.addChild(this.realTail);
        this.realHead.texOffs(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.realHead.texOffs(16, 14).addBox(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.realHead.texOffs(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3.0F, 3.0F, 4.0F, 0.0F);
    }

    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3, this.tail, this.upperBody);
    }

    public void prepareMobModel(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        if (p_212843_1_.isAngry()) {
            this.tail.yRot = 0.0F;
        } else {
            this.tail.yRot = MathHelper.cos(p_212843_2_ * 0.6662F) * 1.4F * p_212843_3_;
        }

        if (p_212843_1_.isInSittingPose()) {
            this.upperBody.setPos(-1.0F, 16.0F, -3.0F);
            this.upperBody.xRot = 1.2566371F;
            this.upperBody.yRot = 0.0F;
            this.body.setPos(0.0F, 18.0F, 0.0F);
            this.body.xRot = ((float)Math.PI / 4F);
            this.tail.setPos(-1.0F, 21.0F, 6.0F);
            this.leg0.setPos(-2.5F, 22.7F, 2.0F);
            this.leg0.xRot = ((float)Math.PI * 1.5F);
            this.leg1.setPos(0.5F, 22.7F, 2.0F);
            this.leg1.xRot = ((float)Math.PI * 1.5F);
            this.leg2.xRot = 5.811947F;
            this.leg2.setPos(-2.49F, 17.0F, -4.0F);
            this.leg3.xRot = 5.811947F;
            this.leg3.setPos(0.51F, 17.0F, -4.0F);
        } else {
            this.body.setPos(0.0F, 14.0F, 2.0F);
            this.body.xRot = ((float)Math.PI / 2F);
            this.upperBody.setPos(-1.0F, 14.0F, -3.0F);
            this.upperBody.xRot = this.body.xRot;
            this.tail.setPos(-1.0F, 12.0F, 8.0F);
            this.leg0.setPos(-2.5F, 16.0F, 7.0F);
            this.leg1.setPos(0.5F, 16.0F, 7.0F);
            this.leg2.setPos(-2.5F, 16.0F, -4.0F);
            this.leg3.setPos(0.5F, 16.0F, -4.0F);
            this.leg0.xRot = MathHelper.cos(p_212843_2_ * 0.6662F) * 1.4F * p_212843_3_;
            this.leg1.xRot = MathHelper.cos(p_212843_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_212843_3_;
            this.leg2.xRot = MathHelper.cos(p_212843_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_212843_3_;
            this.leg3.xRot = MathHelper.cos(p_212843_2_ * 0.6662F) * 1.4F * p_212843_3_;
        }

        this.realHead.zRot = p_212843_1_.getHeadRollAngle(p_212843_4_) + p_212843_1_.getBodyRollAngle(p_212843_4_, 0.0F);
        this.upperBody.zRot = p_212843_1_.getBodyRollAngle(p_212843_4_, -0.08F);
        this.body.zRot = p_212843_1_.getBodyRollAngle(p_212843_4_, -0.16F);
        this.realTail.zRot = p_212843_1_.getBodyRollAngle(p_212843_4_, -0.2F);
    }

    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.head.xRot = p_225597_6_ * ((float)Math.PI / 180F);
        this.head.yRot = p_225597_5_ * ((float)Math.PI / 180F);
        this.tail.xRot = p_225597_4_;
    }
}
