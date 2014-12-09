//==============================================================================
//
//     Hockey-Stats
//     Copyright (C) 2014  Andrew MacCuaig
//     https://github.com/st-andrew/Hockey-Stats
//
//==============================================================================

package pool.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerStats {
    private final IntegerProperty player;
    private final StringProperty team;
    private final IntegerProperty gamesPlayed;
    private final IntegerProperty goals;
    private final IntegerProperty assists;
    private final IntegerProperty points;
    private final IntegerProperty plusMinus;
    private final IntegerProperty penaltyMinutes;
    private final IntegerProperty shots;
    private final IntegerProperty timeOnIce;
    private final IntegerProperty powerPlayGoals;
    private final IntegerProperty shortHandedGoals;
    private final IntegerProperty gameWinningGoals;
    private final IntegerProperty overtimeGoals;

    public PlayerStats(Integer player, String team, Integer gp, Integer g, Integer a, Integer p, Integer plusminus, Integer pim, Integer s, Integer toi,
                       Integer ppg, Integer shg, Integer gwg, Integer otg) {
        this.player = new SimpleIntegerProperty(player);
        this.team = new SimpleStringProperty(team);
        this.gamesPlayed = new SimpleIntegerProperty(gp);
        this.goals = new SimpleIntegerProperty(g);
        this.assists = new SimpleIntegerProperty(a);
        this.points = new SimpleIntegerProperty(p);
        this.plusMinus = new SimpleIntegerProperty(plusminus);
        this.penaltyMinutes = new SimpleIntegerProperty(pim);
        this.shots = new SimpleIntegerProperty(s);
        this.timeOnIce = new SimpleIntegerProperty(toi);
        this.powerPlayGoals = new SimpleIntegerProperty(ppg);
        this.shortHandedGoals = new SimpleIntegerProperty(shg);
        this.gameWinningGoals = new SimpleIntegerProperty(gwg);
        this.overtimeGoals = new SimpleIntegerProperty(otg);
    }

    public Integer getPlayer() {
        return player.get();
    }

    public IntegerProperty playerProperty() {
        return player;
    }

    public String getTeam() {
        return team.get();
    }

    public StringProperty teamProperty() {
        return team;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed.get();
    }

    public IntegerProperty gamesPlayedProperty() {
        return gamesPlayed;
    }

    public Integer getGoals() {
        return goals.get();
    }

    public IntegerProperty goalsProperty() {
        return goals;
    }

    public Integer getAssists() {
        return assists.get();
    }

    public IntegerProperty assistsProperty() {
        return assists;
    }

    public Integer getPoints() {
        return points.get();
    }

    public IntegerProperty pointsProperty() {
        return points;
    }

    public Integer getPlusMinus() {
        return plusMinus.get();
    }

    public IntegerProperty plusMinusProperty() {
        return plusMinus;
    }

    public Integer getPenaltyMinutes() {
        return penaltyMinutes.get();
    }

    public IntegerProperty penaltyMinuteProperty() {
        return penaltyMinutes;
    }

    public Integer getShots() {
        return shots.get();
    }

    public IntegerProperty shotsProperty() {
        return shots;
    }

    public Integer getTimeOnIce() {
        return timeOnIce.get();
    }

    public IntegerProperty timeOnIceProperty() {
        return timeOnIce;
    }

    public Integer getPowerPlayGoals() {
        return powerPlayGoals.get();
    }

    public IntegerProperty powerPlayGoalsProperty() {
        return powerPlayGoals;
    }

    public Integer getShortHandedGoals() {
        return shortHandedGoals.get();
    }

    public IntegerProperty shortHandedGoalsProperty() {
        return shortHandedGoals;
    }

    public Integer getGameWinningGoals() {
        return gameWinningGoals.get();
    }

    public IntegerProperty gameWinningGoalsProperty() {
        return gameWinningGoals;
    }

    public Integer getOvertimeGoals() {
        return overtimeGoals.get();
    }

    public IntegerProperty overtimeGoalsProperty() {
        return overtimeGoals;
    }
}
