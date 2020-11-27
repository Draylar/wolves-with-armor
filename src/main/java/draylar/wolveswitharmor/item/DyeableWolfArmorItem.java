package draylar.wolveswitharmor.item;

import draylar.wolveswitharmor.data.WolfArmorData;
import net.minecraft.item.IDyeableArmorItem;

public class DyeableWolfArmorItem extends WolfArmorItem implements IDyeableArmorItem {

    public DyeableWolfArmorItem(WolfArmorData data) {
        super(data);
    }

    public DyeableWolfArmorItem(WolfArmorData data, boolean fireproof) {
        super(data, fireproof);
    }
}

