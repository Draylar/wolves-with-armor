package draylar.wolveswitharmor.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.entity.passive.WolfEntity;

public class WolfArmorModel extends WolfEntityModel<WolfEntity> {

    public WolfArmorModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData(Dilation dilation) {
        ModelData modelData = new ModelData();
        ModelPartData rootModelPart = modelData.getRoot();
        ModelPartData headModelPart = rootModelPart.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 13.5F, -7.0F));

        headModelPart.addChild("real_head",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, dilation)
                        .uv(16, 14)
                        .cuboid(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, dilation)
                        .uv(16, 14)
                        .cuboid(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, dilation)
                        .uv(0, 10)
                        .cuboid(-0.5F, 0.0F, -5.0F, 3.0F, 3.0F, 4.0F, dilation),
                ModelTransform.NONE);

        rootModelPart.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create()
                                .uv(18, 14)
                                .cuboid(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, dilation),
                        ModelTransform.of(0.0F, 14.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));

        rootModelPart.addChild("upper_body", ModelPartBuilder.create()
                .uv(21, 0)
                .cuboid(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, dilation),
                ModelTransform.of(-1.0F, 14.0F, -3.0F, 1.5707964F, 0.0F, 0.0F));

        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create()
                .uv(0, 18)
                .cuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, dilation);

        rootModelPart.addChild(EntityModelPartNames.RIGHT_HIND_LEG, modelPartBuilder, ModelTransform.pivot(-2.5F, 16.0F, 7.0F));
        rootModelPart.addChild(EntityModelPartNames.LEFT_HIND_LEG, modelPartBuilder, ModelTransform.pivot(0.5F, 16.0F, 7.0F));
        rootModelPart.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, modelPartBuilder, ModelTransform.pivot(-2.5F, 16.0F, -4.0F));
        rootModelPart.addChild(EntityModelPartNames.LEFT_FRONT_LEG, modelPartBuilder, ModelTransform.pivot(0.5F, 16.0F, -4.0F));
        ModelPartData tailModelPart = rootModelPart.addChild(EntityModelPartNames.TAIL, ModelPartBuilder.create(), ModelTransform.of(-1.0F, 12.0F, 8.0F, 0.62831855F, 0.0F, 0.0F));

        tailModelPart.addChild("real_tail", ModelPartBuilder.create()
                .uv(9, 18)
                .cuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, dilation), ModelTransform.NONE);

        return TexturedModelData.of(modelData, 64, 32);
    }
}
