package draylar.wolveswitharmor.registry;

import draylar.wolveswitharmor.WolvesWithArmor;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WWASounds {

    public static final SoundEvent WOLF_ARMOR_EQUIP = register("entity.wolf.armor");

    private static SoundEvent register(String name) {
        Identifier id = WolvesWithArmor.id(name);
        return  Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void initialize() {

    }
}
