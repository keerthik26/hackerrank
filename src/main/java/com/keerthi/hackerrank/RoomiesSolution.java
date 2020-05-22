package com.keerthi.hackerrank;

import java.util.*;

import static java.util.stream.IntStream.range;

public class RoomiesSolution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfDeadEnds = in.nextInt();
        List<String> deadEnds = new ArrayList<>(numberOfDeadEnds);
        range(0, numberOfDeadEnds).forEach(ignored -> deadEnds.add(in.next()));
        String finalState = in.next();
        String initialState = "0000";
        Roomies sol = new Roomies(initialState,deadEnds);
        System.out.println(sol.numberOfSteps(finalState));
        //runTests();

    }

    static void  runTests(){
        Roomies sol = new Roomies("0000", Arrays.asList("0201", "0101", "0102", "1212", "2002"));
        assert sol.numberOfSteps("0202") == 6 : "Number Of steps should be 6";
        sol = new Roomies("0000", Arrays.asList("1202","9202","0102","0302","0212","0292","0201","0203","1212", "2002"));
        assert sol.numberOfSteps("0202") == -1 : "Number Of steps should be -1";
        sol = new Roomies("5", Arrays.asList("4"));
        assert sol.numberOfSteps("2") == 7 : "Number Of steps should be 7";
        sol = new Roomies("5", Arrays.asList("6"));
        assert sol.numberOfSteps("2") == 3 : "Number Of steps should be 3";
    }
}

class Roomies {
    private final String initialState;
    private final Set<String> deadStates;
    Map<String, Integer> dp;
    char[] prev = new char[]{'9', '0', '1', '2', '3', '4', '5', '6', '7', '8'};
    char[] nxt = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public Roomies(String initialState, List<String> deadStates) {
        this.initialState = initialState;
        this.deadStates = new HashSet<>(deadStates);
        dp = new HashMap<>();
        dp.put(initialState, 0);
    }

    int numberOfSteps(String state) {
        preprocess();
        return dp.getOrDefault(state, -1);

    }

    void preprocess() {
        Deque<String> remaining = new ArrayDeque<>();
        remaining.offer(initialState);
        while (!remaining.isEmpty()) {
            String thisState = remaining.poll();
            generateNextStates(thisState).forEach(s -> {
                if (!dp.containsKey(s)) {
                    dp.put(s, dp.get(thisState) + 1);
                    remaining.offer(s);
                }
            });
        }
    }

    List<String> generateNextStates(String state) {
        char[] chars = state.toCharArray();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char n = nxt[chars[i] - '0'];
            char p = prev[chars[i] - '0'];
            char[] charsCopy = state.toCharArray();
            charsCopy[i] = n;
            String nextState = String.valueOf(charsCopy);
            if (!deadStates.contains(nextState)) {
                result.add(nextState);
            }
            charsCopy[i] = p;
            String prevState = String.valueOf(charsCopy);
            if (!deadStates.contains(prevState)) {
                result.add(prevState);
            }
        }
        return result;
    }
}
