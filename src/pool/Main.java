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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pool.model.*;
import pool.view.PlayerViewController;
import pool.view.TeamViewController;
import pool.view.UIController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private DBManager dbManager;

    private ObservableList<Player> playerList = FXCollections.observableArrayList();
    private ObservableList<PlayerStats> playerStatsList = FXCollections.observableArrayList();
    private ObservableList<Team> teamsList = FXCollections.observableArrayList();
    private ObservableList<Game> gamesList = FXCollections.observableArrayList();
    private ObservableList<Conference> conferenceList = FXCollections.observableArrayList();
    private ObservableList<Division> divisionList = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Main() {
        dbManager = new DBManager();
        dbManager.openDB();
        dbManager.getConferencesAndDivisions(divisionList, conferenceList);
        dbManager.loadTeams(teamsList, divisionList);
        dbManager.loadPlayers(playerList, teamsList);
        dbManager.loadPlayerStats(playerStatsList, teamsList);
        dbManager.loadGameResults(teamsList, gamesList);
        dbManager.calculateTeamStandings(teamsList);
    }

    public ObservableList<Player> getPlayerList() {
        return playerList;
    }

    public ObservableList<PlayerStats> getPlayerStatsList() {
        return playerStatsList;
    }

    public ObservableList<Team> getTeamsList() {
        return teamsList;
    }

    public ObservableList<Game> getGamesList() {
        return gamesList;
    }

    public ObservableList<Conference> getConferenceList() {
        return conferenceList;
    }

    public ObservableList<Division> getDivisionList() {
        return divisionList;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hockey Stats");

        initRootLayout();

        showPlayerOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ui.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            UIController uiController = loader.getController();
            uiController.setMainApp(this);

            primaryStage.setScene(new Scene(rootLayout, 800, 600));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPlayerOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/playerView.fxml"));
            BorderPane playerView = loader.load();

            rootLayout.setCenter(playerView);

            // Give the controller access to the main app.
            PlayerViewController playerViewController = loader.getController();
            playerViewController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTeamOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/teamView.fxml"));
            BorderPane teamView = loader.load();

            rootLayout.setCenter(teamView);

            // Give the controller access to the main app.
            TeamViewController teamViewController = loader.getController();
            teamViewController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
     * @return Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns the dbManager
     *
     * @return dbManagere
     */
    public DBManager getDbManager() {
        return dbManager;
    }

    @Override
    public void stop() {
        dbManager.closeDB();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
