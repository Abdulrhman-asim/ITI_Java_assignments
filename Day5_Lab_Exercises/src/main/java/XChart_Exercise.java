import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

public class XChart_Exercise {

    public static void main(String[] args) throws IOException {

        Table data_tblsw = Table.read().csv("src\\main\\resources\\titanic.csv");

        System.out.println("\n** Showing 5 rows **");
        System.out.println(data_tblsw.sampleN(5));

        ArrayList<TitanicPassenger> passengers = new ArrayList<TitanicPassenger>();
        for (Row r : data_tblsw) {
            passengers.add(new TitanicPassenger(r.getInt("pclass"), r.getInt("survived"), r.getString("name"),
                    r.getString("sex"), r.getDouble("age"), r.getDouble("fare")));
        }

        graphPassengerAges(passengers);
        graphPassengerClass(passengers);

    }

    public static void graphPassengerAges(ArrayList<TitanicPassenger> passengerList) {

        List<Double> pAges = passengerList.stream().map(TitanicPassenger::getAge).limit(8).collect(Collectors.toList());
        List<String> pNames = passengerList.stream().map(TitanicPassenger::getName).limit(8)
                .collect(Collectors.toList());
        String[] names = new String[pNames.size()];
        Double ages[] = new Double[pAges.size()];
        ages = pAges.toArray(ages);
        names = pNames.toArray(names);

        
        CategoryChart chart = new CategoryChartBuilder().width(1024).height(768).title("Age Histogram")
                .xAxisTitle("Names").yAxisTitle("Age").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);

        chart.addSeries("Passenger's Ages", pNames, pAges);
        new SwingWrapper(chart).displayChart();

    }

    public static void graphPassengerClass(ArrayList<TitanicPassenger> passengerList) {
        
        Map<Integer, Long> result = passengerList.stream().collect(Collectors.groupingBy(TitanicPassenger::getPclass, Collectors.counting()));

        PieChart chart = new PieChartBuilder().width(800).height(600).title("PClass Pie Chart").build();

        Color[] sliceColors = new Color[] { new Color(180, 68, 50), new Color(130, 105, 120), new Color(80, 143, 160) };
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("First Class", result.get(1));
        chart.addSeries("Second Class", result.get(2));
        chart.addSeries("Third Class", result.get(3));
        
        new SwingWrapper(chart).displayChart();
    }
}