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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pool.Main;
import pool.model.Player;
import pool.model.PlayerStats;
import pool.model.Team;

public class PlayerViewController {

    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> playerNameColumn;
    @FXML
    private TableColumn<Player, String> playerPositionColumn;
    @FXML
    private TableColumn<Player, String> playerHeightColumn;
    @FXML
    private TableColumn<Player, Integer> playerWeightColumn;
    @FXML
    private TableColumn<Player, String> playerDateOfBirthColumn;

    @FXML
    private TableView<PlayerStats> playerStatsTable;
    @FXML
    private TableColumn<PlayerStats, Integer> statsGamesPlayedColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsGoalsColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsAssistsColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsPointsColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsPlusMinusColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsPenaltyMinutesColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsShotsColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsTimeOnIceColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsPowerPlayGoalsColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsShortHandedGoalsColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsGameWinningGoalsColumn;
    @FXML
    private TableColumn<PlayerStats, Integer> statsOvertimeGoalsColumn;

    @FXML
    private TextField txtSearch;

    @FXML
    private ListView<Team> teamsList;

    // Reference to the main application
    private Main main;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        playerPositionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        playerHeightColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("height"));
        playerWeightColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("weight"));
        playerDateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("dateOfBirth"));

        statsGamesPlayedColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("gamesPlayed"));
        statsGoalsColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("goals"));
        statsAssistsColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("assists"));
        statsPointsColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("points"));
        statsPlusMinusColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("plusMinus"));
        statsPenaltyMinutesColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("penaltyMinutes"));
        statsShotsColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("shots"));
        statsTimeOnIceColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("timeOnIce"));
        statsPowerPlayGoalsColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("powerPlayGoals"));
        statsShortHandedGoalsColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("shortHandedGoals"));
        statsGameWinningGoalsColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("gameWinningGoals"));
        statsOvertimeGoalsColumn.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("overtimeGoals"));

        teamsList.setCellFactory(new Callback<ListView<Team>, ListCell<Team>>() {
            @Override
            public ListCell<Team> call(ListView<Team> p) {
                ListCell<Team> cell = new ListCell<Team>() {
                    @Override
                    protected void updateItem(Team t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getName());
                        }
                    }
                };
                return cell;
            }
        });
    }

    @FXML
    private void onSearchChanged() {
        playerTable.setItems(main.getDbManager().searchPlayers(txtSearch.getText(), main.getTeamsList()));
    }

    @FXML
    private void onResetClick() {
        playerTable.setItems(main.getPlayerList());
        playerStatsTable.setItems(main.getPlayerStatsList());
        teamsList.setItems(main.getTeamsList());

        playerTable.getSelectionModel().clearSelection();
        playerStatsTable.getSelectionModel().clearSelection();
        teamsList.getSelectionModel().clearSelection();
    }

    @FXML
    private void filterPlayerList() {
        if (teamsList.getSelectionModel().getSelectedItem() == null)
            playerTable.setItems((main.getPlayerList()));
        else
            playerTable.setItems(teamsList.getSelectionModel().getSelectedItem().getRoster());
    }

    @FXML
    private void filterStatsList() {
        if (playerTable.getSelectionModel().getSelectedItem() == null)
            playerStatsTable.setItems(main.getPlayerStatsList());
        else {

            playerStatsTable.setItems(playerTable.getSelectionModel().getSelectedItem().getStats());
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main Reference to the main application
     */
    public void setMainApp(Main main) {
        this.main = main;

        playerTable.setItems(main.getPlayerList());
        playerStatsTable.setItems(main.getPlayerStatsList());
        teamsList.setItems(main.getTeamsList());
    }

}
