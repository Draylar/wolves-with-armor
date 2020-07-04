package draylar.wolveswitharmor.item;

import draylar.wolveswitharmor.WolvesWithArmor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class WolfArmorItem extends Item {

    private final int bonus;
    private final Identifier entityTexture;

    public WolfArmorItem(int bonus, String name) {
        super(new Item.Settings().maxCount(1).group(WolvesWithArmor.GROUP));
        this.bonus = bonus;
        this.entityTexture = WolvesWithArmor.id("textures/entity/wolf/armor/wolf_armor_" + name + ".png");
    }

    public WolfArmorItem(int bonus, String name, boolean fireproof) {
        super(new Item.Settings().maxCount(1).group(WolvesWithArmor.GROUP).fireproof());
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

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(new LiteralText(""));
        tooltip.add(new TranslatableText("wolveswitharmor.tooltip.when_equipped").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("wolveswitharmor.tooltip.bonus", bonus).formatted(Formatting.BLUE));
    }
}
