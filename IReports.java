import java.util.GregorianCalendar;
import java.util.LinkedList;

public interface IReports {
    //determine size of list
    public int size();
    //get the element at a specified index
    public DailyWeatherReport get(int i);
    //add an item to the list
    public void add(DailyWeatherReport report);
    //find the average temp over a specified month and year
    public double averageTempForMonth(int month, int year);
    //find total rainfall over a specified month and year
    public double totalRainfallForMonth(int month, int year);
    //add a report to the list of reports
    public IReports addDailyReport(GregorianCalendar Date, LinkedList<Reading> Readings);
}
