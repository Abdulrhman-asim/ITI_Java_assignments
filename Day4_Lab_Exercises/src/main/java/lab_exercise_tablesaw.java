

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import static tech.tablesaw.aggregate.AggregateFunctions.*;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.selection.Selection;

public class lab_exercise_tablesaw {
    public static void main(String[] args) throws IOException {

        
        Table data_tblsw = Table.read().csv("src\\main\\resources\\titanic.csv");
        
        

        System.out.println("\n** Showing 5 rows **");
        System.out.println(data_tblsw.sampleN(5));
        
        System.out.println("\n** Showing data summary **");
        System.out.println(data_tblsw.summary());

        DoubleColumn tmpAges = data_tblsw.doubleColumn("age");

        System.out.println("\n** Showing data after adding fake \"Num of pets\" column **");

        IntColumn numOfPets  = IntColumn.create("Num_of_Pets");
        
        Random rand = new Random();
        for (int i = 0; i < tmpAges.size(); i++) {
            
            numOfPets.append(Math.abs(rand.nextInt()) % 4);

        }
        
        data_tblsw.addColumns(numOfPets);

        System.out.println(data_tblsw.sampleN(5));
        
        ArrayList<Double> ages = new ArrayList<Double>(tmpAges.removeMissing().asList());

        System.out.println("\n** Number of missing age values **");
        System.out.println(tmpAges.size() - ages.size());

        double maxAge = ages.stream().max((x, y) -> Double.compare(x, y)).get();
        double minAge = ages.stream().min((x, y) -> Double.compare(x, y)).get();

        System.out.println("\n** Max and Min ages **");
        System.out.println("Max Age: " + maxAge + "\n" + "Min Age: " + minAge);

        System.out.println("\n** Average of survivors By class **");
        Table survByClass = data_tblsw.copy().retainColumns("pclass", "survived").summarize("survived", mean).by("pclass");

        System.out.println(survByClass);

        System.out.println("\n** Average of survivors By gender**");
        Table survByGender = data_tblsw.copy().retainColumns("sex", "survived").summarize("survived",mean).by("sex");

        System.out.println(survByGender);
        
        System.out.println("\n** Joining two tables **");

        StringColumn  t1Uniq = data_tblsw.column("name").unique().asStringColumn();
        Selection names = t1Uniq.isIn(t1Uniq.asList());

        Table t1 = data_tblsw.copy().retainColumns("name", "survived").dropRowsWithMissingValues().where(names);
        Table t2 = data_tblsw.copy().retainColumns("name", "sex", "pclass").dropRowsWithMissingValues().where(names);
        
        System.out.println("\n** Tables to be joined (On \"name\") **");
        
        System.out.println(t1.print(5));
        System.out.println(t2.print(5));
        
        System.out.println("\n** Joint Table **");
        
        Table t3 = t1.joinOn("name").inner(t2);
        
        System.out.println(t3.print(5));
        
        
        
        
        
        
        
        
        
        

    }

}
