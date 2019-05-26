package com.vlkxcool.rps.type;

public enum RoundResult {
    HUMAN_WON("==> You win!"),
    COMPUTER_WON("==> You loose!"),
    DRAW("==> Draw!");

    private String message;

    RoundResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
