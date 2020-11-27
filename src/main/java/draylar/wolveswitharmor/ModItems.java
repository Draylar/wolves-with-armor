package draylar.wolveswitharmor;

import draylar.wolveswitharmor.data.WolfArmorData;
import draylar.wolveswitharmor.item.DyeableWolfArmorItem;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "wolveswitharmor");

    public static final RegistryObject<Item> LEATHER_WOLF_ARMOR = register("leather_wolf_armor", new DyeableWolfArmorItem(new WolfArmorData(3, "leather")));
    public static final RegistryObject<Item> IRON_WOLF_ARMOR = register("iron_wolf_armor", new WolfArmorItem(new WolfArmorData(5, "iron")));
    public static final RegistryObject<Item> GOLDEN_WOLF_ARMOR = register("golden_wolf_armor", new WolfArmorItem(new WolfArmorData(7, "golden")));
    public static final RegistryObject<Item> DIAMOND_WOLF_ARMOR = register("diamond_wolf_armor", new WolfArmorItem(new WolfArmorData(11, "diamond")));
    public static final RegistryObject<Item> NETHERITE_WOLF_ARMOR = register("netherite_wolf_armor", new WolfArmorItem(new WolfArmorData(15, "netherite"), true));

    public static RegistryObject<Item> register(String name, Item item) {
        return ITEMS.register(name, () -> {
            return item;
        });
    }

    private ModItems() {
        // NO-OP
    }
}
