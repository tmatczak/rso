package com.example.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tobiasz on 2016-06-07.
 */
public class Data42 {
    private final SimpleStringProperty uniName;
    private final SimpleStringProperty uniYear;
    private final SimpleStringProperty uniLocation;
    private final SimpleStringProperty uniType;
    private final SimpleStringProperty name;
    private final SimpleStringProperty location;
    private final SimpleStringProperty comeFrom;
    private final SimpleIntegerProperty value;
    private final SimpleIntegerProperty village;
    private final SimpleIntegerProperty smallTown;
    private final SimpleIntegerProperty mediumTown;
    private final SimpleIntegerProperty largeTown;

    public Data42(String uniName, String uniYear, String uniLocation, String uniType,
                  String name, String location, String comeFrom, int value,
                  int village, int smallTown, int mediumTown, int largeTown) {
        this.uniName = new SimpleStringProperty(uniName);
        this.uniYear = new SimpleStringProperty(uniYear);
        this.uniLocation = new SimpleStringProperty(uniLocation);
        this.uniType = new SimpleStringProperty(uniType);
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
        this.comeFrom = new SimpleStringProperty(comeFrom);
        this.value = new SimpleIntegerProperty(value);
        this.village = new SimpleIntegerProperty(village);
        this.smallTown = new SimpleIntegerProperty(smallTown);
        this.mediumTown = new SimpleIntegerProperty(mediumTown);
        this.largeTown = new SimpleIntegerProperty(largeTown);
    }

    public String getUniName() {
        return uniName.get();
    }

    public SimpleStringProperty uniNameProperty() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName.set(uniName);
    }

    public String getUniYear() {
        return uniYear.get();
    }

    public SimpleStringProperty uniYearProperty() {
        return uniYear;
    }

    public void setUniYear(String uniYear) {
        this.uniYear.set(uniYear);
    }

    public String getUniLocation() {
        return uniLocation.get();
    }

    public SimpleStringProperty uniLocationProperty() {
        return uniLocation;
    }

    public void setUniLocation(String uniLocation) {
        this.uniLocation.set(uniLocation);
    }

    public String getUniType() {
        return uniType.get();
    }

    public SimpleStringProperty uniTypeProperty() {
        return uniType;
    }

    public void setUniType(String uniType) {
        this.uniType.set(uniType);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getValue() {
        return value.get();
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getComeFrom() {
        return comeFrom.get();
    }

    public SimpleStringProperty comeFromProperty() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom.set(comeFrom);
    }


    public int getVillage() {
        return village.get();
    }

    public SimpleIntegerProperty villageProperty() {
        return village;
    }

    public void setVillage(int village) {
        this.village.set(village);
    }

    public int getSmallTown() {
        return smallTown.get();
    }

    public SimpleIntegerProperty smallTownProperty() {
        return smallTown;
    }

    public void setSmallTown(int smallTown) {
        this.smallTown.set(smallTown);
    }

    public int getMediumTown() {
        return mediumTown.get();
    }

    public SimpleIntegerProperty mediumTownProperty() {
        return mediumTown;
    }

    public void setMediumTown(int mediumTown) {
        this.mediumTown.set(mediumTown);
    }

    public int getLargeTown() {
        return largeTown.get();
    }

    public SimpleIntegerProperty largeTownProperty() {
        return largeTown;
    }

    public void setLargeTown(int largeTown) {
        this.largeTown.set(largeTown);
    }
}
