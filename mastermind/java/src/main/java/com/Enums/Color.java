package com.enums;

import java.util.Random;

public enum Color {
    BLEU("B"),
    JAUNE("J"),
    NOIR("N"),
    ORANGE("O"),
    ROUGE("R"),
    VERT("V");

    private final String value;
    private static Color[] vals = values();

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Color next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public static Color getRandomColor() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
