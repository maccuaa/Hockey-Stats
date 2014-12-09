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

package pool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pool.model.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DBManager {

    private Connection database;
    private SimpleDateFormat gameDateInFormat = new SimpleDateFormat("MMM dd ''yy");
    private SimpleDateFormat playerDateInFormat = new SimpleDateFormat("MMMM d, yyyy");
    private SimpleDateFormat properDateFormat = new SimpleDateFormat("MMM dd, yyyy");

    public DBManager() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean openDB() {
        try {
            database = DriverManager.getConnection("jdbc:sqlite:pool.db");
            System.out.println("Connected to database");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeDB() {
        try {
            database.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getConferencesAndDivisions(ObservableList<Division> divisions, ObservableList<Conference> conferences) {
        try {
            String sqlConferencesQuery = "SELECT DISTINCT conference FROM teams;";
            String sqlDivisionsQuery = "SELECT DISTINCT division FROM teams WHERE conference=?;";

            Statement conferenceStatement = database.createStatement();
            ResultSet conferenceRs = conferenceStatement.executeQuery(sqlConferencesQuery);

            while (conferenceRs.next()) {
                Conference conference = new Conference(conferenceRs.getString("conference"));
                conferences.add(conference);

                PreparedStatement divisionStatement = database.prepareStatement(sqlDivisionsQuery);
                divisionStatement.setString(1, conference.getName());

                ResultSet divisionRs = divisionStatement.executeQuery();

                while (divisionRs.next()) {
                    Division division = new Division(divisionRs.getString("division"));
                    divisions.add(division);

                    conference.addDivision(division);
                }
                divisionRs.close();
            }
            conferenceRs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTeams(ObservableList<Team> list, ObservableList<Division> divisions) {
        try {
            for (Division division : divisions) {

                String sqlQueryString = "SELECT * FROM teams WHERE division=? ORDER BY name ASC;";

                PreparedStatement statement = database.prepareStatement(sqlQueryString);

                statement.setString(1, division.getName());

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Team team = new Team(rs.getString("id"), rs.getString("name"), rs.getString("conference"),
                            rs.getString("division"));
                    list.add(team);
                    division.addTeam(team);
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadPlayers(ObservableList<Player> list, ObservableList<Team> teams) {
        try {
            // Loop through each team, getting all the players in their roster
            for (Team team : teams) {
                String sqlQueryString = "SELECT * FROM players INNER JOIN rosters ON players.id = rosters.player WHERE rosters.team=? ORDER BY name ASC;";
                PreparedStatement statement = database.prepareStatement(sqlQueryString);
                statement.setString(1, team.getId());

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    // Create player

                    Player player = new Player(rs.getInt("id"), rs.getString("name"), rs.getString("position"),
                            rs.getString("height"), rs.getInt("weight"), properDateFormat.format(playerDateInFormat.parse(rs.getString("dob"))));

                    // Add player to list of all players
                    list.add(player);
                    // Add player to their team
                    team.addPlayer(player);
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void loadPlayerStats(ObservableList<PlayerStats> list, ObservableList<Team> teams) {
        try {
            // Loop through all the teams, getting all the stats for all the players on their roster
            for (Team team : teams) {
                String sqlQueryString = "SELECT * FROM player_stats NATURAL JOIN rosters WHERE rosters.team=?;";
                PreparedStatement statement = database.prepareStatement(sqlQueryString);
                statement.setString(1, team.getId());

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    // Create player stats object
                    PlayerStats stats = new PlayerStats(rs.getInt("player"), rs.getString("team"), rs.getInt("games_played"),
                            rs.getInt("goals"), rs.getInt("assists"), rs.getInt("points"), rs.getInt("plus_minus"),
                            rs.getInt("pim"), rs.getInt("shots"), rs.getInt("toi"), rs.getInt("ppg"), rs.getInt("shg"),
                            rs.getInt("gwg"), rs.getInt("otg"));
                    // Add stats to list of all stats
                    list.add(stats);
                    // Add stats to the player
                    team.getPlayer(stats.getPlayer()).addStats(stats);
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Player> searchPlayers(String query, ObservableList<Team> teams) {
        ObservableList<Player> list = FXCollections.observableArrayList();
        try {

            String sqlQueryString = "SELECT id FROM players WHERE name LIKE ?;";

            PreparedStatement statement = database.prepareStatement(sqlQueryString);
            statement.setString(1, "%" + query + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                for (Team t : teams) {
                    Player p = t.getPlayer(rs.getInt("id"));
                    if (p != null) {
                        list.add(p);
                        break;
                    }
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<Team> searchTeams(String query, ObservableList<Conference> conferences) {
        ObservableList<Team> list = FXCollections.observableArrayList();
        try {
            String sqlQueryString = "SELECT id FROM teams WHERE name LIKE ?;";

            PreparedStatement statement = database.prepareStatement(sqlQueryString);
            statement.setString(1, "%" + query + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                for (Conference conference : conferences) {
                    Team t = conference.getTeam(rs.getString("id"));
                    if (t != null) {
                        list.add(t);
                        break;
                    }
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void loadGameResults(ObservableList<Team> teams, ObservableList<Game> games) {
        try {
            // Loop through all the teams, getting all the stats for all the players on their roster
            for (Team team : teams) {
                String sqlQueryString = "SELECT * FROM matches WHERE home_team=? OR away_team=?";
                PreparedStatement statement = database.prepareStatement(sqlQueryString);
                statement.setString(1, team.getId());
                statement.setString(2, team.getId());

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    // Create player stats object
                    Game game = new Game(rs.getInt("id"), rs.getString("home_team"), rs.getString("away_team"),
                            rs.getInt("home_goals"), rs.getInt("away_goals"), properDateFormat.format(gameDateInFormat.parse(rs.getString("date"))));

                    team.addGame(game);
                    games.add(game);
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void calculateTeamStandings(ObservableList<Team> teams) {
        try {
            for (Team team : teams) {

                String sqlWinsQuery = "select count(*) as 'wins' from matches WHERE (home_team=? AND home_goals > away_goals) OR (away_team=? AND away_goals > home_goals);";
                PreparedStatement winsStatement = database.prepareStatement(sqlWinsQuery);
                winsStatement.setString(1, team.getId());
                winsStatement.setString(2, team.getId());

                String sqlLossesQuery = "select count(*) as 'losses' from matches WHERE (home_team=? AND home_goals < away_goals) OR (away_team=? AND away_goals < home_goals);";
                PreparedStatement lossesStatement = database.prepareStatement(sqlLossesQuery);
                lossesStatement.setString(1, team.getId());
                lossesStatement.setString(2, team.getId());

                ResultSet winsRs = winsStatement.executeQuery();
                ResultSet lossesRs = lossesStatement.executeQuery();

                winsRs.next();
                lossesRs.next();

                team.setWins(winsRs.getInt("wins"));
                team.setLosses(lossesRs.getInt("losses"));

                winsRs.close();
                lossesRs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
