package com.tesnick.trading.utils;

import org.junit.Assert;
import org.junit.Test;

public class CrossFinderTest {

    private CrossFinder crossFinder = new CrossFinder();

    @Test
    public void crossFinderPositiveTest() {

        double oldValue1 = 23;
        double oldValue2 = 21;

        double newValue1 = 22.5;
        double newValue2 = 43;

        Assert.assertTrue(crossFinder.areCrossed(oldValue1, oldValue2,
                newValue1, newValue2));

    }

    @Test
    public void crossFinderNegativeTest() {

        double oldValue1 = 23;
        double oldValue2 = 21;

        double newValue1 = 22;
        double newValue2 = 21.5;

        Assert.assertFalse(crossFinder.areCrossed(oldValue1, oldValue2,
                newValue1, newValue2));

    }

}
