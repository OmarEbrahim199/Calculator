package org.example;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @org.junit.jupiter.api.Test
    void handleAnUnknownAmountOfNumbers() {
        var sc = new StringCalculator();
        assertEquals(5,sc.handleAnUnknownAmountOfNumbers("2,3"));


    }
    @org.junit.jupiter.api.Test
    void HandleNewLinesBetweenNumbersInsteadOfCommas() {
        var sc = new StringCalculator();
        assertEquals(24,sc.HandleNewLinesBetweenNumbersInsteadOfCommas("3,6n15"));
        System.out.println(sc.HandleNewLinesBetweenNumbersInsteadOfCommas("3,6n15"));

    }

    @org.junit.jupiter.api.Test
    void SupportDifferentDelimiters() {
        var sc = new StringCalculator();
        Assert.assertEquals(3+6+15, sc.SupportDifferentDelimiters("//;n3;6;15"));

    }

    @org.junit.jupiter.api.Test
    public final void whenNegativeNumberIsUsedThenRuntimeExceptionIsThrown() {
        var sc = new StringCalculator();
        sc.NegativeNumbersWillThrowNnException("3,-6,15,18,46,33","2");
    }
    @org.junit.jupiter.api.Test
    public final void whenNegativeNumbersAreUsedThenRuntimeExceptionIsThrown() {
        RuntimeException exception = null;
        try {
            var sc = new StringCalculator();
            sc.NegativeNumbersWillThrowNnException("3,-6,15,-18,46,33","-2");
        } catch (RuntimeException e) {
            exception = e;
            System.out.println(exception);
        }
        Assert.assertNotNull(exception);
        Assert.assertEquals("Negatives not allowed: [-6, -18]", exception.getMessage());
    }

}