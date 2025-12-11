package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;

import java.util.*;

public class Day05 implements Day<Integer, String> {

    public class Range {
        long min;
        long max;

        public Range(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }
    public class RangeChecker{
        List<Range> ranges;
        public RangeChecker() {
            ranges = new ArrayList<>();
        }
        public RangeChecker(List<Range> ranges) {
            this.ranges = ranges;
        }

        public void add(long min, long max){
            ranges.add(new Range(min, max));
        }

        /**
         * Deze funtie berekent of een id in een van de +- 200 ranges valt.
         * Dat geeft 200 berekeningen per id (ongeveer 1000).
         * Dat is ongeveer 200.000 berekeningen dus en anders minstens 429.183.984.747.005 om alleen alle individuele ranges te berekenen.
         * Dan kun je welliswaar simpelweg een "get" doen om te checken of een id "fresh" is, maar het staat niet in verhouding.
         * @param id
         * @return
         */
        public boolean isFresh(long id){
            for (Range r : ranges){
                if (r.min <= id && id <= r.max){
                    return true;
                }
            }
            return false; // als er geen range is gevonden waar de id in valt.
        }

        /**
         * Deze functie telt alle unieke ranges (dus zonder overlap) voor part2
         */
        public long countUniqueRanges(){
            List<Range> uniqueRanges = new ArrayList<>();
            for (Range r : ranges){
                boolean addCheck = false;
                Set<Range> removeCheck = new HashSet<>();
                if(uniqueRanges.isEmpty()){
                    uniqueRanges.add(r);
                } else {
                    for(Range r2 : uniqueRanges){
                        if(r.min<=r2.min && r.min<r2.max
                                && r.max >= r2.max && r.max > r2.min){ //volledige overlap en meer  S1---s2---e2---E1
//                            uniqueRanges.remove(r2);
//                            uniqueRanges.add(r);
                            addCheck = true;
                            removeCheck.add(r2);
                        }
                        if((r.min<=r2.min && r.min<r2.max)
                                &&
                                (r.max<=r2.max && r.max >= r2.min)){ // halve overlap aan de voorkant S1--s2--E1--e2
//                            uniqueRanges.remove(r2);
                            r.max = r2.max;
//                            uniqueRanges.add(r);
                            addCheck = true;
                            removeCheck.add(r2);
                        }
                        if(r.min >= r2.min && r.min <= r2.max
                                &&
                                r.max > r2.max && r.max > r2.min){ // halve overlap aan de achterkant s2--S1--e2--e1
//                            uniqueRanges.remove(r2);
                            r.min = r2.min;
//                            uniqueRanges.add(r);
                            addCheck = true;
                            removeCheck.add(r2);
                        }
                        if(r.min >= r2.min && r.min <= r2.max
                                &&
                                r.max<=r2.max && r.max >= r2.min){ //volledige overlap inclusief s2--S1--E1--e2
//                            Do nothing
                            addCheck = false;
                        }
                        if((r.min<r2.min && r.max < r2.min) || (r.min > r2.max && r.max > r2.max)){ // Aansluitend, geen overlap S1---E1--s2---e2 || s2---e2---S1---E1
                            addCheck = true;
                        }
                    }
                    if(addCheck) {
                        uniqueRanges.add(r);
                    }
                    if(!removeCheck.isEmpty())
                        for(Range toRemove: removeCheck) {
                            uniqueRanges.remove(toRemove);
                        }
                        removeCheck.clear();
                }
            }
            long counter = 0;
            for (Range r : uniqueRanges){
                counter+= r.max - r.min +1;
            }
            return counter;
        }
    }

    RangeChecker checker = new RangeChecker();

    @Override
    public Integer part1(List<String> input) {
        Integer freshCounter = 0;
//        Long counter = 0L;
        for (String s : input) {
            if(s.isEmpty()){ // Er staat een witregel tussen de ranges en de id's, die mag overgeslagen worden
                continue;
            }
            if(s.contains("-")) { //Als er een - in staat, gaat het om een range, anders gaat het om een id
                long[] splits = Arrays.stream(s.split("-")).mapToLong(Long::parseLong).toArray();
                //Deze oplossing kan niet, want daar zijn de nummers te groot voor.
//                for(long i = splits[0]; i <= splits[1]; i++){ // gebruik "i<=ranges" omdat het inclusief is.
//                    ranges.add(i);
//                }
                checker.add(splits[0], splits[1]);
//                counter+=splits[1]-splits[0]; //voor de lol even berekenen hoeveel ranges er ongeveer zijn (waarschijnlijk overflowt dit zelfs)
            } else {
                long id = Long.parseLong(s);
                if(checker.isFresh(id)){
                    freshCounter++;
                }
            }
        }
        return freshCounter;
    }


    @Override
    public Integer part2(List<String> input) {
        part1(input);
        Long result =  checker.countUniqueRanges();
        System.out.println(result);
        return null;
    }

}
