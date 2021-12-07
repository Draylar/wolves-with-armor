package draylar.wolveswitharmor.mixin;

import draylar.wolveswitharmor.impl.WolfDataAccessor;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ItemScatterer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin extends TameableEntity implements WolfDataAccessor {

    @Unique private static final TrackedData<ItemStack> WOLF_ARMOR = DataTracker.registerData(WolfEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    private WolfEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }


    // ARMOR DATA INJECTS -------------
    @Inject(method = "initDataTracker", at = @At("HEAD"))
    private void injectWolfArmorData(CallbackInfo ci) {
        dataTracker.startTracking(WOLF_ARMOR, ItemStack.EMPTY);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeArmorNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.put("WolfArmor", getWolfArmor().writeNbt(new NbtCompound()));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void readArmorNbt(NbtCompound nbt, CallbackInfo ci) {
        if(nbt.contains("WolfArmor")) {
            setWolfArmor(ItemStack.fromNbt(nbt.getCompound("WolfArmor")));
        }
    }


    // ARMOR LOGIC -------------
    @Override
    public void setOnFireFor(int seconds) {
        ItemStack armor = getWolfArmor();
        if(armor != null && armor.getItem() instanceof WolfArmorItem && armor.getItem().isFireproof()) {
            return;
        }

        super.setOnFireFor(seconds);
    }

    @Override
    public boolean isFireImmune() {
        ItemStack armor = getWolfArmor();
        if(armor != null && armor.getItem() instanceof WolfArmorItem && armor.getItem().isFireproof()) {
            return true;
        }

        return super.isFireImmune();
    }

    @Inject(
            method = "onDeath",
            at = @At("HEAD"))
    private void dropArmorOnDeath(DamageSource source, CallbackInfo ci) {
        ItemStack armor = getWolfArmor();
        if(!armor.isEmpty()) {
            ItemScatterer.spawn(world, getX(), getY(), getZ(), armor);
        }
    }


    // DATA ACCESSORS -------------
    @Override
    public void setWolfArmor(ItemStack stack) {
        dataTracker.set(WOLF_ARMOR, stack);
    }

    @Override
    public ItemStack getWolfArmor() {
        return dataTracker.get(WOLF_ARMOR);
    }
}
