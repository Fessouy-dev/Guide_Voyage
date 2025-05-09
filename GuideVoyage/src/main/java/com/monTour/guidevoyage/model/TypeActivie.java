package com.monTour.guidevoyage.model;

public enum TypeActivie {
    Musee,
    Excursion,
    Jardin,
    Mosque,
    Cathedral,
    Artisinal,
    Gastronomique;

    public static TypeActivie fromValue(String value) {
        try {
            return valueOf(value);
        } catch (IllegalArgumentException e) {
            // Handle numeric values
            switch (value) {
                case "0": return Musee;
                case "1": return Excursion;
                case "2": return Jardin;
                case "3": return Mosque;
                case "4": return Cathedral;
                case "5": return Artisinal;
                case "6": return Gastronomique;
                default: throw new IllegalArgumentException("Invalid TypeActivie value: " + value);
            }
        }
    }
}
