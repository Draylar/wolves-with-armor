package draylar.wolveswitharmor;

import draylar.staticcontent.StaticContent;
import draylar.wolveswitharmor.data.WolfArmorData;
import draylar.wolveswitharmor.impl.WolfDataAccessor;
import draylar.wolveswitharmor.impl.WolfInteractionHandler;
import draylar.wolveswitharmor.registry.WWASounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;

public class WolvesWithArmor implements ModInitializer {

    @Override
    public void onInitialize() {
        StaticContent.load(id("wolf_armor"), WolfArmorData.class);
        UseEntityCallback.EVENT.register(new WolfInteractionHandler());
        WWASounds.initialize();
    }

    public static Identifier id(String name) {
        return new Identifier("wolveswitharmor", name);
    }

    public static WolfDataAccessor getData(WolfEntity wolf) {
        return ((WolfDataAccessor) wolf);
    }
}
