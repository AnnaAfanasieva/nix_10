package ua.com.alevel.cities;

import java.util.ArrayList;
import java.util.List;

public class FindingTheCheapestWay {

    private static List<City> cities = new ArrayList<>();
    private static int numberOfWaysSought;
    private static String[][] soughtWays;

    public static void main(String[] args) {
        getAllInformation();
        int firstCityID;
        int secondCityID;

        for (int i = 0; i < numberOfWaysSought; i++) {
            firstCityID = getCityID(soughtWays[i][0]);
            secondCityID = getCityID(soughtWays[i][1]);
            if (firstCityID == secondCityID) {
                System.out.println("Ошибка при записи искомых путей - нельзя искать стоимость из города в тот же город");
                System.exit(0);
            } else if (firstCityID != 0 && secondCityID != 0) {
                System.out.println("Стоимость пути из " + cities.get(firstCityID - 1).getName() + " в " + cities.get(secondCityID - 1).getName() + " = " + findWay(firstCityID, secondCityID));
            } else {
                System.out.println("Ошибка при записи искомых путей - город не найден в списке");
                System.exit(0);
            }
        }
    }

    private static void getAllInformation() {
        CitiesAndWays citiesAndWays = ReadFromFile.readFromFileCities();
        cities = citiesAndWays.getCities();
        numberOfWaysSought = citiesAndWays.getNumberOfWaysSought();
        soughtWays = citiesAndWays.getSoughtWays();
    }

    private static int getCityID(String cityName) {
        for (City city : cities) {
            if (cityName.equals(city.getName())) {
                return city.getCityID();
            }
        }
        return 0;
    }

    private static int findWay(int firstCityID, int secondCityID) {
        int allCities[][] = new int[cities.size()][2];
        for (int i = 0; i < cities.size(); i++) {
            allCities[i][0] = -1;
            allCities[i][1] = 0;
        }
        allCities[firstCityID - 1][0] = 0;

        int currentCityId = firstCityID - 1;
        boolean allWaysFind = false;
        while (!allWaysFind) {
            allCities[currentCityId][1] = 1;

            boolean allCitiesVisited = true;
            for (int i = 0; i < cities.size(); i++) {
                if (allCities[i][1] == 0) {
                    allCitiesVisited = false;
                }
            }

            if (allCitiesVisited) {
                allWaysFind = true;
                return allCities[secondCityID - 1][0];
            }

            List<CostTable> costTables = cities.get(currentCityId).getCostTables();
            for (CostTable costTable : costTables) {
                int neighborID = costTable.getEndCity() - 1;
                int currentCostInThisCity = allCities[neighborID][0];
                if (allCities[neighborID][1] != 1 && (currentCostInThisCity == -1 || currentCostInThisCity > costTable.getCost() + allCities[currentCityId][0])) {
                    allCities[neighborID][0] = costTable.getCost() + allCities[currentCityId][0];
                }
            }

            int minCost = 0;
            int indexNextCity = 0;
            for (int i = 0; i < cities.size(); i++) {
                if (allCities[i][1] != 1 && allCities[i][0] != -1) {
                    int currentCost = allCities[i][0] + allCities[currentCityId][0];
                    if (minCost == 0) {
                        minCost = currentCost;
                        indexNextCity = i;
                    } else if (minCost > currentCost) {
                        minCost = currentCost;
                        indexNextCity = i;
                    }
                }
            }
            currentCityId = indexNextCity;
        }
        return 0;
    }
}