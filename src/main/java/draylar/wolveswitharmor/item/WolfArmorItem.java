package draylar.wolveswitharmor.item;

import draylar.wolveswitharmor.WolvesWithArmor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class WolfArmorItem extends Item {

    private final int bonus;
    private final Identifier entityTexture;

    public WolfArmorItem(int bonus, String name) {
        super(new Item.Settings().maxCount(1).group(WolvesWithArmor.GROUP));
        this.bonus = bonus;
        this.entityTexture = WolvesWithArmor.id("textures/entity/wolf/armor/wolf_armor_" + name + ".png");
    }

    @Environment(EnvType.CLIENT)
    public Identifier getEntityTexture() {
        return this.entityTexture;
    }

    public int getBonus() {
        return this.bonus;
    }
}
