public class Reading {

    private Time TheTime;
    private double temp;
    private double rainfallSince; //rainfall since last reading

    public Reading(Time TheTime, double temp, double rainfallSince){
        this.TheTime = TheTime;
        this.temp = temp;
        this.rainfallSince = rainfallSince;
    }

    /**
     * Return the temperature for the reading object
     * @return temp
     */
    public double getTemp(){
        return this.temp;
    }

    /**
     * Return the rainfall for the reading object
     * @return rainfallSince
     */
    public double getRainfall(){
        return this.rainfallSince;
    }
}
