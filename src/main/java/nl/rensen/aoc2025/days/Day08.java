package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;

import java.util.*;

class Circuit implements Comparable<Circuit>{
    Set<Cord> members;
    public Circuit(){
        this.members = new HashSet<>();
    }

    boolean add(Cord cord){
        return members.add(cord);
    }

    boolean contains(Cord cord){
        return members.contains(cord);
    }

    int size(){
        return members.size();
    }


    @Override
    public int compareTo(Circuit o) {
        return o.size() - this.size();
    }
}

class Cord{
    int x, y, z;
    Circuit circuit;
    public Cord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "{"  + x +
                ", " + y +
                ", " + z +
                '}';
    }
}
class DistancePair implements Comparable<DistancePair>{
    Cord cord1, cord2;
    Double distance;

    public DistancePair(Cord cord1, Cord cord2, Double distance){
        this.cord1 = cord1;
        this.cord2 = cord2;
        this.distance = distance;
    }
    @Override
    public String toString() {
        return "DistancePair{" +
                "cord1=" + cord1 +
                ", cord2=" + cord2 +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(DistancePair o) {
        return this.distance.compareTo(o.distance);
    }
}
public class Day08 implements Day<Integer, String> {
    int SAMPLE_SIZE = 1000;

    @Override
    public Integer part1(List<String> input) {

//      Zet alle inputs om in coordinaten objecten (x,y,z)
        List<Cord> cords = new ArrayList<>();
        for(String str : input) {
            String[] inputs = str.split(",");
            cords.add(new Cord(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])));
        }

//        Zet alle coordinaten in een distance mapping tot elkaar (de pairs list).
//        Dus elk coordinaat slaat de afstand van zichzlef tot elk ander coordinaat op.
//        Dit hoeft niet voor zowel x>y en y<x, maar alleen, x>y is voldoende.
        List<DistancePair> pairs = new ArrayList<>();
        for(int i = 0; i < cords.size(); i++){
            Cord base =  cords.get(i);
            for(int j = i+1; j < cords.size(); j++){
                Cord compare = cords.get(j);
                var distance =  // https://www.geeksforgeeks.org/maths/euclidean-distance/
                        Math.pow(base.x-compare.x, 2) +
                        Math.pow(base.y - compare.y, 2) +
                        Math.pow(base.z - compare.z, 2)
                ;
                pairs.add(new DistancePair(base, compare, distance));
            }
        }
//        Check welke pairs het dichtst bij elkaar zijn (gebruikt de "compareTo" van DistancePair, die eigenlijk Double.compareTo gebruikt.
        pairs.sort(DistancePair::compareTo);

//        Pak alleen de eerste 1000 gevonden pairs
        pairs = pairs.subList(0, SAMPLE_SIZE);

//        In deze lijst worden alle circuits opgeslagen
        List<Circuit> circuits = new ArrayList<>();

//        Deze (lange) for-loop maakt alle circuits.
//        Het loopt door alle distancepairs heen.
//        Als geen van beide coordinaten in een circuit gebruikt worden, wordt het een nieuwe circuit.
//        Als een van beide al wel in een circuit zit, wordt de andere coordinaat ook aan dat circuit toegevoegd.
//        Als een coordinaat in een circuit zit en het andere coordinaat in een ander circuit, dan worden de circuits samengevoegd.
        for(int i = 0; i < pairs.size(); i++){
            DistancePair pair = pairs.get(i);
            if(circuits.isEmpty()){
                Circuit circuit = new Circuit();
                circuit.add(pair.cord1);
                circuit.add(pair.cord2);
                pair.cord1.circuit = circuit;
                pair.cord2.circuit = circuit;
                circuits.add(circuit);
                continue;
            }
            boolean circuitFound = false;
            boolean coupleCircuit = false;
            Circuit foundCircuit = null;
            Circuit foundCoupleCircuit = null;

//            Deze for-loop in de for-loop checkt of de coordinaten al in een bestaande circuit zit en zet bovenstaande variabelen als dat zo is.
            for(Circuit circuit : circuits){
                if(circuit.contains(pair.cord1) || circuit.contains(pair.cord2)){
                    if(circuitFound){
                        coupleCircuit = true;
                        foundCoupleCircuit = circuit;
                    } else {
                        circuitFound = true;
                        foundCircuit = circuit;
                    }
                }
            }
            if(coupleCircuit){
                for(Cord cord : foundCoupleCircuit.members){
                    foundCircuit.add(cord);
                }
                circuits.remove(foundCoupleCircuit);
            }
            else if(!circuitFound){
                Circuit circuit = new Circuit();
                circuit.add(pair.cord1);
                circuit.add(pair.cord2);
                circuits.add(circuit);
                continue;
            } else {
                foundCircuit.add(pair.cord1);
                foundCircuit.add(pair.cord2);
            }
        }

//        Sorteer alle gevonden circuits met compareTo (vergelijkt de members.size van twee circuits)
//        Daarna vermenigvuldigt dit stukje de sizes van de grootste 3 circuits met elkaar.
        circuits.sort(Circuit::compareTo);
        int count = 1;
        for(int i = 0; i < 3; i++){
            count *= circuits.get(i).size();
        }
        return count;
    }

    @Override
    public Integer part2(List<String> input) {

        List<Cord> cords = new ArrayList<>();
        for(String str : input) {
            String[] inputs = str.split(",");
            cords.add(new Cord(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])));
        }

        List<DistancePair> pairs = new ArrayList<>();
        for(int i = 0; i < cords.size(); i++){
            Cord base =  cords.get(i);
            for(int j = i+1; j < cords.size(); j++){
                Cord compare = cords.get(j);
                var distance =  // https://www.geeksforgeeks.org/maths/euclidean-distance/
                        Math.pow(base.x-compare.x, 2) +
                                Math.pow(base.y - compare.y, 2) +
                                Math.pow(base.z - compare.z, 2)
                        ;
                pairs.add(new DistancePair(base, compare, distance));
            }
        }
        pairs.sort(DistancePair::compareTo);

//        Dit is niet meer nodig voor part 2, nu wordt namelijk de volledige lijst gebruikt totdat het resultaat bereikt is.
//        pairs = pairs.subList(0, SAMPLE_SIZE);

        List<Circuit> circuits = new ArrayList<>();
        for(int i = 0; i < pairs.size(); i++){
            DistancePair pair = pairs.get(i);
            if(circuits.isEmpty()){
                Circuit circuit = new Circuit();
                circuit.add(pair.cord1);
                circuit.add(pair.cord2);
                pair.cord1.circuit = circuit;
                pair.cord2.circuit = circuit;
                circuits.add(circuit);
                continue;
            }
            boolean circuitFound = false;
            boolean coupleCircuit = false;
            Circuit foundCircuit = null;
            Circuit foundCoupleCircuit = null;

            for(Circuit circuit : circuits){
                if(circuit.contains(pair.cord1) || circuit.contains(pair.cord2)){
                    if(circuitFound){
                        coupleCircuit = true;
                        foundCoupleCircuit = circuit;
                    } else {
                        circuitFound = true;
                        foundCircuit = circuit;
                    }
                }
            }
            if(coupleCircuit){
                for(Cord cord : foundCoupleCircuit.members){
                    foundCircuit.add(cord);
                }
                circuits.remove(foundCoupleCircuit);
            }
            else if(!circuitFound){
                Circuit circuit = new Circuit();
                circuit.add(pair.cord1);
                circuit.add(pair.cord2);
                circuits.add(circuit);
                continue;
            } else {
                foundCircuit.add(pair.cord1);
                foundCircuit.add(pair.cord2);
            }

//            Alles wat nodig is voor part 2, is deze extra check.
//            Wanneer er slechts 1 circuit is en de members-lijst van deze circuit alle coordinaten bevat (even lang als de coordinaten lijst), return dan het resultaat.
            if(circuits.size() == 1 && cords.size() == circuits.get(0).members.size()){
                return pair.cord1.x * pair.cord2.x;
            }
        }

//      Deze volgende berekeningen zijn niet meer nodig voor part 2

//        circuits.sort(Circuit::compareTo);
//        int count = 1;
//        for(int i = 0; i < 3; i++){
//            count *= circuits.get(i).size();
//        }
//        return count;
        return 0;
    }
}
