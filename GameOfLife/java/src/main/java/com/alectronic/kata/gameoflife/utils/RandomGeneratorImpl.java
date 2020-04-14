package com.alectronic.kata.gameoflife.utils;

public class RandomGeneratorImpl implements RandomGenerator {

    @Override
    public boolean generateBoolean() {
        return Math.random() < 0.2;
    }

}
