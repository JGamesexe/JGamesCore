package com.jgamesexe.jgamescore.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

public class JBoard {

    private Scoreboard scoreboard;
    private Objective objective;
    private HashMap<Integer, Line> lines = new HashMap<>();

    public JBoard(Player player) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(scoreboard);
        objective = scoreboard.registerNewObjective("jBoard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void addToTeam(Player player, String teamName) {

        Team team = scoreboard.getEntryTeam(player.getName());
        if (team != null) team.unregister();

        teamName += player.getUniqueId().toString().substring(0, 8);
        team = scoreboard.getTeam(teamName);

        if (team == null) team = scoreboard.registerNewTeam(teamName);

        team.addEntry(player.getName());

    }

    public void updateNick(Player player, String nick) {

        Team team = scoreboard.getEntryTeam(player.getName());

        String[] split = nick.split(player.getName());

        team.setPrefix("");
        team.setSuffix("");

        if (split.length > 0) team.setPrefix(split[0].replace(player.getName(), ""));
        if (split.length > 1) team.setSuffix(split[1]);

    }

    public void displayName(String name) {
        objective.setDisplayName(name);
    }

    public void addLine(int line, String left, String middle, String right) {
        if (lines.containsKey(line)) return;
        lines.put(line, new Line(line, left, middle, right));
    }

    public void addBlankLine(int line) {
        if (lines.containsKey(line)) return;
        lines.put(line, new Line(line));
    }

    public Line getLine(int line) {
        return lines.containsKey(line) ? lines.get(line) : null;
    }

    public void removeLine(int line) {
        if (!lines.containsKey(line)) return;
        Line l = getLine(line);
        scoreboard.resetScores(l.entry.getEntry());
        l.team.removeEntry(l.entry.getEntry());
        lines.remove(line);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public class Line {

        int line;
        Team team;
        Score entry;
        boolean blank = false;

        private Line(int line, String left, String middle, String right) {
            line = 26 - line;
            Team team = scoreboard.getTeam("teamLine" + line);
            if (team == null) team = scoreboard.registerNewTeam("teamLine" + line);

            team.setPrefix(left);
            team.setSuffix(right);

            Score score = objective.getScore(middle);
            team.addEntry(score.getEntry());
            score.setScore(line);

            this.line = line;
            this.team = team;
            this.entry = score;
        }

        private Line(int line) {
            blank = true;
            line = 26 - line;
            Score score = objective.getScore("§1§0§" + Integer.toHexString(line - 11));
            score.setScore(line);
        }

        private boolean isBlank() {
            return blank;
        }

        public void update(String left, String right) {
            if (left != null) team.setPrefix(left);
            if (right != null) team.setSuffix(right);
        }

    }

}
