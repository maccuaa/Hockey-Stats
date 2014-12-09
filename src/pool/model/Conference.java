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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Conference {
    private final StringProperty name;
    private final ObservableList<Division> divisions;

    public Conference(String name) {
        this.name = new SimpleStringProperty(name);
        this.divisions = FXCollections.observableArrayList();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObservableList<Division> getDivisions() {
        return divisions;
    }

    public void addDivision(Division division) {
        divisions.add(division);
    }

    public ObservableList<Team> getTeams() {
        ObservableList<Team> teams = FXCollections.observableArrayList();
        for (Division division : divisions)
            teams.addAll(division.getTeams());
        return teams;
    }

    public Team getTeam(String teamId) {
        for (Team team : getTeams()) {
            if (team.getId().equals(teamId)) {
                return team;
            }
        }
        return null;
    }

}
