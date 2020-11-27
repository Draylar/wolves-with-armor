package draylar.wolveswitharmor.api;

import net.minecraft.item.ItemStack;

public interface WolfArmorAccessor {
    ItemStack getArmor();
    void setArmor(ItemStack stack);
}
