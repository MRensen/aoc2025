package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;
import nl.rensen.aoc2025.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day01Test {

    /*
     * safe
     * dial
     * numbers 0-99
     * click when number reached
     * input: rotations
     * L (lower) R (higher)
     */

    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(1);

    @Test
    public void part1() {
        Day<Integer> day = new Day01();
        assertEquals(Integer.valueOf(3), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day01();
        assertEquals(Integer.valueOf(6412), day.part2(input.getLines()));
    }
}