package draylar.wolveswitharmor.container;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class WolfContainer extends Container {

    private final Inventory playerInv;
    private final WolfEntity entity;

    public WolfContainer(int syncId, PlayerInventory playerInventory, Inventory inventory, final WolfEntity wolf) {
        super(null, syncId);
        this.playerInv = inventory;
        this.entity = wolf;
        inventory.onInvOpen(playerInventory.player);

        this.addSlot(new Slot(inventory, 0, 8, 36) {
            @Environment(EnvType.CLIENT)
            @Override
            public boolean doDrawHoveringEffect() {
                return true;
            }

            @Override
            public int getMaxStackAmount() {
                return 1;
            }
        });

        int o;
        int n;

        for(o = 0; o < 3; ++o) {
            for(n = 0; n < 9; ++n) {
                this.addSlot(new Slot(playerInventory, n + o * 9 + 9, 8 + n * 18, 102 + o * 18 + -18));
            }
        }

        for(o = 0; o < 9; ++o) {
            this.addSlot(new Slot(playerInventory, o, 8 + o * 18, 142));
        }
    }

    public boolean canUse(PlayerEntity player) {
        return this.playerInv.canPlayerUseInv(player) && this.entity.isAlive() && this.entity.distanceTo(player) < 8.0F;
    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            int i = this.playerInv.getInvSize();
            if (invSlot < i) {
                if (!this.insertItem(itemStack2, i, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).canInsert(itemStack2) && !this.getSlot(1).hasStack()) {
                if (!this.insertItem(itemStack2, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).canInsert(itemStack2)) {
                if (!this.insertItem(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i <= 2 || !this.insertItem(itemStack2, 2, i, false)) {
                int k = i + 27;
                int m = k + 9;
                if (invSlot >= k && invSlot < m) {
                    if (!this.insertItem(itemStack2, i, k, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (invSlot >= i && invSlot < k) {
                    if (!this.insertItem(itemStack2, k, m, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.insertItem(itemStack2, k, k, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.playerInv.onInvClose(player);
    }
}