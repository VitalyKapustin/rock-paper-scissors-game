package com.vlkxcool.rps;

import com.vlkxcool.rps.predictors.MarkovChainMovePredictor;
import com.vlkxcool.rps.predictors.MovePredictor;
import com.vlkxcool.rps.statistics.GameStatistics;
import com.vlkxcool.rps.type.HandShape;
import com.vlkxcool.rps.type.RoundResult;

import java.util.Random;

class RpsGame {

    private GameTerminal gameTerminal = new GameTerminal();
    private MovePredictor movePredictor = new MarkovChainMovePredictor();
    private GameStatistics gameStatistics = new GameStatistics();
    private HandShape previousHumanChoice = null;

    void play() {
        showGameDescription();
        gameTerminal.observeHumanCommands(this::processHumanChoice);
    }

    private void showGameDescription() {
        gameTerminal.showMessage("Rock, paper and scissors game\n"
                + "Below the commands for the game:\n"
                + "r - rock\n"
                + "p - paper\n"
                + "s - scissors\n"
                + "q - quit the game");
    }

    private void processHumanChoice(HandShape humanChoice) {
        HandShape computerChoice = getNextMove(previousHumanChoice);
        gameTerminal.showMessage("Computer choice: " + computerChoice);

        RoundResult roundResult = determineRoundResult(humanChoice, computerChoice);
        gameStatistics.update(roundResult);
        showRoundInfoAndStatistics(roundResult);

        previousHumanChoice = humanChoice;
    }

    private HandShape getNextMove(HandShape previousHumanChoice) {
        return previousHumanChoice != null
                ? movePredictor.getNextMove(previousHumanChoice)
                : getFirstMove();
    }

    private HandShape getFirstMove() {
        int choiceAmount = HandShape.getShapesAmount();
        int nextChoiceIndex = new Random().nextInt(choiceAmount);
        return HandShape.getByIndex(nextChoiceIndex);
    }

    private RoundResult determineRoundResult(HandShape humanChoice, HandShape computerChoice) {
        RoundResult roundResult;
        if (computerChoice.equals(humanChoice)) {
            roundResult = RoundResult.DRAW;
        } else if (computerChoice.isLosesTo(humanChoice)) {
            roundResult = RoundResult.HUMAN_WON;
        } else {
            roundResult = RoundResult.COMPUTER_WON;
        }
        return roundResult;
    }

    private void showRoundInfoAndStatistics(RoundResult roundResult) {
        gameTerminal.showMessage(roundResult.getMessage());
        gameTerminal.showMessage(gameStatistics.toString() + "\n");
    }
}
