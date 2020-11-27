package draylar.wolveswitharmor.item;

import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.data.WolfArmorData;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class WolfArmorItem extends Item {

    private final int bonus;
    private final ResourceLocation entityTexture;

    public WolfArmorItem(WolfArmorData data) {
        super(new Item.Properties().stacksTo(1).tab(WolvesWithArmor.GROUP));
        this.bonus = data.getBonus();
        this.entityTexture = WolvesWithArmor.id("textures/entity/wolf/armor/wolf_armor_" + data.getName() + ".png");
    }

    public WolfArmorItem(WolfArmorData data, boolean isFireproof) {
        super(new Item.Properties().stacksTo(1).tab(WolvesWithArmor.GROUP).fireResistant());
        this.bonus = data.getBonus();
        this.entityTexture = WolvesWithArmor.id("textures/entity/wolf/armor/wolf_armor_" + data.getName() + ".png");
    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getEntityTexture() {
        return this.entityTexture;
    }

    public int getBonus(ItemStack stack) {
        int protectionLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, stack) * 2;
        return this.bonus + protectionLevel;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag context) {
        super.appendHoverText(stack, world, tooltip, context);

        tooltip.add(new StringTextComponent(""));
        tooltip.add(new TranslationTextComponent("wolveswitharmor.tooltip.when_equipped").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("wolveswitharmor.tooltip.bonus", getBonus(stack)).withStyle(TextFormatting.BLUE));

        if(stack.isEnchanted()) {
            tooltip.add(new StringTextComponent(""));
        }
    }
}
