
public class TitanicPassenger {
    
    int pclass, survived;
    String name, sex;
    
    
    
    public TitanicPassenger(int pclass, int survived, String name, String sex, double age, double fare) {
        super();
        this.pclass = pclass;
        this.survived = survived;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.fare = fare;
    }
    
    
    public int getPclass() {
        return pclass;
    }
    public void setPclass(int pclass) {
        this.pclass = pclass;
    }
    public int getSurvived() {
        return survived;
    }
    public void setSurvived(int survived) {
        this.survived = survived;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public double getAge() {
        return age;
    }
    public void setAge(double age) {
        this.age = age;
    }
    public double getFare() {
        return fare;
    }
    public void setFare(double fare) {
        this.fare = fare;
    }
    double age, fare;

}
