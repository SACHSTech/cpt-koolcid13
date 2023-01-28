package cpt;

/**
* dataPack file, for every dataset line
* @author: Avin A.
*
*/

public class dataPack {
    
    // instance vars
    private String ageRange;
    private double suicideRate;
    private int year;

    public dataPack (String ageRange, double suicideRate, int year) {
        this.ageRange = ageRange;
        this.suicideRate = suicideRate;
        this.year = year;
    }

    public String getAge() {
        return ageRange;
    }

    public double getSuicideRate() {
        return suicideRate;
    }

    public int getYear() {
        return year;
    }

}
