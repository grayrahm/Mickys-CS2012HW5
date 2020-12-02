import java.util.GregorianCalendar;
import java.util.LinkedList;

public class ReportsLinkedList implements IReports{
    LinkedList<DailyWeatherReport> reports;

    public ReportsLinkedList(LinkedList<DailyWeatherReport> reports){
        this.reports = reports;
    }

    public ReportsLinkedList(){
        this.reports = new LinkedList<DailyWeatherReport>();
    }

    /**
     * Return the size of the linked list
     * @return an int of the size
     */
    public int size(){
        return reports.size();
    }

    /**
     * Return the DailyWeatherReport object at a given index
     * @param i the index of the desired object
     * @return a DailyWeatherReport
     */
    public DailyWeatherReport get(int i){
        return reports.get(i);
    }

    /**
     * Add a DailyReport to a list
     * @param report the report to be added
     */
    public void add(DailyWeatherReport report){
        reports.add(report);
    }

    /**
     * Calculate the average temperature over all days that month
     * @param month the month that the user is looking for the average temp in
     * @param year the year that the user is looking for the average temp in
     * @return the average temperature
     */
    public double averageTempForMonth(int month, int year){
        double totalTemp = 0;
        LinkedList<Double> tempReadings = new LinkedList<>();
        LinkedList<Double> tempToAdd;
        DailyWeatherReport report;
        for(int i=0; i < this.size(); i++){
            report = this.get(i);
            tempReadings.addAll(report.clean(month, year, 't'));
            tempToAdd = report.clean(month, year, 't');
            totalTemp += report.getTotal(tempToAdd);
        }
        int size = tempReadings.size();
        if(size == 0){return 0;}
        return totalTemp / size;
    }

    /**
     * Calculate the total rainfall over all days that month
     * @param month the month that the user is looking for the average temp in
     * @param year the year that the user is looking for the average temp in
     *@return the total rainfall
     */
    public double totalRainfallForMonth(int month, int year){
        LinkedList<Double> rainfall;
        double totalRain = 0;
        DailyWeatherReport report;
        for(int i=0; i < this.size(); i++){
            report = this.get(i);
            rainfall =  report.clean(month, year, 'r');
            totalRain += report.getTotal(rainfall);
        }
        return totalRain;
    }

    /**
     * Add a new daily report to the weather monitor linked list
     * @param Date the date the reading is from
     * @param Readings a list of temp and rainfall readings at different times
     * @return The updates list of Daily Reports
     */
    public IReports addDailyReport(GregorianCalendar Date, LinkedList<Reading> Readings){
        LinkedList<Double> temp = new LinkedList<Double>();
        LinkedList<Double> rainfall = new LinkedList<Double>();
        for(Reading reading : Readings){
            temp.add(reading.getTemp());
            rainfall.add(reading.getRainfall());
        }
        this.add(new DailyWeatherReport(Date, temp, rainfall));
        return this;
    }
}