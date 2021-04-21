package draylar.wolveswitharmor.data;

import draylar.staticcontent.api.ContentData;
import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.item.DyeableWolfArmorItem;
import draylar.wolveswitharmor.item.WolfArmorDispenserBehavior;
import draylar.wolveswitharmor.item.WolfArmorItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

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
        Item item;

        if(isFireproof()) {
            if(dyeable) {
                item = new DyeableWolfArmorItem(this, true);
            } else {
                item = new WolfArmorItem(this, true);
            }
        } else {
            if(dyeable) {
                item = new DyeableWolfArmorItem(this);
            } else {
                item = new WolfArmorItem(this);
            }
        }

        Registry.register(Registry.ITEM, WolvesWithArmor.id(name + "_wolf_armor"), item);
        DispenserBlock.registerBehavior(item, new WolfArmorDispenserBehavior());
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
