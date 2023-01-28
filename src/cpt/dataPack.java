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

    /**
     * constructor method, for datapoints individually
     * 
     * @param ageRange age range
     * @param suidiceRate rate of suidice for that range
     * @param year corresponding year
     * 
     */
    public dataPack (String ageRange, double suicideRate, int year) {
        this.ageRange = ageRange;
        this.suicideRate = suicideRate;
        this.year = year;
    }

    /**
     * getter method for age
     * 
     * @return age range
     */
    public String getAge() {
        return ageRange;
    }

    /**
     * getter method for suicide rate
     * 
     * @return suicide rate
     */
    public double getSuicideRate() {
        return suicideRate;
    }

    /**
     * getter method for year
     * 
     * @return year
     */
    public int getYear() {
        return year;
    }

}
