package draylar.wolveswitharmor.mixin;

import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.cca.WolfArmorComponent;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ItemScatterer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin extends TameableEntity {

    private WolfEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setOnFireFor(int seconds) {
        ItemStack armor = WolvesWithArmor.WOLF_ARMOR.get(this).getArmor();
        if(armor != null && armor.getItem() instanceof WolfArmorItem && armor.getItem().isFireproof()) {
            return;
        }

        super.setOnFireFor(seconds);
    }

    @Override
    public boolean isFireImmune() {
        ItemStack armor = WolvesWithArmor.WOLF_ARMOR.get(this).getArmor();
        if(armor != null && armor.getItem() instanceof WolfArmorItem && armor.getItem().isFireproof()) {
            return true;
        }

        return super.isFireImmune();
    }

    @Inject(
            method = "onDeath",
            at = @At("HEAD"))
    private void dropArmorOnDeath(DamageSource source, CallbackInfo ci) {
        WolfArmorComponent component = WolvesWithArmor.WOLF_ARMOR.get(this);
        ItemStack armor = component.getArmor();
        if(!armor.isEmpty()) {
            ItemScatterer.spawn(world, getX(), getY(), getZ(), armor);
        }
    }
}
