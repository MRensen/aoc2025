package nl.rensen.aoc2025.days;

import nl.rensen.aoc2025.common.Day;
import org.checkerframework.checker.units.qual.C;

import java.util.*;

class Node{
    public static long count = 0;
    Coord coord;
    Node left;
    Node right;
    Node parent;
    int layer;
    public Node(Coord coord,Node parent, int layer){
        this.coord = coord;
        this.layer = layer;

        this.parent = parent;
    }
    public Node(Coord coord, Node left, Node right, Node parten, int layer){
        this.coord = coord;
        this.left = left;
        this.right = right;
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "Node{" +
                "coord=" + coord +
                ", layer=" + layer +
                '}';
    }

    public String toStringNode(int indent){
        return layer + ": Node{" +
                "coord=" + coord +
                (this.left!=null ? ", \n"+("\t".repeat(indent))+"left=" + left.toStringNode(indent+1) : "null") +
                (this.right!=null ? ", \n"+("\t".repeat(indent))+"right=" + right.toStringNode(indent+1) : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(coord, node.coord);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coord);
    }
}
//class Tree{
//    Node
//}
class Coord{
    static int MAX;
    int x;
    int y;
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    static void setMax(int max){
        MAX = max;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
public class Day07 implements Day<Integer, String> {
    @Override
    public Integer part1(List<String> input) {
        String[][] grid = new String[input.size()][input.get(0).length()];
        Coord start = null;
        Coord.setMax(input.size()-1);
        List<Coord> splitters = new ArrayList<>();
        Set<Coord> beams = new HashSet<>();
        int splits = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                grid[i][j] = input.get(i).charAt(j) + "";
                if(input.get(i).charAt(j) == 'S'){
                    start = new Coord(i, input.get(i).indexOf("S"));
                }
                if(input.get(i).charAt(j) == '^'){
                    splitters.add(new Coord(i, j));
                }
            }

        }
        beams.add(start);
        boolean going = true;
        while(going){
            List<Coord> toAdd = new ArrayList<>();
            List<Coord> toRemove = new ArrayList<>();
            for(Coord beam : beams){
                stepDown(beam);
                if(beam.x == Coord.MAX){
                    going = false;
                }
                if(splitters.contains(beam)){
                    splits++;
                    toRemove.add(beam);
                    toAdd.add(new Coord(beam.x, beam.y+1));
                    toAdd.add(new Coord(beam.x, beam.y-1));
                }
            }
            for(Coord beam : toAdd){
                safeAdd(beams, beam);
            }
            for(Coord rem : toRemove){
                beams.removeIf(beam -> beam.equals(rem));
            }
        }
        return splits;
    }

    private void safeAdd(Set<Coord> beams, Coord coord) {
        boolean safe = true;
        for (Coord beam : beams){
            if (beam.y == coord.y) {
                safe = false;
                break;
            }
        }
        if(safe){
            beams.add(coord);
        }
    }

    private void stepDown(Coord beam) {
        beam.x++;
    }

    @Override
    public Integer part2(List<String> input) {
        String[][] grid = new String[input.size()][input.get(0).length()];
        Coord start = null;
        Coord.setMax(input.size()-1);
        System.out.println("MAX: " + Coord.MAX);
        List<Coord> splitters = new ArrayList<>();
        Set<Coord> beams = new HashSet<>();
        int splits = 0;
        Node startNode = null;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                grid[i][j] = input.get(i).charAt(j) + "";
                if(input.get(i).charAt(j) == 'S'){
                    start = new Coord(i, input.get(i).indexOf("S"));
//                    startNode = new Node(start);
                }
                if(input.get(i).charAt(j) == '^'){
                    splitters.add(new Coord(i, j));
                }
            }

        }

        beams.add(start);
        start.x = 2;
        startNode =  getPath(new Node(start, null, 0), 1, splitters, new ArrayList<>());
        System.out.println();
        System.out.println(startNode.toStringNode(1));
//        Coord testcoord = new Coord()
//        Node testNode = new Node(new Coord(1,2), new Node())
        int paths = allPaths(startNode, 1);

        return paths;
//        return 0;
    }

    private int allPaths(Node node, int paths) {
        if(node == null){
            System.out.println("null");
            return 0;
        }
//        if(node.parent == null){
//            return paths;
//        }
        if(node.coord.x == Coord.MAX){
            System.out.println(node.coord);
            return 1;
        } else {
            System.out.print(node.coord + " --> ");
          int path1 = allPaths(node.left, paths);
          int path2 = allPaths(node.right, paths);
          return path1 + path2;
        }
    }


//    private Node getPath(Node node,  int layer, List<Coord> splitters) {
//        int x = node.coord.x;
//        boolean setNodes = false;
//        while(x != Coord.MAX){
//            x++;
//            Coord temp = new Coord(x, node.coord.y);
//            if(splitters.contains(temp)){
//                setNodes = true;
//                break;
//            }
//        }
//        if(setNodes) {
//            node.left = getPath(new Node(new Coord(x, node.coord.y - 1), node, layer + 1), layer + 1, splitters);
//            node.right = getPath(new Node(new Coord(x, node.coord.y + 1), node, layer + 1), layer + 1, splitters);
//        } else {
//            node.left = new Node(new Coord(Coord.MAX,node.coord.y-1), node, layer +1);
//            node.right = new Node(new Coord(Coord.MAX, node.coord.y+1), node, layer +1);
//        }
//        return node;
//    }
private Node getPath(Node node,  int layer, List<Coord> splitters, List<Node> hist) {
        Node.count++;
    System.out.print(hist.size());
    int x = node.coord.x;
    int yNewLeft = node.coord.y+1;
    int yNewRight = node.coord.y-1;
    boolean tryleft = true;
    boolean tryright = true;
    boolean keepGoing = true;
    if(hist.contains(node)){
        return node;
    } else {
        hist.add(node);
    }
    while(keepGoing){
        x++;
        Coord templeft = new Coord(x, yNewLeft);
        Coord tempright = new Coord(x, yNewRight);
        if(tryleft && splitters.contains(templeft)){
            tryleft = false;
            if(node.left == null){
                System.out.println("---left");
                node.left = getPath(new Node(templeft, node, layer + 1), layer + 1, splitters, hist);
            }

        }
        if(tryright && splitters.contains(tempright)){
            tryright = false;
            if(node.right == null){
                System.out.println("---right");

                node.right = getPath(new Node(tempright, node, layer + 1), layer + 1, splitters, hist);
            }
        }
        if((!tryright && !tryleft)||x>=Coord.MAX-1){
            keepGoing = false;
        }
    }
    if(node.left == null){
        node.left = new Node(new Coord(Coord.MAX,yNewLeft), node, layer +1);
    }
    if(node.right == null){
        node.right = new Node(new Coord(Coord.MAX, yNewRight), node, layer +1);
    }
    return node;
}
}

