package com.keerthi.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CountingValleysSolutionTest {

    @Test
    void countingValleys() {
        Assertions.assertEquals(1,CountingValleysSolution.countingValleys(8,"UDDDUDUU"));
    }

    @Test
    void countingValleysForMultiple() {
        Assertions.assertEquals(2,CountingValleysSolution.countingValleys(8,"UDDDUDUUDDUU"));
    }

    @Test
    void countingValleysForEmptyString() {
        Assertions.assertEquals(0,CountingValleysSolution.countingValleys(0,""));
    }

    @Test
    void countingValleysForNoValleys() {
        Assertions.assertEquals(0,CountingValleysSolution.countingValleys(0,"UUUDDD"));
    }

    @Test
    void countingValleysForInvalidInput() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> CountingValleysSolution.countingValleys(8, "UUUDDDU"));
        String expectedMessage = "Invalid Hike path";
        Assertions.assertEquals(expectedMessage,exception.getMessage());
    }
}