package Bingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Bingo {
    private int maxRand;
    private ArrayList<String> squareList;
    private PrintWriter pw;
    private StringBuilder csvBuilder;


    public Bingo() throws FileNotFoundException {
        this.maxRand = 0;
        this.squareList = new ArrayList<>();
        this.csvBuilder = new StringBuilder();
        this.pw = new PrintWriter(new File("board.csv"));
    }

    private int findRandomDigit() {
        Random random = new Random();
        return random.nextInt(maxRand);
    }

    private void createBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    csvBuilder.append("FREE");
                    csvBuilder.append(',');
                } else {
                    int index = findRandomDigit();
                    String square = squareList.get(index);
                    squareList.remove(index);
                    maxRand--;
                    csvBuilder.append(square);
                    csvBuilder.append(',');
                }
            }
            csvBuilder.append('\n');

        }
    }

    public void addSquare(String square) {
        squareList.add(square);
        this.maxRand++;
    }

    public void run() throws Throwable {
        if (maxRand < 25) {
            throw new Throwable("You need " + Math.subtractExact(25, squareList.size()) + " more board square strings!");
        }
        createBoard();
        pw.write(csvBuilder.toString());
        pw.close();
        System.out.println("done");
    }
}

