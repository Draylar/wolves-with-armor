package draylar.wolveswitharmor.mixin;

import draylar.wolveswitharmor.ModItems;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IRegistryDelegate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(ItemColors.class)
public class ItemColorsMixin {

    @Inject(
            method = "createDefault",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/client/ForgeHooksClient;onItemColorsInit(Lnet/minecraft/client/renderer/color/ItemColors;Lnet/minecraft/client/renderer/color/BlockColors;)V"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void createDefault(BlockColors p_186729_0_, CallbackInfoReturnable<ItemColors> cir, ItemColors colors) {
        colors.register((p_210239_0_, p_210239_1_) -> {
            return p_210239_1_ > 0 ? -1 : ((IDyeableArmorItem) p_210239_0_.getItem()).getColor(p_210239_0_);
        }, ModItems.LEATHER_WOLF_ARMOR.get());
    }
}
