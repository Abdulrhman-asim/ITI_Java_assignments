import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        PyramidCSVDAO pyrmd_csv_reader = new PyramidCSVDAO();
        List<Pyramid> pyramids = pyrmd_csv_reader.readPyramidsFromCSV("pyramids.csv");
        String sep = "-".repeat(100); 
        System.out.println(String.format("%-20s | %-50s | %-4s | %-20s", "Pharaoh", "Modern Name", "Site", "Height (meters)"));
        System.out.println(sep);
        for(Pyramid p: pyramids) {
            
            System.out.println(String.format("%-20s | %-50s | %-4s | %-20s", p.getPharaoh(), p.getModernName(), p.getSite(), p.getHeight_m()));
        }
        
        System.out.println("\n## (-1) is used inplace of missing values ##");

    }

}
