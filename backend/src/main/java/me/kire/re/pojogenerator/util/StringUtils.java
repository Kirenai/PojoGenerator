package me.kire.re.pojogenerator.util;

import static me.kire.re.pojogenerator.util.Constants.ARRAY_BODY;
import static me.kire.re.pojogenerator.util.Constants.OUT_CONCAT;

public class StringUtils {
    public static String removeArrayFormat(String name) {
        // TODO: Delete this if later
        if (name.endsWith(OUT_CONCAT)) {
            return name.substring(0, name.indexOf(ARRAY_BODY)) + name.substring(name.length() - OUT_CONCAT.length());
        }
        if (name.endsWith(ARRAY_BODY)) {
            return name.substring(0, name.length() - ARRAY_BODY.length());
        }
        return name;
    }
}
