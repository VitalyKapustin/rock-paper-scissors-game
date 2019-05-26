package com.vlkxcool.rps.type;

import java.util.Arrays;

public enum HandShape {
    ROCK("r"), PAPER("p"), SCISSORS("s");

    private String alias;
    private HandShape losesTo;

    HandShape(String alias) {
        this.alias = alias;
    }

    static {
        ROCK.losesTo = PAPER;
        PAPER.losesTo = SCISSORS;
        SCISSORS.losesTo = ROCK;
    }

    public static int getShapesAmount() {
        return values().length;
    }

    public static HandShape getByAlias(String alias) {
        return Arrays.stream(values())
                .filter(handShape -> alias.equals(handShape.alias))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Handshape with specified alias not found"));
    }

    public static HandShape getByIndex(int index) {
        return values()[index];
    }

    public boolean isLosesTo(HandShape handShape) {
        return losesTo == handShape;
    }

    public HandShape getLosesTo() {
        return losesTo;
    }
}
