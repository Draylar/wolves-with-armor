package draylar.wolveswitharmor.data;

import draylar.staticcontent.api.ContentData;
import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.item.DyeableWolfArmorItem;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WolfArmorData implements ContentData {

    private final String name;
    private final int bonus;
    private final boolean fireproof;
    private final boolean dyeable;

    public WolfArmorData(int bonus, String name, boolean fireproof, boolean dyeable) {
        this.bonus = bonus;
        this.name = name;
        this.fireproof = fireproof;
        this.dyeable = dyeable;
    }

    @Override
    public void register(Identifier identifier) {
        // I don't like the person who made Item.Settings#fireproof() have no args...

        if(isFireproof()) {
            if(dyeable) {
                Registry.register(Registry.ITEM, WolvesWithArmor.id(name + "_wolf_armor"), new DyeableWolfArmorItem(this, true));
            } else {
                Registry.register(Registry.ITEM, WolvesWithArmor.id(name + "_wolf_armor"), new WolfArmorItem(this, true));
            }
        } else {
            if(dyeable) {
                Registry.register(Registry.ITEM, WolvesWithArmor.id(name + "_wolf_armor"), new DyeableWolfArmorItem(this));
            } else {
                Registry.register(Registry.ITEM, WolvesWithArmor.id(name + "_wolf_armor"), new WolfArmorItem(this));
            }
        }
    }

    public int getBonus() {
        return bonus;
    }

    public boolean isFireproof() {
        return fireproof;
    }

    public String getName() {
        return name;
    }
}
