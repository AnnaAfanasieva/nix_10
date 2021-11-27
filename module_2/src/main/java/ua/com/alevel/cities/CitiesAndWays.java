package ua.com.alevel.cities;

import java.util.ArrayList;
import java.util.List;

public class CitiesAndWays {

    private List<City> cities = new ArrayList<>();
    private int numberOfWaysSought;
    private String[][] soughtWays;

    public CitiesAndWays(List<City> cities, int numberOfWaysSought, String[][] soughtWays) {
        this.cities = cities;
        this.numberOfWaysSought = numberOfWaysSought;
        this.soughtWays = soughtWays;
    }

    public List<City> getCities() {
        return cities;
    }

    public int getNumberOfWaysSought() {
        return numberOfWaysSought;
    }

    public String[][] getSoughtWays() {
        return soughtWays;
    }
}
