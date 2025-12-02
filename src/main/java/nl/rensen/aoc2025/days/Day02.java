package nl.rensen.aoc2025.days;

import com.google.common.collect.Range;
import nl.rensen.aoc2025.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
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
        List<String> results = new ArrayList<>();
        String[] raw_ranges = input.get(0).split(",");
        for (String string_range : raw_ranges){
            String[] begin_end = string_range.split("-");
            LongStream.rangeClosed(Long.parseLong(begin_end[0]), Long.parseLong(begin_end[1])).forEach((el)->{
                String string_value = String.valueOf(el);
                if(isSequence(string_value)){
                    results.add(string_value);
                }

            });
        }

        Long result = 0L;
        for(String id : results){
            result += Long.parseLong(id);
        }
        return result;
    }
    public boolean isSequence(String id){
        if(id.length()<2){
            return false;
        }
        StringBuilder acc = new StringBuilder();
            for(char c : id.toCharArray()) {
                acc.append(c);
//                https://www.baeldung.com/java-string-count-occurences-of-sequence
                int count = Math.toIntExact(Pattern.compile(Pattern.quote(acc.toString()))
                        .matcher(id)
                        .results()
                        .count()
                );
                if((count * acc.length()) == id.length()){
                    if(!acc.toString().equals(id)) {
                        return true;
                    }
                }
            }
        return false;
    }
}
