package com.sentiment.trial.Analysis;

public class HeatMap {

    public double[][] cells;
    public boolean valid;

    public HeatMap(boolean valid) {
        this.cells = new double[7][24];
        this.valid = valid;
    }

}
