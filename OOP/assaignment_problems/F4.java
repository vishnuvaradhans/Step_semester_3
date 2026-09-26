interface Attackable {

    String attack();

    String attack(String weaponName);
}

interface Defendable {
    String defend();
}

abstract class GameCharacter {

    private static int count = 0;
    private final String characterId;

    protected String name;

    GameCharacter(String name) {

        count++;

        characterId = "CHAR-" + count;

        this.name = name;
    }

    public abstract String getSpecialMove();

    String getCharacterId() {
        return characterId;
    }
}

class Warrior extends GameCharacter
        implements Attackable, Defendable {

    public Warrior(String name) {
        super(name);
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        return name + " strikes with an " +
               weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}

class Trap implements Defendable {

    private String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}

public class F4 {

    static void resolveDefense(
            Defendable[] combatants) {

        for (Defendable c : combatants) {
            System.out.println(c.defend());
        }
    }

    public static void main(String[] args) {

        Warrior w =
            new Warrior("Kael");

        System.out.println(w.attack());
        System.out.println(
            w.attack("Iron Sword")
        );
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());

        Trap t =
            new Trap("Spike Pit");

        System.out.println(t.defend());

        resolveDefense(
            new Defendable[]{w, t}
        );
    }
}