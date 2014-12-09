//==============================================================================
//
//     Hockey-Stats
//     Copyright (C) 2014  Andrew MacCuaig
//     https://github.com/st-andrew/Hockey-Stats
//
//==============================================================================

package pool.model;

import javafx.beans.property.*;

public class Game {
    private final IntegerProperty id;
    private final StringProperty home_team;
    private final StringProperty away_team;
    private final IntegerProperty home_goals;
    private final IntegerProperty away_goals;
    private StringProperty date;

    public Game(Integer id, String home_team, String away_team, Integer home_goals, Integer away_goals, String date) {
        this.id = new SimpleIntegerProperty(id);
        this.home_team = new SimpleStringProperty(home_team);
        this.away_team = new SimpleStringProperty(away_team);
        this.home_goals = new SimpleIntegerProperty(home_goals);
        this.away_goals = new SimpleIntegerProperty(away_goals);
        this.date = new SimpleStringProperty(date);
    }

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getHomeTeam() {
        return home_team.get();
    }

    public StringProperty homeTeamProperty() {
        return home_team;
    }

    public String getAwayTeam() {
        return away_team.get();
    }

    public StringProperty awatTeamProperty() {
        return away_team;
    }

    public Integer getHomeGoals() {
        return home_goals.get();
    }

    public IntegerProperty homeGoalsProperty() {
        return home_goals;
    }

    public Integer getawayGoals() {
        return away_goals.get();
    }

    public IntegerProperty awayGoalsProperty() {
        return away_goals;
    }

    public String getString() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

}
