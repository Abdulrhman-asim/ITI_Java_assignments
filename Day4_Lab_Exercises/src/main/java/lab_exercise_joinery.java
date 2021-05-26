import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.lang.Math;
import joinery.DataFrame;
import joinery.DataFrame.JoinType;

public class lab_exercise_joinery {

    public static void main(String[] args) throws IOException {
        
        DataFrame<Object> data_jnry = DataFrame.readCsv("src\\main\\resources\\titanic.csv");
        
        System.out.println("\n** Showing top 5 rows **");
        
        System.out.println(data_jnry.head(5));
        

        System.out.println("\n** Showing data description **");
        System.out.println(data_jnry.describe());
        
        List<Object> tmpAges = data_jnry.col("age");
        
        System.out.println("\n** Showing data after adding fake \"Num of pets\" column **");
        
        
        List<Object> tmpNumOfPets = new ArrayList<Object>();
        Random rand = new Random();
        for(int i=0; i< tmpAges.size(); i++) {
            
            tmpNumOfPets.add(Math.abs(rand.nextInt()) % 4);
            
        }
        
        data_jnry.add("Num_Of_Pets", tmpNumOfPets);
        
        
        System.out.println(data_jnry.head(5));
        
        
        ArrayList<Double> ages = new ArrayList<Double>(tmpAges.stream()
                .filter(x -> x != null)
                .map(Object::toString)
                .map(Double::valueOf).collect(Collectors.toList()));
        
        System.out.println("\n** Number of missing age values **");
        System.out.println(tmpAges.size() - ages.size());
        
        
        
        
        
        double maxAge = ages.stream().max((x,y) -> Double.compare(x, y)).get();
        double minAge = ages.stream().min((x,y) -> Double.compare(x, y)).get();
        
        System.out.println("\n** Max and Min ages **");
        System.out.println("Max Age: " + maxAge + "\n" + "Min Age: " + minAge);
        
        System.out.println("\n** Average of survivors By class **");
        DataFrame<Object> survByClass = data_jnry.retain("pclass", "survived").groupBy(row -> row.get(0)).mean();
        
        System.out.println(survByClass);
        
        System.out.println("\n** Average of survivors By gender**");
        DataFrame<Object> survByGender = data_jnry.retain("sex", "survived").groupBy(row -> row.get(1)).mean();
        
        System.out.println(survByGender);
        
        
        System.out.println("\n** Joining two tables **");

        DataFrame<Object> t1 = data_jnry.retain("name", "survived").dropna().groupBy("survived").unique("name");
        DataFrame<Object> t2 = data_jnry.retain("name", "sex", "pclass").dropna().groupBy("sex", "pclass").unique("name");

        
        System.out.println("\n** Tables to be joined (On \"name\") **");
        
        System.out.println(t1.head(5));
        System.out.println(t2.head(5));
        
        System.out.println("\n** Joint Table **");
        
        DataFrame<Object> t3 = t1.joinOn(t2, JoinType.INNER, "name").resetIndex();
       
        System.out.println(t3.head(5));
        
        
        
        

    }

}
