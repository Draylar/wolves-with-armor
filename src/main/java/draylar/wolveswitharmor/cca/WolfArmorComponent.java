package draylar.wolveswitharmor.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import draylar.wolveswitharmor.WolvesWithArmor;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

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
    public void readFromNbt(CompoundTag tag) {
        armor = ItemStack.fromTag(tag.getCompound("ArmorInventory"));
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        CompoundTag armorInventoryTag = new CompoundTag();
        armor.toTag(armorInventoryTag);
        tag.put("ArmorInventory", armorInventoryTag);
    }
}
