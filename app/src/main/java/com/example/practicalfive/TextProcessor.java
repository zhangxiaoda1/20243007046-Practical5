package com.example.practicalfive;

import java.util.Locale;

/**
 * A regular Java class (no Activity, no layout) that validates and
 * normalises user-entered municipality names.
 */
public class TextProcessor {

    /**
     * Validate and normalise a municipality name (case-insensitive).
     *
     * @param municipality the raw text entered by the user
     * @return "Beijing", "Shanghai", "Tianjin", or "Chongqing" for valid input;
     *         null for null, blank, or unsupported input
     */
    public String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }
        switch (municipality.trim().toLowerCase(Locale.ROOT)) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null;
        }
    }
}
