package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

record HighestCount (char highest, Integer index){}

public class Day03 implements Day<Integer, String> {
    @Override
    public Integer part1(List<String> input) {
        int sum = 0;
        for(String bank : input){
            char[] characters = bank.toCharArray();
            List<Integer> combos = new ArrayList<>();
//            HighestCount[] highest = new HighestCount[2];
            char highest;
            for(int i = 0; i < bank.length(); i++){
//                highest[0] = new HighestCount(characters[i], i);
                highest = characters[i];
                for(int j = i+1; j < bank.length(); j++){
                    combos.add(Integer.valueOf(highest + "" + characters[j]));
                }

            }
//            System.out.println(Collections.max(combos));
            sum += Collections.max(combos);
        }

      return sum;
    }

    @Override
    public Integer part2(List<String> input) {
       return null;
    }

}
