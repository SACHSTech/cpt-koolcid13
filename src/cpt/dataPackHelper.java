package cpt;

public class dataPackHelper {
    
    private String country;
    private int year;
    private double allAge;
    private double seventyPlus;
    private double fiftyTo69;
    private double fifteenTo49;
    private double fiveTo14;
    private double standardized;


    public dataPackHelper (String country, int year, double allAge, double seventyPlus, double fiftyTo69, double fifteenTo49, double fiveTo14, double standardized) {
        this.country = country;
        this.year = year;
        this.allAge = allAge;
        this.seventyPlus = seventyPlus;
        this.fiftyTo69 = fiftyTo69;
        this.fifteenTo49 = fifteenTo49;
        this.fiveTo14 = fiveTo14;
        this.standardized = standardized;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public double getAllAge() {
        return allAge;
    }

    public double getSeventyPlus() {
        return seventyPlus;
    }

    public double getFiftyTo69() {
        return fiftyTo69;
    }

    public double getFifteenTo49() {
        return fifteenTo49;
    }

    public double getFiveTo14() {
        return fiveTo14;
    }

    public double getStandard() {
        return standardized;
    }

}
