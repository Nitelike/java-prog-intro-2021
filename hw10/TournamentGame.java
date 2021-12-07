package game;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TournamentGame {
    private Board board;
    private Player[] players;

    public TournamentGame(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public Map<String, Integer> play(boolean winLog, boolean gameLog) {
        int cnt = players.length;
        int[] points = new int[cnt];
        ArrayList<String> resMessages = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                if (i != j) {
                    board.clear();
                    int curRes = new TwoPlayerGame(board, players[i], players[j]).play(gameLog);
                    switch (curRes) {
                        case 1:
                            points[i] += 3;
                            resMessages.add("Player" + i + " defeated Player" + j);
                            break;
                        case 2:
                            points[j] += 3;
                            resMessages.add("Player" + j + " defeated Player" + i);
                            break;
                        case 0:
                            points[i]++;
                            points[j]++;
                            resMessages.add("Draw between Player" + j + " and Player" + i);
                            break;
                        default:
                            throw new AssertionError("Unknown round result: " + curRes);
                    }
                }
            }
        }

        Map<Integer, List<String>> tempStandings = new TreeMap<>();

        for (int i = 0; i < cnt; i++) {
            tempStandings.computeIfAbsent(points[i], k -> new ArrayList<>()).add("Player" + i);
        }

        Map<String, Integer> standings = new LinkedHashMap<>();

        for (Map.Entry<Integer, List<String>> entry : tempStandings.entrySet()) {
            int curPoints = entry.getKey();
            for (String name : entry.getValue()) {
                standings.put(name, curPoints);
            }
        }

        if (winLog) {
            for (String resMessage : resMessages) {
                System.out.println(resMessage);
            }
        }

        return standings;
    }
}
