package lab1_3;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private String name;
    private int country_code;    
    
    
    public Country(String name, int country_code) {
        super();
        this.name = name;
        this.country_code = country_code;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCountry_code() {
        return country_code;
    }
    public void setCounrty_code(int country_code) {
        this.country_code = country_code;
    }
    
}
