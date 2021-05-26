package lab1_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

public class CityOperator {

    private HashMap<Integer, List<City>> citiesByCountry = new HashMap<>();
    private HashMap<String, List<City>> citiesByContinent = new HashMap<>();
    private List<Country> countries;

    public CityOperator(List<City> cities, List<Country> countries) {

        this.countries = countries;

        Collections.sort(countries, (c1, c2) -> Integer.compare(c1.getCountry_code(), c2.getCountry_code()));

        for (Country c : this.countries) {

            citiesByCountry.put(c.getCountry_code(), new ArrayList<City>());

        }

        for (City cty : cities) {

            cty.setContinent(cty.getContinent().toLowerCase());

            if (citiesByCountry.containsKey(cty.getCountry_code())) {
                citiesByCountry.get(cty.getCountry_code()).add(cty);
            } else {
                System.out.println(cty.getName() + " wasn't added since it's country code " + "doesn't exist");

            }

            if (!citiesByContinent.containsKey(cty.getContinent())) {

                citiesByContinent.put(cty.getContinent(), new ArrayList<City>());
            }

            citiesByContinent.get(cty.getContinent()).add(cty);
        }

    }

    public void citiesSorter(int country_code) {

        if (citiesByCountry.containsKey(country_code)) {
            Collections.sort(citiesByCountry.get(country_code), (c1, c2) -> Integer.compare(c1.getPop(), c2.getPop()));
        } else {
            System.out.println("Country Code inserted doesn't exist");
        }
    }

    public void displayCities() {

        Iterator<Entry<Integer, List<City>>> it = citiesByCountry.entrySet().iterator();
        it.forEachRemaining((countCtyPair) -> {

            System.out.println(countries.get(countCtyPair.getKey() - 1).getName() + ": ");
            for (City c : countCtyPair.getValue()) {
                System.out.print(c.getName() + ", " + c.getPop() + "| ");

            }
            System.out.println();
        });

    }

    public void highestCityPopInCountry() {

        Iterator<Entry<Integer, List<City>>> it = citiesByCountry.entrySet().iterator();
        it.forEachRemaining((countCtyPair) -> {

            try {
                City maxPop = countCtyPair.getValue().stream()
                        .max((City c1, City c2) -> Integer.compare(c1.getPop(), c2.getPop())).get();

                System.out.print(countries.get(countCtyPair.getKey() - 1).getName() + ": " + maxPop.getName());

                System.out.println();
            } catch (NoSuchElementException e) {
                System.out
                        .println(countries.get(countCtyPair.getKey() - 1).getName() + " has no cities in the dataset");
            }
        });

    }

    public void highestCityPopInContinent() {

        Iterator<Entry<String, List<City>>> it = citiesByContinent.entrySet().iterator();
        it.forEachRemaining((continentCtyPair) -> {

            try {
                City maxPop = continentCtyPair.getValue().stream()
                        .max((City c1, City c2) -> Integer.compare(c1.getPop(), c2.getPop())).get();

                System.out.print(continentCtyPair.getKey() + ": " + maxPop.getName());

                System.out.println();
            } catch (NoSuchElementException e) {
                System.out
                .println(continentCtyPair.getKey() + " has no cities in the dataset");
            }
        });

    }

    public ArrayList<Double> dataStats() {

        Iterator<Entry<Integer, List<City>>> it = citiesByCountry.entrySet().iterator();
        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Double> pops = new ArrayList<>();

        it.forEachRemaining((countCtyPair) -> {

            for (City c : countCtyPair.getValue()) {
                pops.add((double) c.getPop());
            }
        });

        int numOfCities = pops.size();
        pops.sort((x, y) -> Double.compare(x, y));

        results.add(pops.stream().reduce(0.0, (x, y) -> x + y) / numOfCities);

        results.add(quartile(pops));

        int splitInd = numOfCities / 2;
        results.add(quartile(pops.subList(0, splitInd)));
        
        if (numOfCities % 2 != 0)
            splitInd++;
        results.add(quartile(pops.subList(splitInd, numOfCities)));

        return results;

    }

    private double quartile(List<Double> arr) {

        int numOfCities = arr.size();
        double median = numOfCities % 2 == 0
                ? (arr.get((int) (numOfCities / 2)) + arr.get((int) (numOfCities / 2) - 1)) / 2
                : arr.get((int) (numOfCities / 2));

        return median;

    }
}
