package lab1_3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/*THIS PACKAGE CONTAINS THE IMPLEMENTATIONS 
FOR LAB EXERCISES 1, 3 and 4 
*/

public class Lab1_3 {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        List<City> cities = CitiesDAO.readCitiesFromCSV("Cities.txt");
        List<Country> countries = CountriesDAO.readCountriesFromCSV("Countries.txt");
        
        CityOperator ctyOp = new CityOperator(cities, countries);
        ctyOp.displayCities();
        
        System.out.println("********");
        System.out.println("After sorting cities in Italy");
        System.out.println("********");
        
        ctyOp.citiesSorter(5);
        ctyOp.displayCities();
        
        
        System.out.println("********");
        System.out.println("Highest Population By Continent");
        System.out.println("********");
        
        ctyOp.highestCityPopInContinent();
        
        System.out.println("********");
        System.out.println("Highest Population By Country");
        System.out.println("********");
        
        ctyOp.highestCityPopInCountry();
        
        System.out.println("********");
        System.out.println("Population data Quartiles");
        System.out.println("********");
        
        ArrayList<Double> stats= ctyOp.dataStats();
        
        System.out.println("Average = " + stats.get(0));
        System.out.println("Q1 = " + stats.get(2));
        System.out.println("Q2 = " + stats.get(1));
        System.out.println("Q3 = " + stats.get(3));
        
        
        
        
        
        
    }
    
    
}
