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
import pool.Main;

public class UIController {

    // Reference to the main application
    private Main main;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    @FXML
    private void onCloseClick() {
        main.getPrimaryStage().close();
    }

    @FXML
    private void onTeamViewSelected() {
        main.showTeamOverview();
    }

    @FXML
    private void onPlayerViewSelected() {
        main.showPlayerOverview();
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main Reference to the main application
     */
    public void setMainApp(Main main) {
        this.main = main;
    }

}
