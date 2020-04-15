package draylar.wolveswitharmor.registry;

import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.item.DyeableWolfArmorItem;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class Items {

    public static final Item LEATHER_WOLF_ARMOR = register("leather_wolf_armor", new DyeableWolfArmorItem(3, "leather"));
    public static final Item IRON_WOLF_ARMOR = register("iron_wolf_armor", new WolfArmorItem(5, "iron"));
    public static final Item GOLDEN_WOLF_ARMOR = register("golden_wolf_armor", new WolfArmorItem(7, "gold"));
    public static final Item DIAMOND_WOLF_ARMOR = register("diamond_wolf_armor", new WolfArmorItem(11, "diamond"));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, WolvesWithArmor.id(name), item);
    }

    public static void init() {

    }

    private Items() {

    }
}
