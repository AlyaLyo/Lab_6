package City.Fields;

/**
 * Enum хранящий значения климата.
 */

public enum Climate {
    RAIN_FOREST, TROPICAL_SAVANNA, HUMIDSUBTROPICAL, STEPPE;

    public static Climate parse(String string){
        switch (string.trim().toUpperCase()) {
            case "RAIN_FOREST":
                return RAIN_FOREST;
            case "TROPICAL_SAVANNA":
                return TROPICAL_SAVANNA;
            case "HUMIDSUBTROPICAL":
                return HUMIDSUBTROPICAL;
            case "STEPPE":
                return STEPPE;
        }
        return null;
    }
}
