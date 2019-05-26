package com.vlkxcool.rps.predictors;

import com.vlkxcool.rps.type.HandShape;

public class MarkovChainMovePredictor implements MovePredictor {

    private int previousHandShapeIndex = -1;
    private int[][] markovChain;

    public MarkovChainMovePredictor() {
        int choiceAmount = HandShape.getShapesAmount();
        markovChain = new int[choiceAmount][choiceAmount];
    }

    @Override
    public HandShape getNextMove(HandShape previousHumanHandShape) {
        updateMarkovChain(previousHumanHandShape);

        HandShape predictedNextHumanHandShape = getNextHumanHandShape(previousHumanHandShape);

        return predictedNextHumanHandShape.getLosesTo();
    }

    private void updateMarkovChain(HandShape previousHumanHandShape) {
        int nextHandShapeIndex = previousHumanHandShape.ordinal();
        if (previousHandShapeIndex != -1) {
            markovChain[previousHandShapeIndex][nextHandShapeIndex]++;
        }
        previousHandShapeIndex = nextHandShapeIndex;
    }

    private HandShape getNextHumanHandShape(HandShape previousHandShape) {
        int nextHandShapeIndex = getNextHumanHandShapeIndex(previousHandShape);
        return HandShape.getByIndex(nextHandShapeIndex);
    }

    private int getNextHumanHandShapeIndex(HandShape previousHandShape) {
        int previousHandShapeIndex = previousHandShape.ordinal();
        int nextIndex = 0;
        for (int i = 0; i < HandShape.values().length; i++) {
            if (markovChain[previousHandShapeIndex][i] > markovChain[previousHandShapeIndex][nextIndex]) {
                nextIndex = i;
            }
        }
        return nextIndex;
    }
}
