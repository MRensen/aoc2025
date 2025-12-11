package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;
import nl.rensen.aoc2025.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day06Test {


    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(6);

    @Test
    public void part1() {
        Day<Long, String> day = new Day06();
        assertEquals(Long.valueOf(7098065460541L), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Long, String> day = new Day06();
        assertEquals(Long.valueOf(13807151830618L), day.part2(input.getLines()));
//        9909491 -> too low
//        13846417711401 -> too high
    }
}