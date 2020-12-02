import sun.awt.image.ImageWatched;

import java.util.GregorianCalendar;
import java.util.LinkedList;

public class WeatherMonitor {

    private IReports dailyReports;

    public WeatherMonitor(IReports dailyReports){
        this.dailyReports = dailyReports;
    }

    //For empty Weather Monitors
    public WeatherMonitor(){
        this.dailyReports = new ReportsLinkedList();
    }

    /**
     * Calculate the average temperature over all days that month
     * @param month the month that the user is looking for the average temp in
     * @param year the year that the user is looking for the average temp in
     * @return the average temperature
     */
    public double averageTempForMonth(int month, int year){
        return this.dailyReports.averageTempForMonth(month, year);
    }

    /**
     * Calculate the total rainfall over all days that month
     * @param month the month that the user is looking for the average temp in
     * @param year the year that the user is looking for the average temp in
     *@return the total rainfall
     */
    public double totalRainfallForMonth(int month, int year){
        return this.dailyReports.totalRainfallForMonth(month, year);
    }


    /**
     * Add a new daily report to the weather monitor linked list
     * @param Date the date the reading is from
     * @param Readings a list of temp and rainfall readings at different times
     */
    public void addDailyReport(GregorianCalendar Date, LinkedList<Reading> Readings){
        this.dailyReports = this.dailyReports.addDailyReport(Date, Readings);
    }
}
