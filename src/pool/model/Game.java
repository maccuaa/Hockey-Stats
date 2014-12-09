//==============================================================================
//
//     Hockey-Stats
//     Copyright (C) 2014  Andrew MacCuaig
//     https://github.com/st-andrew/Hockey-Stats
//
//     This program is free software: you can redistribute it and/or modify
//     it under the terms of the GNU General Public License as published by
//     the Free Software Foundation, either version 3 of the License, or
//     (at your option) any later version.
//
//     This program is distributed in the hope that it will be useful,
//     but WITHOUT ANY WARRANTY; without even the implied warranty of
//     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//     GNU General Public License for more details.
//
//     You should have received a copy of the GNU General Public License
//     along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
