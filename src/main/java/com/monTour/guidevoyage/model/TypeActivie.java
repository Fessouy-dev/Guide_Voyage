package com.monTour.guidevoyage.model;

public enum TypeActivie {
    Musee, Excursion, Jardin, Mosque, Cathedral;

    public static TypeActivie fromValue(String value) {
        if (value == null) {
            return null;
        }
        
        // Gestion des valeurs numériques
        if (value.equals("1")) {
            return Musee;
        }
        
        try {
            return valueOf(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
} 