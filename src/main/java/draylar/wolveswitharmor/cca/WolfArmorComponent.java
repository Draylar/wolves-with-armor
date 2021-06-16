package draylar.wolveswitharmor.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import draylar.wolveswitharmor.WolvesWithArmor;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class WolfArmorComponent implements ComponentV3, AutoSyncedComponent {

    private ItemStack armor = ItemStack.EMPTY;
    private final WolfEntity wolfEntity;

    public WolfArmorComponent(WolfEntity wolfEntity) {
        this.wolfEntity = wolfEntity;
    }

    public void setArmor(ItemStack armor) {
        this.armor = armor.copy();
        WolvesWithArmor.WOLF_ARMOR.sync(wolfEntity);
    }

    public ItemStack getArmor() {
        return armor;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        armor = ItemStack.fromNbt(tag.getCompound("ArmorInventory"));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        NbtCompound armorInventoryTag = new NbtCompound();
        armor.writeNbt(armorInventoryTag);
        tag.put("ArmorInventory", armorInventoryTag);
    }
}
