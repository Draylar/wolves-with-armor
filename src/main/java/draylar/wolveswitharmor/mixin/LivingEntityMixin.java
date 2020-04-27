package draylar.wolveswitharmor.mixin;

import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    private static final UUID WOLF_ARMOR_BONUS_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");

    @Shadow public abstract EntityAttributeInstance getAttributeInstance(EntityAttribute attribute);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getArmor", at = @At("HEAD"))
    private void getArmor(CallbackInfoReturnable<Integer> cir) {
        if((Object) this instanceof WolfEntity) {
            WolfEntity wolfEntity = (WolfEntity) (Object) this;
            ItemStack stack = WolvesWithArmor.WOLF_ARMOR.get(wolfEntity).getArmor();

            this.getAttributeInstance(EntityAttributes.ARMOR).removeModifier(WOLF_ARMOR_BONUS_UUID);

            if(stack.getItem() instanceof WolfArmorItem) {
                int i = ((WolfArmorItem) stack.getItem()).getBonus();

                if (i != 0) {
                    this.getAttributeInstance(EntityAttributes.ARMOR).addModifier((new EntityAttributeModifier(WOLF_ARMOR_BONUS_UUID, "Wolf armor bonus", i, EntityAttributeModifier.Operation.ADDITION)).setSerialize(false));
                }
            }
        }
    }
}
