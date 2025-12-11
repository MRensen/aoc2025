package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;

import java.util.*;

public class Day06 implements Day<Long, String> {


    @Override
    public Long part1(List<String> input) {
        List<List<Integer>> cols = new ArrayList<>();
        List<String> ops = new ArrayList<>();

//        parse de input in rows
        for (String s : input) {
            List<Integer> row = new ArrayList<>();
            for(String num : s.split(" ")) {
//                System.out.println("|"+num+"|");
                if(num.isEmpty()) continue;
                if(num.strip().equals("+") || num.strip().equals("*")){
                    ops.add(num);
                } else {
                    row.add(Integer.parseInt(num.strip()));
                }
            }
            if(!row.isEmpty()) cols.add(row);
        }

//        Doe de sommen
        long count = 0;
        for(int i = 0; i < ops.size(); i++){
            long temp_count;
            if(ops.get(i).equals("+")){
                temp_count = 0;
            } else {
                temp_count = 1;
            }
            for( List<Integer> row : cols){
                if(ops.get(i).equals("+")){
                    temp_count += row.get(i);
                } else {
                    temp_count *= row.get(i);
                }
            }
            System.out.println(temp_count);
            count += temp_count;
        }


        return count;
    }

    @Override
    public Long part2(List<String> input) {
//        List<List<List<Integer>>> cols = new ArrayList<>();
        List<String> ops = new ArrayList<>();
        List<String[]> allCols = new ArrayList<>();
        int size = input.size();
        String[][] cols = new String[size][input.getFirst().length()];

//        Split alle nummer (en spaties)
        for(int i = 0; i < size; i++){
            cols[i] = input.get(i).split("");
        }
//        Vervang alle spatie-kolommen met @
        for(int i = 0; i < input.getFirst().length(); i++){
            int space = 0;
            for(int j = 0; j < size; j++){
                if(cols[j][i].equals(" ")){
                    space++;
                }
            }
            if(space == size){
                for(int j = 0; j < size; j++){
                    cols[j][i] = "@";
                }
            }
        }

//        Join de nummers, inclusief spaties.
        for(String[] row : cols){
            StringBuilder builder = new StringBuilder();
            for(String s : row){
                builder.append(s);
            }
            allCols.add(builder.toString().split("@"));
        }

        long sum = 0;
//        Doe de sommen voor alle rijen
        for(int i = 0; i < allCols.getFirst().length; i++){
            System.out.println(i);
            long count;
//            System.out.println(allCols.getLast()[i].strip());
            String operator = allCols.getLast()[i].substring(0,1);
            if(operator.equals("+")){
                count = 0;
            } else {
                count = 1;
            }

            String[] found_cols = new String[size-1];
            for(int j = 0; j < size-1; j++) {
//                System.out.println("|"+number+"|");
                found_cols[j] = allCols.get(j)[i];
            }

            for(int k = 0; k < found_cols[0].length(); k++) {
                StringBuilder stringToNumber = new StringBuilder();
                for(String number : found_cols) {
                    String stringNumber = number.substring(k, k + 1);
                    System.out.println(number + " - " + stringNumber + " (" + operator + ")");
                    if (stringNumber.equals(" ")) continue;
                    stringToNumber.append(stringNumber);
                }
                int intNumber = Integer.parseInt(stringToNumber.toString());
                if (operator.equals("+")) {
                    count += intNumber;
                } else {
                    count *= intNumber;
                }

                System.out.println("count: " + count);
            }
            sum += count;
        }

        return sum;
    }
}
