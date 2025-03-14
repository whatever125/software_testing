package org.example.domainModel;

public class Atmosphere {
    private int colorIntensity;

    public Atmosphere() {
        this.colorIntensity = 0;
    }

    public void addColorSplashes() {
        colorIntensity += 10;
    }

    public int getColorIntensity() {
        return colorIntensity;
    }
}
