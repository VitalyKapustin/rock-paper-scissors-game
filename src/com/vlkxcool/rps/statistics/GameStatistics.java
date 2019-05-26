package com.vlkxcool.rps.statistics;

import com.vlkxcool.rps.type.RoundResult;

import java.text.DecimalFormat;

public class GameStatistics {

    private static DecimalFormat DECIMAL_FORMATTER = new DecimalFormat(".##");

    private int round;
    private int humanWins;
    private int computerWins;
    private int draws;

    public void update(RoundResult roundResult) {
        round++;
        switch (roundResult) {
            case HUMAN_WON: humanWins++; break;
            case COMPUTER_WON: computerWins++; break;
            case DRAW: draws++; break;
        }
    }

    @Override
    public String toString() {
        int totalScore = humanWins + computerWins + draws;
        return "GameStatistics{" +
                "round=" + round +
                ", humanWins=" + getScorePercentageAsString(humanWins, totalScore) +
                ", computerWins=" + getScorePercentageAsString(computerWins, totalScore) +
                ", draws=" + getScorePercentageAsString(draws, totalScore) +
                '}';
    }

    private String getScorePercentageAsString(int score, int totalScore) {
        return DECIMAL_FORMATTER.format(score / (float) totalScore * 100);
    }
}
