package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;
import nl.rensen.aoc2025.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day09Test {


    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(9);

    @Test
    public void part1() {
        Day<Double, String> day = new Day09();
        assertEquals(Double.valueOf(50), day.part1(input.getLines()));
    }
//    476541161 too low

    @Test
    public void part2() {
        Day<Double, String> day = new Day09();
        assertEquals(Double.valueOf(0), day.part2(input.getLines()));
    }
}