package com.pahimar.chitchat.reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reference {

    /* Equivalent character map for common character substitutions */
    private static Map<String, List<String>> equivalentCharacterMap;

    /**
     * Returns the map of equivalent characters
     *
     * @return A mapping of alphabetical letters and their commonly substituted characters
     */
    public static Map<String, List<String>> getEquivalentCharacterMap() {

        if (equivalentCharacterMap == null) {
            equivalentCharacterMap = new HashMap<>();
            initEquivalentCharacterMaps();
        }

        return equivalentCharacterMap;
    }

    /**
     * Initializes the equivalent character map, mapping common letter substitutions to letters of the alphabet
     */
    private static void initEquivalentCharacterMaps() {

        Reference.equivalentCharacterMap.put("a", Arrays.asList("@", "4", "æ", "à", "á", "â", "â", "ã", "ä", "å", "À", "Á", "Â", "Ã", "Ä", "Å", "Æ", "ª"));
        Reference.equivalentCharacterMap.put("b", Arrays.asList("8", "ß", "Þ", "þ"));
        Reference.equivalentCharacterMap.put("c", Arrays.asList("ç", "Ç", "©", "¢", "Œ"));
        Reference.equivalentCharacterMap.put("d", Arrays.asList("Ð"));
        Reference.equivalentCharacterMap.put("e", Arrays.asList("3", "æ", "è", "é", "ê", "ë", "€", "È", "É", "Ê", "Ë", "Æ", "Œ", "œ"));
        Reference.equivalentCharacterMap.put("f", Arrays.asList("ƒ"));
        Reference.equivalentCharacterMap.put("g", null);
        Reference.equivalentCharacterMap.put("h", null);
        Reference.equivalentCharacterMap.put("i", Arrays.asList("!", "l", "1", "ì", "í", "î", "ï", "Ì", "Í", "Î", "Ï", "¡"));
        Reference.equivalentCharacterMap.put("j", null);
        Reference.equivalentCharacterMap.put("k", null);
        Reference.equivalentCharacterMap.put("l", Arrays.asList("1", "£"));
        Reference.equivalentCharacterMap.put("m", null);
        Reference.equivalentCharacterMap.put("n", Arrays.asList("ñ", "Ñ"));
        Reference.equivalentCharacterMap.put("o", Arrays.asList("0", "ð", "ò", "ó", "ô", "õ", "ö", "ø", "Ò", "Ó", "Ô", "Õ", "Ö", "Ø", "œ"));
        Reference.equivalentCharacterMap.put("p", Arrays.asList("Þ", "þ"));
        Reference.equivalentCharacterMap.put("q", null);
        Reference.equivalentCharacterMap.put("r", Arrays.asList("®"));
        Reference.equivalentCharacterMap.put("s", Arrays.asList("5", "$", "z", "Š", "š", "§"));
        Reference.equivalentCharacterMap.put("t", Arrays.asList("+", "7", "†"));
        Reference.equivalentCharacterMap.put("u", Arrays.asList("ù", "ú", "û", "ü", "Ù", "Ú", "Û", "Ü"));
        Reference.equivalentCharacterMap.put("v", null);
        Reference.equivalentCharacterMap.put("w", null);
        Reference.equivalentCharacterMap.put("x", Arrays.asList("×"));
        Reference.equivalentCharacterMap.put("y", Arrays.asList("ý", "ÿ", "Ý", "¥", "Ÿ"));
        Reference.equivalentCharacterMap.put("z", Arrays.asList("2", "s", "Ž", "ž"));
    }
}
