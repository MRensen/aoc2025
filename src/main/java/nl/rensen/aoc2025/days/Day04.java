package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04 implements Day<Integer, String> {
    int[][] dirs = {{-1,-1}, {-1,0}, {-1,1}, {0,-1},{0,1},{1,-1},{1,0},{1,1}};

    @Override
    public Integer part1(List<String> input) {
        int counter = 0;
//        transformeer de "List<String>" in een "String[][]", zodat je er makkelijker doorheen kunt loopen.
        String[][] grid = new String[input.size()][input.getFirst().length()];
        for(int row = 0; row < input.size(); row++){
            String[] chars = input.get(row).split("");
            for(int col = 0; col < input.get(row).length(); col++) {
                grid[row][col] = chars[col];
            }
        }

//        Loop door het grid en kijk voor elke "roll" of deze minder dan 4 buren heeft.
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                String current = grid[row][col];
                if(!grid[row][col].equals("@")){
                    continue;
                }
                List<String> neighbours = new ArrayList<>();
//                Loop door de dirs (een lijst met relatieve coordinaten voor alle buren) en voeg deze aan "neighbours" toe als het binnen het "grid" valt.
                for(int[] dir : dirs){
                    int newrow = row+dir[0];
                    int newcol = col+dir[1];
                    if(newrow>=0 && newrow< grid.length && newcol>=0 && newcol< grid[row].length){
                        String neigbour = grid[newrow][newcol];
//                        Voeg alleen de "neighbour" toe als dit een "roll" (@) is
                        if(neigbour.equals("@")) {
                            neighbours.add(neigbour);
                        }
                    }
                }
//                Als dit item minder dan 4 roll-neighbours heeft, dan mag de counter met 1 omhoog
                if(neighbours.size()<4){
                    counter+=1;
                }
            }
        }
        return counter;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }

}
