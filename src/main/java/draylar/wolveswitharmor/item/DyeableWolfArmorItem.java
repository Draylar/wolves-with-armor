package draylar.wolveswitharmor.item;

import draylar.wolveswitharmor.data.WolfArmorData;
import net.minecraft.item.DyeableItem;

public class DyeableWolfArmorItem extends WolfArmorItem implements DyeableItem {

    public DyeableWolfArmorItem(WolfArmorData data) {
        super(data);
    }

    public DyeableWolfArmorItem(WolfArmorData data, boolean fireproof) {
        super(data, fireproof);
    }
}

