package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;
import nl.rensen.aoc2025.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {


    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(2);

    @Test
    public void part1() {
        Day<Long, String> day = new Day02();
        assertEquals(Long.valueOf(0), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Long, String> day = new Day02();
        assertEquals(Long.valueOf(0), day.part2(input.getLines()));
    }
}