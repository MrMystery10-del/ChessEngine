package bots.intermediate_bots_500_1000;

import bots.Bot;

@SuppressWarnings("unused")
public class Ava implements Bot {

    private static final int elo = 500;
    private static final String name = "Ava";
    private static final String description = "No description currently";
    private static final String algorithm = null;

    @Override
    public int getElo() {
        return elo;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUsedAlgorithm() {
        return algorithm;
    }
}
