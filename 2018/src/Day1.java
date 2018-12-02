import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day1 {
    private ArrayList<Integer> inputs = new ArrayList();
    private HashMap<Integer, Integer> frequencyMap = new HashMap();
    private BufferedReader br;

    public Day1(BufferedReader br) {
        this.br = br;
    }

    public int getPart1Answer() throws IOException {
        String st;
        int pos = 0;
        while ((st = br.readLine()) != null) {
            int input = Integer.parseInt(st);
            inputs.add(input);
            pos += input;
        }
        return pos;
    }

    public int getPart2Answer() {
        int loc = 0;
        int pos = 0;
        while (true) {
            if (loc >= inputs.size()) loc = 0;
            int input = inputs.get(loc);
            pos += input;
            if (frequencyMap.containsKey(pos)) {
                return pos;
            } else {
                frequencyMap.put(pos, 1);
            }
            loc++;
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("./2018/inputs/day1");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Day1 day1 = new Day1(br);
        System.out.println("Day1 part 1: " + day1.getPart1Answer());
        System.out.println("Day1 part 2: " + day1.getPart2Answer());
    }
}