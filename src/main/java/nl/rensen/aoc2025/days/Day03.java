package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day03 implements Day<Long, String> {
    @Override
    public Long part1(List<String> input) {
        int sum = 0;
        for(String bank : input){ //The input is a list of "power banks"
            char[] characters = bank.toCharArray(); //Split the "powerbanks" into "jolts"
            List<Integer> combos = new ArrayList<>(); //accumulator
            char highest;
            //For every jolt, look with which other jolts you can make a combo.
            for(int i = 0; i < bank.length(); i++){
                highest = characters[i];
                for(int j = i+1; j < bank.length(); j++){
                    combos.add(Integer.valueOf(highest + "" + characters[j]));
                }

            }
            sum += Collections.max(combos); // add together the most valuable jolt-combo of each bank
        }

      return Integer.toUnsignedLong(sum);
    }

    @Override
    public Long part2(List<String> input) {
        long sum = 0;
        for(String bank : input){ //The input is a list of "power banks"
            char[] characters = bank.toCharArray(); //Split the "powerbanks" into "jolts"
            List<Character> combos = new ArrayList<>(); //accumulator
            for(int i = bank.length()-1; i >= 0; i--){
//                Zet de laatste 12 cijfers in de acumulator (combos)
                if(combos.size()<12){
                    combos.add(characters[i]);
                } else {
//                    Zet de huidige 12 om in een Long waarde
                    Long oldJoltage = Long.parseLong(combos.reversed().stream().map(String::valueOf).collect(Collectors.joining()));
//                    Maake alle 12 mogelijke variaties van "nieuwe jolt" + "11 van de 12 oude jolts" (want de volgorde mag niet wijzigen)
//                    Dat wordt dan zoiets (n is de nieuwe jolt):  [n234567890, n134567890, n124567890, n123567890, etc...]
                    Long[] newJoltages = new Long[12];
                    for(int j = 0; j < 12; j++){
                        newJoltages[j] = Long.parseLong(characters[i] + combos.reversed().subList(0,j).stream().map(String::valueOf).collect(Collectors.joining()) + combos.reversed().subList(j+1,combos.size()).stream().map(String::valueOf).collect(Collectors.joining()));
                    }

//                    Haal vervolgens de max waarde uit de mogelijke Joltages
                    Long max = Arrays.stream(newJoltages).max(Long::compare).orElseThrow();

//                    Als de max waarde van de nieuwe Joltages groter is dan de oude waarde, dan zet je het weer terug naar een List<Character> en vervang je die.
                    if(max > oldJoltage){
                        combos = max.toString().chars().mapToObj(c -> (char)c).collect(Collectors.toList()).reversed();
                    }

                }

            }
//            Uiteindelijk moet je alle definitieve List<Character> waardes omzetten naar Long en bij elkaar optellen
            StringBuilder builder = new StringBuilder();
            for(int j = combos.size()-1; j >= 0; j--){
                builder.append(combos.get(j));
            }
            sum += Long.parseLong(builder.toString());
        }

        return sum;
    }

}
