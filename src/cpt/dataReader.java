package cpt;

public class dataReader {
    
    private String strCountry;
    private double dblDeath1990;
    private double dblDeath2019;

    public dataReader (String countryName, double deathRate1990, double deathRate2019) {
        this.strCountry = countryName;
        this.dblDeath1990 = deathRate1990;
        this.dblDeath2019 = deathRate2019;
    }

}
