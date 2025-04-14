package com.irex.FirstSpring.Cricket;

import java.util.*;

public class IPL {
    private final List<CricketTeam> teams;
    private final List<Match> matchSchedules;

    public IPL(List<CricketTeam> teams) {
        this.teams = teams;
        this.matchSchedules = generateMatchSchedules();
    }

    public List<CricketTeam> getTeams() {
        return this.teams;
    }

    public List<Match> getMatchSchedules() {
        return matchSchedules;
    }

    public Match getMatchOnDay(int day) {
        if(day > matchSchedules.size()) return null;
        return matchSchedules.get(day - 1);
    }

    public List<Match> getMatchesByTeamName(String teamName) {
        List<Match> res = new ArrayList<>();
        for(Match match : matchSchedules) {
            if(match.teamA().equalsIgnoreCase(teamName) || match.teamB().equalsIgnoreCase(teamName))
                res.add(match);
        }
        return res;
    }

    private List<Match> generateMatchSchedules() {
        if(teams.size() < 10) return null;

        int groupLength = teams.size() / 2;
        Queue<int[]> queue = new ArrayDeque<>();

        int[][] intra = getIntraGroupCombinations(groupLength);
        for(int[] pair : intra) {
            queue.offer(pair);
            queue.offer(new int[] {pair[0] + groupLength, pair[1] + groupLength});
        }

        int[][] inter = getInterGroupCombinations(groupLength);
        for(int[] pair : inter) {
            queue.offer(pair);
        }

        return getSchedules(queue, teams);
    }

    private ArrayList<Match> getSchedules(Queue<int[]> queue, List<CricketTeam> teams) {
        int prevA = -1, prevB = -1;
        int day = 1;
        ArrayList<Match> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int a = pair[0], b = pair[1];

            if (prevA == a || prevB == a || prevA == b || prevB == b) {
                queue.offer(pair);
                continue;
            }

            prevA = a;
            prevB = b;

            res.add(new Match(day++, teams.get(a).getTeamName(), teams.get(b).getTeamName(), teams.get(a).getHometown()));
        }

        return res;
    }

    private int[][] getInterGroupCombinations(int groupLength) {
        int[][] res = new int[groupLength * (groupLength + 1)][2]; // Each team will play an extra match with a random team of the other group
        int p = 0; // to track the next empty index of result array

        Random random = new Random();
        int limit = groupLength;
        boolean[] selected = new boolean[groupLength];

        for (int i = 0; i < groupLength; i++) {
            int rand = random.nextInt(limit--);
            int index = -1;
            for (int k = 0; k < groupLength; k++) {
                if (!selected[k]) {
                    if (rand == 0) {
                        index = k;
                        selected[k] = true;
                        break;
                    }
                    rand--;
                }
            }

            insertValues(i, index + groupLength, p++, res); // Inserting A vs B (First Match)

            for (int j = 0; j < groupLength; j++) {
                if (j != index) {
                    insertValues(i, j + groupLength, p++, res); // Inserting A vs Others (Single Match)
                }
            }

            insertValues(index + groupLength, i, p++, res); // Inserting B vs A (Second Match)
        }

        return res;
    }

    private int[][] getIntraGroupCombinations(int groupLength) {
        int[][] res = new int[groupLength * (groupLength - 1)][2];
        int p = 0;

        for (int i = 0; i < groupLength; i++) {
            for (int j = 0; j < groupLength; j++) {
                if (i != j) {
                    insertValues(i, j, p++, res);
                }
            }
        }

        return res;
    }

    private void insertValues(int a, int b, int index, int[][] container) {
        container[index][0] = a;
        container[index][1] = b;
    }
}
