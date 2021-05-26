package lab1_3;

public class City {

    private String name, continent;
    private int pop, country_code;
    
    public City(String name, String continent, int pop, int country_code) {
        super();
        this.name = name;
        this.continent = continent;
        this.pop = pop;
        this.country_code = country_code;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContinent() {
        return continent;
    }
    public void setContinent(String continent) {
        this.continent = continent;
    }
    public int getPop() {
        return pop;
    }
    public void setPop(int pop) {
        this.pop = pop;
    }
    public int getCountry_code() {
        return country_code;
    }
    public void setCountry_code(int country_code) {
        this.country_code = country_code;
    }
}
