package draylar.wolveswitharmor.cca;

import draylar.wolveswitharmor.WolvesWithArmor;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class WolfArmorComponent implements EntitySyncedComponent {

    private final BasicInventory armor = new BasicInventory(1);
    private final WolfEntity wolfEntity;

    public WolfArmorComponent(WolfEntity wolfEntity) {
        this.wolfEntity = wolfEntity;
    }

    public BasicInventory getArmor() {
        return armor;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        armor.setInvStack(0, ItemStack.fromTag(tag.getCompound("ArmorInventory")));
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        CompoundTag armorInventoryTag = new CompoundTag();
        armor.getInvStack(0).toTag(armorInventoryTag);
        tag.put("ArmorInventory", armorInventoryTag);
        return tag;
    }

    @Override
    public Entity getEntity() {
        return wolfEntity;
    }

    @Override
    public ComponentType<?> getComponentType() {
        return WolvesWithArmor.WOLF_ARMOR;
    }
}
