package org.example.domainModel;

public class Horizon {
    private int blackness;
    public static final int MAX_BLACKNESS = 100;

    public Horizon() {
        this.blackness = MAX_BLACKNESS;
    }

    public void brighten() {
        if (blackness > 0) {
            blackness -= 10;
        }
    }

    public void darken() {
        if (blackness < MAX_BLACKNESS) {
            blackness += 10;
        }
    }

    public int getBlackness() {
        return blackness;
    }
}

