package com.test.aequilibrium.Helper;

import java.util.stream.Stream;

public enum WinnersEnum {
    OPTIMUS_PRIME("Optimus Prime"),
    PREDAKING("Predaking");

    private String name;

    WinnersEnum(String name) {
        this.name = name;
    }

    public static boolean isWinner(String name){
        return Stream.of(WinnersEnum.values()).anyMatch(winner -> winner.name.equalsIgnoreCase(name));
    }
}
