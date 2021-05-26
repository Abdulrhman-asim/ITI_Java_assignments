package lab4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PyramidCSVDAO {

    public List<Pyramid> readPyramidsFromCSV(String fileName) throws FileNotFoundException {

        Scanner sc;
        List<Pyramid> pyramids = new ArrayList<Pyramid>();
        sc = new Scanner(new File(fileName));
        sc.useDelimiter("\n");
        sc.next();
        while (sc.hasNext()) {

            String data = sc.next();
            String[] meta = data.split(",");
            Pyramid tmpPyrd = createPyramid(meta);
            if(tmpPyrd!=null)
                pyramids.add(tmpPyrd);

        }
        sc.close();
        return pyramids;

    }

    public Pyramid createPyramid(String[] metaData) {
        int site = -1;
        double height = -1;
        try {
            site = Integer.parseInt(metaData[3]);
        } catch (Exception e) {
            return null;
        }
        try {
            height = Double.parseDouble(metaData[7]);
        } catch (Exception e) {
            return null;
        }


        return new Pyramid(metaData[0], metaData[2], site, height);

    }

}
