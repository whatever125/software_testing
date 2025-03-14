package org.example.domainModel;

public class Moon extends LightPoint {
    public enum Phase {
        NEW_MOON,
        GROWING_CRESCENT,
        FULL_MOON,
        SHRINKING_CRESCENT,
    }

    private Phase currentPhase;

    public Moon() {
        super();
        this.currentPhase = Phase.NEW_MOON;
    }

    public Phase getPhase() {
        return this.currentPhase;
    }

    public void nextPhase() {
        switch (currentPhase) {
            case NEW_MOON:
                currentPhase = Phase.GROWING_CRESCENT;
                break;
            case GROWING_CRESCENT:
                currentPhase = Phase.FULL_MOON;
                break;
            case FULL_MOON:
                currentPhase = Phase.SHRINKING_CRESCENT;
                break;
            case SHRINKING_CRESCENT:
                currentPhase = Phase.NEW_MOON;
                break;
        }
    }
}
