package lab4;

public class Pyramid {
    
    String pharaoh, modernName;
    double height_m;
    int site;
    
    
    
    public Pyramid(String pharaoh, String modernName, int site, double height_m) {
        super();
        this.pharaoh = pharaoh;
        this.modernName = modernName;
        this.site = site;
        this.height_m = height_m;
    }
    
    

    public String getPharaoh() {
        return pharaoh;
    }
    public void setPharaoh(String pharaoh) {
        this.pharaoh = pharaoh;
    }
    public String getModernName() {
        return modernName;
    }
    public void setModernName(String modernName) {
        this.modernName = modernName;
    }
    public int getSite() {
        return site;
    }
    public void setSite(int site) {
        this.site = site;
    }
    public double getHeight_m() {
        return height_m;
    }
    public void setHeight_m(double height_m) {
        this.height_m = height_m;
    }
    
    
}
