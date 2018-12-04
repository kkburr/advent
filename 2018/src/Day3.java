import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Square {
    public int id;
    public int column;
    public int row;
    public int width;
    public int height;

    public Square(String input) {
        input = input.replaceAll("\\s","");
        int[] digits = Stream.of(input.split("\\D")).filter(s -> !s.equals("")).mapToInt(s -> Integer.parseInt(s)).toArray();

    //find a better way to do this
        this.id = digits[0];
        this.column = digits[1];
        this.row = digits[2];
        this.width = digits[3];
        this.height = digits[4];
    }
}

public class Day3 {
    private Integer[][] grid = new Integer[1000][];
    private ArrayList<Square> squares = new ArrayList<Square>();

    public Day3(BufferedReader br) throws IOException{
        String st;
        for(int x = 0; x < 1000; x++){
            grid[x] = new Integer[1000];
            for(int y = 0; y < 1000; y++){
                grid[x][y] = 0;
            }
        }

        while((st = br.readLine()) != null) {
            Square square = new Square(st);
            for (int h=0; h<square.height; h++) {
                for (int w=0; w<square.width; w++) {
                    int val = grid[square.row+h][square.column+w];
                    grid[square.row+h][square.column+w] = val+1;
                    squares.add(square);
                }
            }
        }
    }

    public void getPart1() {
        int count = 0;

        for(int i=0; i<grid.length; i++) {
            for (int y=0; y<grid[i].length; y++) {
                if (grid[i][y] > 1) count++;
            }
        }

        System.out.println("Part 1 answer: " + count);
    }

    public void getPart2() {
        List<Square> answerL = squares.stream().filter(s -> {
            for (int h=0; h<s.height; h++) {
                for (int w = 0; w < s.width; w++) {
                    int val = grid[s.row + h][s.column + w];
                    if (val > 1) {
                        return false;
                    }
                }
            }
            //why is this getting hit so many times?
            return true;
        }).collect(Collectors.toList());
        //find better way than collect ^
        Square answer = answerL.get(0);
        System.out.println("Part 2 answer: " + answer.id);
    }


    public static void main(String[] args) throws IOException{
        File file = new File("./2018/src/day3");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Day3 answer = new Day3(br);

        answer.getPart1();
        answer.getPart2();
    }
}
