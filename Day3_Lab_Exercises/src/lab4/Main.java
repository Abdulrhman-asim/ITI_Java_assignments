package lab4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        PyramidCSVDAO pyrmd_csv_reader = new PyramidCSVDAO();
        List<Pyramid> pyramids = pyrmd_csv_reader.readPyramidsFromCSV("pyramids.csv");
        String sep = "-".repeat(100);
        System.out.println(
                String.format("%-20s | %-50s | %-4s | %-20s", "Pharaoh", "Modern Name", "Site", "Height (meters)"));
        System.out.println(sep);
        for (Pyramid p : pyramids) {

            System.out.println(String.format("%-20s | %-50s | %-4s | %-20s", p.getPharaoh(), p.getModernName(),
                    p.getSite(), p.getHeight_m()));
        }

        ArrayList<Double> stats = dataStats(pyramids);

        System.out.println("Average = " + stats.get(0));
        System.out.println("Q1 = " + stats.get(2));
        System.out.println("Q2 = " + stats.get(1));
        System.out.println("Q3 = " + stats.get(3));
    }

    public static ArrayList<Double> dataStats(List<Pyramid> pyrds) {

        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Double> heights = new ArrayList<>();

        for (Pyramid p : pyrds) {

            heights.add((double) p.getHeight_m());

        }

        int numOfHeights = heights.size();
        heights.sort((x, y) -> Double.compare(x, y));

        results.add(heights.stream().reduce(0.0, (x, y) -> x + y) / numOfHeights);

        results.add(quartile(heights));

        int splitInd = numOfHeights / 2;
        results.add(quartile(heights.subList(0, splitInd)));

        if (numOfHeights % 2 != 0)
            splitInd++;
        results.add(quartile(heights.subList(splitInd, numOfHeights)));

        return results;

    }

    private static double  quartile(List<Double> arr) {

        int numOfCities = arr.size();
        double median = numOfCities % 2 == 0
                ? (arr.get((int) (numOfCities / 2)) + arr.get((int) (numOfCities / 2) - 1)) / 2
                : arr.get((int) (numOfCities / 2));

        return median;

    }

}
