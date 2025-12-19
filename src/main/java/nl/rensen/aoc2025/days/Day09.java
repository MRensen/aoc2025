package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day09 implements Day<Double, String> {
    @Override
    public Double part1(List<String> input) {
        List<Coord> coords = new ArrayList<>();
        for(String s : input) {
            String[] split = s.split(",");
            coords.add(new Coord(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        Coord biggest1 = null;
        Coord biggest2 = null;
        for(Coord coord : coords) {
            for(Coord coord2 : coords) {
                if(biggest1 == null || biggest2 == null) {
                    biggest1 = coord;
                    biggest2 = coord2;
                }
                else if(size(coord, coord2) > size(biggest1, biggest2)){
                    biggest1 = coord;
                    biggest2 = coord2;
                }
            }
        }
        return calcArea(biggest1, biggest2);
    }

    private Double calcArea(Coord biggest1, Coord biggest2) {
        double x = Math.pow(Math.abs(biggest1.x - biggest2.x) + 1, 2);
        double y = Math.pow(Math.abs(biggest1.y - biggest2.y) + 1, 2);
        return Math.sqrt(x * y);
    }

    public Double size(Coord i, Coord j){
        return Math.pow(i.x-j.x, 2) + Math.pow(i.y - j.y, 2);
    }

    @Override
    public Double part2(List<String> input) {
        return 0.0;
    }
}
