package draylar.wolveswitharmor.mixin;

import draylar.wolveswitharmor.api.WolfArmorAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfEntity.class)
public abstract class WolfArmorMixin extends LivingEntity implements WolfArmorAccessor {

    private static final DataParameter<ItemStack> ARMOR = EntityDataManager.defineId(WolfEntity.class, DataSerializers.ITEM_STACK);
    private static final String ARMOR_KEY = "Armor";

    private WolfArmorMixin(EntityType<? extends LivingEntity> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }

    @Inject(
            method = "defineSynchedData",
            at = @At("RETURN")
    )
    private void registerArmorData(CallbackInfo ci) {
        this.entityData.define(ARMOR, ItemStack.EMPTY);
    }

    @Inject(
            method = "addAdditionalSaveData",
            at = @At("RETURN")
    )
    private void onSaveData(CompoundNBT tag, CallbackInfo ci) {
        CompoundNBT armor = new CompoundNBT();
        entityData.get(ARMOR).save(armor);
        tag.put(ARMOR_KEY, armor);
    }

    @Inject(
            method = "readAdditionalSaveData",
            at = @At("RETURN")
    )
    private void onReadData(CompoundNBT tag, CallbackInfo ci) {
        CompoundNBT armor = tag.getCompound(ARMOR_KEY);
        entityData.set(ARMOR, ItemStack.of(armor));
    }

    @Override
    public ItemStack getArmor() {
        return entityData.get(ARMOR);
    }

    @Override
    public void setArmor(ItemStack stack) {
        entityData.set(ARMOR, stack);
    }
}
