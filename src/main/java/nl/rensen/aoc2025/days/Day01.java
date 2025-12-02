package nl.rensen.aoc2025.days;


import nl.rensen.aoc2025.common.Day;

import java.util.List;

public class Day01 implements Day<Integer> {
    int MAX_DIAL = 99;
    int MIN_DIAL = 0;

    @Override
    public Integer part1(List<String> input) {
        int current_dial = 50;
        int count_zeroes = 0;
        for (String dir : input){

//            splits de string L65 in L en 65
            String letter = dir.substring(0, 1);
            Integer number = Integer.valueOf(dir.substring(1));

//            Draai de "dial" terug, rekening houden met een "wrap around"
            if(letter.equalsIgnoreCase("L")){
                current_dial = ((current_dial - number) % MAX_DIAL + MAX_DIAL) % MAX_DIAL;
            }
            if(letter.equalsIgnoreCase("R")){
                current_dial = (current_dial + number) % MAX_DIAL;
            }

//            Als de dial op 0 staat, tel dat an bij de counter op.
            if(current_dial == 0){
                count_zeroes++;
            }
        }
        return count_zeroes; // return de counter
    }


    @Override
    public Integer part2(List<String> input) {
        System.out.println("test");
        int current_dial = 50;
        int count_zeroes = 0;
        for (String dir : input){
            String letter = dir.substring(0, 1);
            Integer number = Integer.valueOf(dir.substring(1));

            int previous_dial = current_dial; // houd de vorige stand van de "dial" bij

            while(number>0){
                if(letter.equalsIgnoreCase("l")){
                    if(current_dial == 0){
                        current_dial = 99;
                    } else {
                        current_dial -= 1;
                    }
                }
                if(letter.equalsIgnoreCase("r")){
                    if (current_dial == 99){
                        current_dial = 0;
                    } else {
                        current_dial += 1;
                    }
                }
                if(current_dial == 0){
                    count_zeroes += 1;
                }
                number -= 1;

            }
        }
        return count_zeroes;
    }


}

