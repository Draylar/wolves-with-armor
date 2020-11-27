package draylar.wolveswitharmor.data;

public class WolfArmorData {

    private final String name;
    private final int bonus;

    public WolfArmorData(int bonus, String name) {
        this.bonus = bonus;
        this.name = name;
    }

    public int getBonus() {
        return bonus;
    }

    public String getName() {
        return name;
    }
}
