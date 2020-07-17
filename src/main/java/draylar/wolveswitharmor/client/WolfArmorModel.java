package draylar.wolveswitharmor.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.entity.passive.WolfEntity;

public class WolfArmorModel extends WolfEntityModel<WolfEntity> {

    public WolfArmorModel(float extra) {
        this.head = new ModelPart(this, 0, 0);
        this.head.setPivot(-1.0F, 13.5F, -7.0F);
        this.field_20788 = new ModelPart(this, 0, 0);
        this.field_20788.addCuboid(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, extra);
        this.head.addChild(this.field_20788);
        this.torso = new ModelPart(this, 18, 14);
        this.torso.addCuboid(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, extra);
        this.torso.setPivot(0.0F, 14.0F, 2.0F);
        this.neck = new ModelPart(this, 21, 0);
        this.neck.addCuboid(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, extra);
        this.neck.setPivot(-1.0F, 14.0F, 2.0F);
        this.rightBackLeg = new ModelPart(this, 0, 18);
        this.rightBackLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.rightBackLeg.setPivot(-2.5F, 16.0F, 7.0F);
        this.leftBackLeg = new ModelPart(this, 0, 18);
        this.leftBackLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.leftBackLeg.setPivot(0.5F, 16.0F, 7.0F);
        this.rightFrontLeg = new ModelPart(this, 0, 18);
        this.rightFrontLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.rightFrontLeg.setPivot(-2.5F, 16.0F, -4.0F);
        this.leftFrontLeg = new ModelPart(this, 0, 18);
        this.leftFrontLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.leftFrontLeg.setPivot(0.5F, 16.0F, -4.0F);
        this.tail = new ModelPart(this, 9, 18);
        this.tail.setPivot(-1.0F, 12.0F, 8.0F);
        this.field_20789 = new ModelPart(this, 9, 18);
        this.field_20789.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, extra);
        this.tail.addChild(this.field_20789);
        this.field_20788.setTextureOffset(16, 14).addCuboid(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.field_20788.setTextureOffset(16, 14).addCuboid(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.field_20788.setTextureOffset(0, 10).addCuboid(-0.5F, 0.0F, -5.0F, 3.0F, 3.0F, 4.0F, 0.0F);
    }
}
