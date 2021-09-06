package draylar.wolveswitharmor;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import draylar.staticcontent.StaticContent;
import draylar.wolveswitharmor.cca.WolfArmorComponent;
import draylar.wolveswitharmor.data.WolfArmorData;
import draylar.wolveswitharmor.impl.WolfInteractionHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;

public class WolvesWithArmor implements ModInitializer, EntityComponentInitializer {

    public static final ComponentKey<WolfArmorComponent> WOLF_ARMOR = ComponentRegistryV3.INSTANCE.getOrCreate(id("wolf_armor"), WolfArmorComponent.class);

    @Override
    public void onInitialize() {
        StaticContent.load(id("wolf_armor"), WolfArmorData.class);
        UseEntityCallback.EVENT.register(new WolfInteractionHandler());
    }

    public static Identifier id(String name) {
        return new Identifier("wolveswitharmor", name);
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(WolfEntity.class, WOLF_ARMOR, WolfArmorComponent::new);
    }
}
