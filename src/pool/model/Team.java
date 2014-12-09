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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Team {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty conference;
    private final StringProperty division;
    private final IntegerProperty wins;
    private final IntegerProperty losses;
    private final ObservableList<Player> roster;
    private final ObservableList<Game> games;

    public Team(String id, String name, String conference, String division) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.conference = new SimpleStringProperty(conference);
        this.division = new SimpleStringProperty(division);
        this.roster = FXCollections.observableArrayList();
        this.games = FXCollections.observableArrayList();
        this.wins = new SimpleIntegerProperty(0);
        this.losses = new SimpleIntegerProperty(0);
    }

    public void addPlayer(Player player) {
        roster.add(player);
    }

    public Player getPlayer(Integer playerId) {
        for (Player player : roster) {
            if (player.getId().equals(playerId)) {
                return player;
            }
        }
        return null;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public Game getGame(String gameId) {
        for (Game game : games) {
            if (game.getId().equals(gameId)) {
                return game;
            }
        }
        return null;
    }

    public void setWins(Integer wins) {
        this.wins.set(wins);
    }

    public void setLosses(Integer losses) {
        this.losses.set(losses);
    }

    public Integer getWins() {
        return wins.get();
    }

    public IntegerProperty winsProperty() {
        return wins;
    }

    public Integer getLosses() {
        return losses.get();
    }

    public IntegerProperty lossesProperty() {
        return losses;
    }

    public ObservableList<Player> getRoster() {
        return roster;
    }

    public ObservableList<Game> getGames() {
        return games;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getConference() {
        return conference.get();
    }

    public StringProperty conferenceProperty() {
        return conference;
    }

    public String getDivision() {
        return division.get();
    }

    public StringProperty divisionProperty() {
        return division;
    }
}
