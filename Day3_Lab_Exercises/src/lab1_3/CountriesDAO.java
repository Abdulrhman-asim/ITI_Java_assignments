package lab1_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountriesDAO {
    public static List<Country> readCountriesFromCSV(String fileName) throws FileNotFoundException {

        Scanner sc;
        List<Country> Countries = new ArrayList<>();
        sc = new Scanner(new File(fileName));
        sc.nextLine();
        while (sc.hasNext()) {

            String data = sc.nextLine();
            String[] meta = data.split(",");
            
            Countries.add(createCountries(meta));

        }

        sc.close();
        return Countries;

    }

    private static Country createCountries(String[] metaData) {

        return new Country(metaData[1], Integer.parseInt(metaData[0]));

    }
}
