package org.example.domainModel;

public class Sun extends LightPoint {
    private boolean isRisen;

    public Sun() {
        super();
        this.isRisen = false;
    }

    public void rise() {
        if (!isRisen) {
            super.appear();
            isRisen = true;
        } else {
            throw new IllegalStateException("Sun already risen");
        }
    }

    public void setDown() {
        if (isRisen) {
            while (super.getIntensity() > 0) {
                super.spread();
            }
            isRisen = false;
        } else {
            throw new IllegalStateException("Sun not risen");
        }
    }

    public void burnHorizon(Horizon horizon) {
        if (horizon == null) {
            throw new NullPointerException("Horizon cannot be null");
        }
        horizon.brighten();
    }

    public void colorAtmosphere(Atmosphere atmosphere) {
        if (atmosphere == null) {
            throw new NullPointerException("Atmosphere cannot be null");
        }
        atmosphere.addColorSplashes();
    }

    public boolean isRisen() {
        return isRisen;
    }
}
