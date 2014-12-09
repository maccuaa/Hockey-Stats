//==============================================================================
//
//     Hockey-Stats
//     Copyright (C) 2014  Andrew MacCuaig
//     https://github.com/st-andrew/Hockey-Stats
//
//==============================================================================

package pool.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Player {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty position;
    private final StringProperty height;
    private final IntegerProperty weight;
    private final StringProperty dob;
    private final ObservableList<PlayerStats> stats;

    public Player(Integer id, String name, String position, String height, Integer weight, String dob) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.height = new SimpleStringProperty(height);
        this.weight = new SimpleIntegerProperty(weight);
        this.dob = new SimpleStringProperty(dob);
        this.stats = FXCollections.observableArrayList();
    }

    public Player(Integer id, String name, String position, String height, Integer weight, String dob, PlayerStats stats) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.height = new SimpleStringProperty(height);
        this.weight = new SimpleIntegerProperty(weight);
        this.dob = new SimpleStringProperty(dob);
        this.stats = FXCollections.observableArrayList(stats);
    }

    public void addStats(PlayerStats stat) {
        stats.add(stat);
    }

    public PlayerStats getStats(String team) {
        for (PlayerStats stat : stats) {
            if (stat.getTeam().equals(team)) {
                return stat;
            }
        }
        return null;
    }

    public ObservableList<PlayerStats> getStats() {
        return stats;
    }

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public String getHeight() {
        return height.get();
    }

    public StringProperty heightroperty() {
        return height;
    }

    public Integer getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public String getStringOfBirth() {
        return dob.get();
    }

    public StringProperty dateOfBirthProperty() {
        return dob;
    }

}
