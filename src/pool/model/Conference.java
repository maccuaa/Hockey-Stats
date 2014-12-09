//==============================================================================
//
//     Hockey-Stats
//     Copyright (C) 2014  Andrew MacCuaig
//     https://github.com/st-andrew/Hockey-Stats
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
