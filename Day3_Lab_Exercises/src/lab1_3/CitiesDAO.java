package lab1_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CitiesDAO {

    public static List<City> readCitiesFromCSV(String fileName) throws FileNotFoundException {

        Scanner sc;
        List<City> Cities = new ArrayList<>();
        sc = new Scanner(new File(fileName));
        sc.nextLine();
        while (sc.hasNext()) {

            String data = sc.nextLine();
            String[] meta = data.split(",");
            
            Cities.add(createCity(meta));

        }

        sc.close();
        return Cities;

    }

    private static City createCity(String[] metaData) {

        return new City(metaData[1], metaData[2], Integer.parseInt(metaData[3]), Integer.parseInt(metaData[0]));

    }

}
