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
                System.out.println("The cheapest way = " + findWay(firstCityID, secondCityID, firstCityID));
                resetValues();
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

    private static void resetValues() {
        currentPrice = 0;
        visitedCities.clear();
        wayHaveFinish = true;
        theCheapestPrice = 0;
    }

    private static List<Integer> visitedCities = new ArrayList<>();
    private static List<Way> allWays = new ArrayList<>();
    private static int theCheapestPrice = 0;
    private static int currentPrice = 0;
    private static boolean wayHaveFinish = true;


    private static int findWay(int firstCityID, int secondCityID, int currentCityId) {
        System.out.println("Сейчас мы в городе с ID = " + currentCityId);
        /**
         * Добавляем текущий город в список посещенных
         */
        visitedCities.add(currentCityId);
        /**
         * Если мы уже прошли все города, то завершаем выполнение
         */
        if (cities.size() == visitedCities.size() && currentCityId != secondCityID) {
            wayHaveFinish = false;
            //resetValues();
            return 0;
        }
        /**
         * Берем у города таблицу с его соседями
         */
        List<CostTable> costTables = cities.get(currentCityId - 1).getCostTables();
        /**
         * Проходимся по всем соседям
         */
        for (CostTable costTable : costTables) {
            /**
             * Присваиваем буферной переменной значение соседа
             */
            int nextCityID = costTable.getEndCity();
            boolean isNewPoint = true;

            /**
             * Если город в таблице - наш искомый, то мы
             * 1. присваиваем currentPrice итоговое значение
             * 2. Если currentPrice меньше theCheapestPrice или theCheapestPrice равен нулю, то присваиваем theCheapestPrice текущую стоимость
             */
            if (nextCityID == secondCityID) {
                currentPrice += costTable.getCost();
                if (currentPrice < theCheapestPrice || theCheapestPrice == 0) {
                    theCheapestPrice = currentPrice;
                }
                return costTable.getCost();
            } else {
                /**
                 * Проверяем, есть ли сосед в посещенных городах
                 */
                for (int visitedCity : visitedCities) {
                    if (visitedCity == nextCityID) {
                        isNewPoint = false;
                    }
                }

                /**
                 * - если сосед ЕСТЬ в списке посещенных городов, то идем дальше (пропускаем его)
                 * - если соседа НЕТУ в списке посещенных городов, то добавляем стоимость поездки туда
                 *      + вызываем рекурсию
                 */
                if (isNewPoint) {
                    currentPrice += findWay(firstCityID, secondCityID, nextCityID);
                    return costTable.getCost() + currentPrice;
                }
            }
        }

        System.out.println(firstCityID + " -> " + secondCityID + " = " + theCheapestPrice);
        return 0;
    }
}