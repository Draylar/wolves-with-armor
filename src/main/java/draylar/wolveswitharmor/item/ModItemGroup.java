package draylar.wolveswitharmor.item;

import draylar.wolveswitharmor.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup extends ItemGroup {

    public ModItemGroup() {
        super("wolveswitharmor.group");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.DIAMOND_WOLF_ARMOR.get());
    }
}
