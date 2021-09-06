package draylar.wolveswitharmor.impl.client;

import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;

public class LeatherHueProvider implements ItemColorProvider {

    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        return tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack);
    }
}
