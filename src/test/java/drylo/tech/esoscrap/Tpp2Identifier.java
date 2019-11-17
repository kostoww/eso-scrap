package drylo.tech.esoscrap;

import java.util.HashMap;
import java.util.Map;

public enum Tpp2Identifier {
    UNIT_1_POWER(619),
    UNIT_2_POWER(620),
    UNIT_3_POWER(621),
    UNIT_4_POWER(622),
    UNIT_5_POWER(623),
    UNIT_6_POWER(624),
    UNIT_7_POWER(625),
    UNIT_8_POWER(626);

    private int id;
    private static Map map = new HashMap<>();

    Tpp2Identifier(int id) {
        this.id = id;
    }

    static {
        for (Tpp2Identifier identifier : Tpp2Identifier.values()) {
            map.put(identifier.id, identifier);
        }
    }

    public static Tpp2Identifier valueOf(int identifier) {
        return (Tpp2Identifier) map.get(identifier);
    }

    public int getId() {
        return id;
    }
}
