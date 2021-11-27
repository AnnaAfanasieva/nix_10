package ua.com.alevel.cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReadFromFile {

    private static List<City> cities = new ArrayList<>();
    private static final String PATH = "files/citiesInput.txt";
    private static int numberOfCurrentLine;

    public static CitiesAndWays readFromFileCities() {
        cities.clear();
        numberOfCurrentLine = 1;
        BufferedReader reader;
        int numberOfCities = 0;
        int numberOfWaysSought;
        String[][] soughtWays;

        try {
            reader = new BufferedReader(new FileReader(PATH));
            numberOfCities = Integer.parseInt(reader.readLine());
            numberOfCurrentLine++;
            for (int i = 0; i < numberOfCities; i++) {
                City newCity = new City();
                String cityName = reader.readLine();
                numberOfCurrentLine++;
                int numberOfNeighbors = Integer.parseInt(reader.readLine());
                numberOfCurrentLine++;
                List<CostTable> costTables = new ArrayList<>();

                for (int j = 0; j < numberOfNeighbors; j++) {
                    String[] cityAndCost = stringToArray(reader.readLine());
                    numberOfCurrentLine++;
                    CostTable costTable = new CostTable();
                    int nextCity = Integer.parseInt(cityAndCost[0]);
                    int cost = Integer.parseInt(cityAndCost[1]);
                    costTable.setStartCity(newCity.getCityID());
                    costTable.setEndCity(nextCity);
                    costTable.setCost(cost);

                    costTables.add(costTable);
                }

                newCity.setName(cityName);
                newCity.setNumberOfNeighbors(numberOfNeighbors);
                newCity.setCostTables(costTables);
                cities.add(newCity);
            }

            numberOfWaysSought = Integer.parseInt(reader.readLine());
            numberOfCurrentLine++;
            soughtWays = new String[numberOfWaysSought][2];
            String[] currentWay;
            for (int i = 0; i < numberOfWaysSought; i++) {
                currentWay = stringToArray(reader.readLine());
                numberOfCurrentLine++;
                soughtWays[i][0] = currentWay[0];
                soughtWays[i][1] = currentWay[1];
            }
            CitiesAndWays citiesAndWays = new CitiesAndWays(cities, numberOfWaysSought, soughtWays);
            return citiesAndWays;
        } catch (Exception e) {
            System.out.println("Ошибка при считывании файла в строке " + numberOfCurrentLine);
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    private static String[] stringToArray(String string) {
        String newInput = string.replaceAll(" ", "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }
}
