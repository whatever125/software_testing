package org.example;

import org.example.domainModel.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DomainModelTest {
    private Moon moon;
    private Sun sun1;
    private Sun sun2;
    private Horizon horizon;
    private Atmosphere atmosphere;

    @Before
    public void setUp() {
        moon = new Moon();
        sun1 = new Sun();
        sun2 = new Sun();
        horizon = new Horizon();
        atmosphere = new Atmosphere();
    }

    @Test
    public void scriptTest() {
        // В полной темноте сверкнула ослепительно яркая точка света
        moon.appear();
        assertTrue(moon.isVisible());
        assertEquals(moon.getIntensity(), Moon.MAX_INTENSITY);

        // Она начала расползаться в стороны
        moon.spread();
        assertTrue(moon.getIntensity() < Moon.MAX_INTENSITY);

        // превращаясь в узкий полумесяц
        while (moon.getPhase() != Moon.Phase.GROWING_CRESCENT) {
            moon.nextPhase();
        }
        assertEquals(moon.getPhase(), Moon.Phase.GROWING_CRESCENT);

        // через несколько секунд показались два солнца
        sun1.rise();
        assertTrue(sun1.isRisen());
        sun2.rise();
        assertTrue(sun2.isRisen());

        // огненные светила, сжигающие белым пламенем черный край горизонта
        sun1.burnHorizon(horizon);
        sun2.burnHorizon(horizon);
        assertTrue(horizon.getBlackness() < Horizon.MAX_BLACKNESS);

        // Яркие цветные сполохи струились сквозь разреженную атмосферу
        sun1.colorAtmosphere(atmosphere);
        sun2.colorAtmosphere(atmosphere);
        assertTrue(atmosphere.getColorIntensity() > 0);
    }

    @Test
    public void testLightPointAppear() {
        moon.appear();
        assertTrue(moon.isVisible());
        assertEquals(LightPoint.MAX_INTENSITY, moon.getIntensity());
    }

    @Test
    public void testLightPointSpread() {
        moon.appear();
        moon.spread();
        assertEquals(90, moon.getIntensity());
    }

    @Test
    public void testSunRise() {
        sun1.rise();
        sun2.rise();
        assertTrue(sun1.isRisen());
        assertTrue(sun2.isRisen());
    }

    @Test
    public void testHorizonLightUp() {
        horizon.brighten();
        assertTrue(horizon.getBlackness() < Horizon.MAX_BLACKNESS);
    }

    @Test
    public void testAtmosphereAddColorSplashes() {
        atmosphere.addColorSplashes();
        assertTrue(atmosphere.getColorIntensity() > 0);
    }

    @Test
    public void testLightPointSpreadNegative() {
        assertThrows(IllegalStateException.class, () -> sun1.spread());
    }

    @Test
    public void testSunRiseNegative() {
        sun1.rise();
        assertTrue(sun1.isRisen());
        assertThrows(IllegalStateException.class, () -> sun1.rise());
    }
}

