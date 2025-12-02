package nl.rensen.aoc2025.common;

import java.util.List;

public interface Day<T, I> {
    T part1(List<I> input);
    T part2(List<I> input);
}
