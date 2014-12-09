//==============================================================================
//
//     Hockey-Stats
//     Copyright (C) 2014  Andrew MacCuaig
//     https://github.com/st-andrew/Hockey-Stats
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
