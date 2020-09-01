package com.keerthi.codechef.complexity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.reverseOrder;

public class WinningStrategySolution {
    public static void main(String[] args) throws java.lang.Exception {
        runTests();
        runLogic();
    }

    static void runLogic() {
        Scanner scanner = new Scanner(System.in);
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int size = scanner.nextInt();
            ArrayList<Integer> input = new ArrayList<>();
            IntStream.range(0, size).forEach(value -> input.add(Integer.parseInt(scanner.next())));
            WinningStrategy strategy = new WinningStrategy();
            System.out.println(strategy.getWinningStrategy(input));
        }
    }

    static void runTests() {

        WinningStrategy strategy = new WinningStrategy();

        assert strategy.getWinningStrategy(Arrays.asList(1, 1, 1)).equals("second") : "Should be a second player";
        assert strategy.getWinningStrategy(Arrays.asList(1, 1, 1, 1)).equals("draw") : "Is a draw game";
        assert strategy.getWinningStrategy(Arrays.asList(1, 1, 1, 2)).equals("first") : "Should be a first player";
        assert strategy.getWinningStrategy(Arrays.asList(5, 1, 3, 2)).equals("first") : "Should be a first player";
    }

}

class WinningStrategy {


    public String getWinningStrategy(List<Integer> input) {
        final List<Integer> elements = input.stream().sorted(reverseOrder()).collect(Collectors.toList());
        long player1Score = IntStream.range(0, elements.size()).filter(this::checkIfFirstPlayerTurn).mapToLong(elements::get).sum();
        long player2Score = IntStream.range(0, elements.size()).filter(this::checkIfSecondPlayerTurn).mapToLong(elements::get).sum();

        return player1Score == player2Score ? "draw" : player1Score > player2Score ? "first" : "second";
    }

    boolean checkIfSecondPlayerTurn(int i) {
        return !checkIfFirstPlayerTurn(i);
    }

    boolean checkIfFirstPlayerTurn(int i) {
        if (i == 0) {
            return true;
        } else if (i == 1 || i == 2) {
            return false;
        } else return i % 2 == 1;
    }



}


