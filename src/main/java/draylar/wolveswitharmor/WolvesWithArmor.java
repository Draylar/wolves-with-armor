package draylar.wolveswitharmor;

import draylar.wolveswitharmor.api.WolfArmorAccessor;
import draylar.wolveswitharmor.item.ModItemGroup;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("wolveswitharmor")
public class WolvesWithArmor {

    public static final ItemGroup GROUP = new ModItemGroup();

    public WolvesWithArmor() {
        MinecraftForge.EVENT_BUS.register(new EventHandlers());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static WolfArmorAccessor getAccessor(WolfEntity wolf) {
        return ((WolfArmorAccessor) wolf);
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation("wolveswitharmor", name);
    }
}
