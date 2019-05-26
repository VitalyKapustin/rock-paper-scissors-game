package com.vlkxcool.rps.predictors;

import com.vlkxcool.rps.type.HandShape;

public interface MovePredictor {

    HandShape getNextMove(HandShape previousHumanHandShape);
}
