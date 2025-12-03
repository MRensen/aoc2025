package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;
import nl.rensen.aoc2025.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03Test {


    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(3);

    @Test
    public void part1() {
        Day<Long, String> day = new Day03();
        assertEquals(Long.valueOf(17278), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Long, String> day = new Day03();
        assertEquals(Long.valueOf(171528556468625L), day.part2(input.getLines()));
    }
}