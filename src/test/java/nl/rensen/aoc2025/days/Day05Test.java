package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;
import nl.rensen.aoc2025.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {


    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(5);

    @Test
    public void part1() {
        Day<Integer, String> day = new Day05();
        assertEquals(Integer.valueOf(3), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer, String> day = new Day05();
        assertEquals(Long.valueOf(356509329271283L), day.part2(input.getLines()));
    }
}