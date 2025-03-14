package org.example.domainModel;

public class LightPoint {
    public static final int MAX_INTENSITY = 100;
    private boolean isVisible;
    private int intensity;

    public LightPoint() {
        this.isVisible = false;
        this.intensity = 0;
    }

    public void appear() {
        if (!isVisible) {
            isVisible = true;
            intensity = MAX_INTENSITY;
        } else {
            throw new IllegalStateException("LightPoint already appeared");
        }
    }

    public void spread() {
        if (isVisible && intensity > 0) {
            intensity -= 10;
            if (intensity == 0) {
                isVisible = false;
            }
        } else {
            throw new IllegalStateException("LightPoint not visible");
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public int getIntensity() {
        return intensity;
    }
}
