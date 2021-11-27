package ua.com.alevel.cities;

public class CostTable {
    private int startCity;
    private int endCity;
    private int cost;

    public void setStartCity(int startCity) {
        this.startCity = startCity;
    }

    public int getEndCity() {
        return endCity;
    }

    public void setEndCity(int endCity) {
        this.endCity = endCity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "CostTable{" +
                "startCity=" + startCity +
                ", endCity=" + endCity +
                ", cost=" + cost +
                '}';
    }
}
