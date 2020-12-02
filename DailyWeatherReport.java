import java.util.GregorianCalendar;
import java.util.LinkedList;

public class DailyWeatherReport {

    private GregorianCalendar Date;
    private LinkedList<Double> tempReadings;
    private LinkedList<Double> rainfall;

    public DailyWeatherReport(GregorianCalendar Date, LinkedList<Double> tempReadings, LinkedList<Double> rainfall){
        this.Date = Date;
        this.tempReadings = tempReadings;
        this.rainfall = rainfall;
    }

    /**
     * Remove any reports that do not satisfy the month and year and
     * produce a list of the values according to type
     * @param month the month that is being examined
     * @param year the year that is being examined
     * @param type whether to add rainfall or temp values
     * @return linkedlist of cleaned values
     */
    public LinkedList<Double> clean (int month, int year, char type){
        LinkedList<Double> cleanedValues = new LinkedList<>();
        if(this.Date.get(GregorianCalendar.MONTH) == month &&
                this.Date.get(GregorianCalendar.YEAR) == year){
                if(type == 't'){
                    for(Double value : tempReadings){
                        cleanedValues.add(value);
                    }
                }
                else if(type == 'r'){
                    for(Double value : rainfall){
                        if(value >= 0){
                            cleanedValues.add(value);
                        }
                    }
                }
        }
        return cleanedValues;
    }

    /**
     * Produce the total of all the values in the list
     * @param list a list of items to be added
     * @return the sum
     */
    public double getTotal(LinkedList<Double> list){
        double total = 0;
        for(Double value : list){
            total += value;
        }
        return total;
    }

}
