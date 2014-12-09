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

package pool.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pool.Main;
import pool.model.Conference;
import pool.model.Division;
import pool.model.Game;
import pool.model.Team;

public class TeamViewController {

    @FXML
    private TableView<Team> teamTable;
    @FXML
    private TableColumn<Team, String> teamNameColumn;
    @FXML
    private TableColumn<Team, String> teamWinsColumn;
    @FXML
    private TableColumn<Team, String> teamLossesColumn;

    @FXML
    private TableView<Game> resultsTable;
    @FXML
    private TableColumn<Game, String> homeTeamColumn;
    @FXML
    private TableColumn<Game, Integer> homeTeamGoalsColumn;
    @FXML
    private TableColumn<Game, String> awayTeamColumn;
    @FXML
    private TableColumn<Game, Integer> awayTeamGoalsColumn;
    @FXML
    private TableColumn<Game, String> dateGameColumn;

    @FXML
    private TableView<Conference> conferenceTable;
    @FXML
    private TableColumn<Conference, String> conferenceColumn;

    @FXML
    private TableView<Division> divisionTable;
    @FXML
    private TableColumn<Division, String> divisionColumn;

    @FXML
    private TextField txtSearch;

    // Reference to the main application
    private Main main;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
        teamWinsColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("wins"));
        teamLossesColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("losses"));

        homeTeamColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("homeTeam"));
        awayTeamColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("awayTeam"));
        homeTeamGoalsColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("homeGoals"));
        awayTeamGoalsColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("awayGoals"));

        dateGameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("date"));

        conferenceColumn.setCellValueFactory(new PropertyValueFactory<Conference, String>("name"));
        divisionColumn.setCellValueFactory(new PropertyValueFactory<Division, String>("name"));
    }

    @FXML
    private void onSearchChanged() {
        teamTable.setItems(main.getDbManager().searchTeams(txtSearch.getText(), main.getConferenceList()));
    }

    @FXML
    private void onTeamSelected() {
        if (teamTable.getSelectionModel().getSelectedItem() == null)
            resultsTable.setItems(main.getGamesList());
        else
            resultsTable.setItems(teamTable.getSelectionModel().getSelectedItem().getGames());
    }

    @FXML
    private void onResetClick() {
        teamTable.setItems(main.getTeamsList());
        divisionTable.setItems(main.getDivisionList());
        conferenceTable.setItems(main.getConferenceList());

        teamTable.getSelectionModel().clearSelection();
        divisionTable.getSelectionModel().clearSelection();
        conferenceTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void onConferenceSelected() {
        if (conferenceTable.getSelectionModel().getSelectedItem() == null) {
            divisionTable.setItems(main.getDivisionList());
            teamTable.setItems(main.getTeamsList());
        } else {
            divisionTable.setItems(conferenceTable.getSelectionModel().getSelectedItem().getDivisions());
            teamTable.setItems(conferenceTable.getSelectionModel().getSelectedItem().getTeams());
        }
    }

    @FXML
    private void onDivisionSelected() {
        if (divisionTable.getSelectionModel().getSelectedItem() == null)
            teamTable.setItems(main.getTeamsList());
        else
            teamTable.setItems(divisionTable.getSelectionModel().getSelectedItem().getTeams());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main Reference to the main application
     */
    public void setMainApp(Main main) {
        this.main = main;
        teamTable.setItems(main.getTeamsList());
        resultsTable.setItems(main.getGamesList());
        conferenceTable.setItems(main.getConferenceList());
        divisionTable.setItems(main.getDivisionList());
    }

}
