package nl.rensen.aoc2025.days;

import com.google.common.collect.Range;
import nl.rensen.aoc2025.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day02 implements Day<Long, String> {
    @Override
    public Long part1(List<String> input) {
        List<String> results = new ArrayList<>();
        String[] raw_ranges = input.get(0).split(",");
        for (String string_range : raw_ranges){
            String[] begin_end = string_range.split("-");
            LongStream.rangeClosed(Long.parseLong(begin_end[0]), Long.parseLong(begin_end[1])).forEach((el)->{
                String string_value = String.valueOf(el);
                String second_half = string_value.substring(string_value.length()/2);
                if(string_value.length()%2==0 && string_value.startsWith(second_half)){
                    results.add(string_value);
                }

            });
        }

        Long result = 0L;
        for(String id : results){
//            System.out.println(id);
            result += Long.parseLong(id);
        }
        return result;
    }

    @Override
    public Long part2(List<String> input) {
        return null;
    }
}
