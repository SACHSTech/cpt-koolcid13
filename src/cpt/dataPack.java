package cpt;

public class dataPack {
    
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

}
