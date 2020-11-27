package draylar.wolveswitharmor.mixin;

import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin extends TameableEntity {

    private WolfEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setSecondsOnFire(int seconds) {
        ItemStack armor = WolvesWithArmor.getAccessor((WolfEntity) (Object) this).getArmor();
        if(armor != null && armor.getItem() instanceof WolfArmorItem && armor.getItem().isFireResistant()) {
            return;
        }

        super.setSecondsOnFire(seconds);
    }

    @Override
    public boolean fireImmune() {
        ItemStack armor = WolvesWithArmor.getAccessor((WolfEntity) (Object) this).getArmor();
        if(armor != null && armor.getItem() instanceof WolfArmorItem && armor.getItem().isFireResistant()) {
            return true;
        }

        return super.fireImmune();
    }
}
