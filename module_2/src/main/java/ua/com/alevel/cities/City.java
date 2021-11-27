package ua.com.alevel.cities;

import java.util.List;

public class City {

    private static int currentID = 0;

    private int cityID;
    private String name;
    private int numberOfNeighbors;
    List<CostTable> costTables;

    public City() {
        cityID = generateCityID();
    }

    public City(String name, int numberOfNeighbors, List<CostTable> costTables) {
        cityID = generateCityID();
        this.name = name;
        this.numberOfNeighbors = numberOfNeighbors;
        this.costTables = costTables;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public List<CostTable> getCostTables() {
        return costTables;
    }

    public void setCostTables(List<CostTable> costTables) {
        this.costTables = costTables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfNeighbors() {
        return numberOfNeighbors;
    }

    public void setNumberOfNeighbors(int numberOfNeighbors) {
        this.numberOfNeighbors = numberOfNeighbors;
    }

    private int generateCityID() {
        currentID++;
        return currentID;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityID=" + cityID +
                ", name='" + name + '\'' +
                ", numberOfNeighbors=" + numberOfNeighbors +
                ", costTables=" + costTables +
                '}';
    }
}
