import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day2 {
    private BufferedReader br;
    private ArrayList<String> inputs = new ArrayList();
    private List<String> sortedList;
    private Integer twos = 0;
    private Integer threes = 0;
    private String part2Answer;

    private void iterateThroughLine(String str, HashMap<Character,Integer> record) {
        Collection values = record.values();
        if (str.length() == 0) {
            if (values.contains(2)) twos++;
            if (values.contains(3)) threes++;
            return;
        }

        if(values.contains(2) && values.contains(3)) {
            twos++;
            threes++;
            return;
        }

        Character next = str.charAt(0);
        if (!record.containsKey(next)){
            record.put(next, 1);
        } else {
            int val = record.get(next);
            record.put(next, val+1);
        }
        iterateThroughLine(str.substring(1), record);
    }

    public Day2() throws IOException {
        File file = new File("./day2");
        this.br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null) {
            inputs.add(st);
            iterateThroughLine(st, new HashMap<Character, Integer>());
        }
        this.sortedList = inputs.stream().sorted().collect(Collectors.toList());
    }

    public int getPart1Answer() {
        return this.twos * this.threes;
    }

    public boolean compareStrings(String a, String b, String match, Integer misses) {
        int l = a.length();
        if (l == 0 && misses == 1) {
            this.part2Answer = match;
            return true;
        }
        if (l > 0) {
            if (a.charAt(0) == b.charAt(0)) {
                match += a.charAt(0);
                return compareStrings(a.substring(1), b.substring(1), match, misses);
            } else {
                misses++;
                if (misses > 1) {
                    return false;
                } else {
                    return compareStrings(a.substring(1), b.substring(1), match, misses);
                }
            }
        }
        return false;
    } 

    public Boolean compareFromInt(Integer i) {
        if (i + 1 < sortedList.size()) {
            String a = sortedList.get(i);
            String b = sortedList.get(i+1);
            if (compareStrings(a, b, "", 0)) {
                return true;
            }
        }
        return false;
    }

    public String getPart2Answer() {
        int l = sortedList.size();
        IntStream.range(0, l).filter(i -> compareFromInt(i)).mapToObj(i -> sortedList.get(i)).collect(Collectors.toList());

        return this.part2Answer;
    }

    public static void main(String[] args) throws IOException{
        Day2 day2 = new Day2();
        System.out.println("Day2 part 1: " + day2.getPart1Answer());
        System.out.println("Day2 part 2: " + day2.getPart2Answer());
    }
}
