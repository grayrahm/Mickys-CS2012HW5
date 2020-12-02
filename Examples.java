import org.junit.Test;
import sun.util.calendar.Gregorian;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class Examples {


    Time midnight = new Time(0, 0);
    Time morning = new Time(4, 30);
    Time noon = new Time(12, 0);
    Time afternoon = new Time(15, 45);
    Time evening = new Time(19, 21);
    Time night = new Time(22, 00);

    GregorianCalendar jan1 = new GregorianCalendar(2000, 0, 1);
    GregorianCalendar jan2 = new GregorianCalendar(2000, 0, 2);
    GregorianCalendar spring = new GregorianCalendar(1998, 4, 30);
    GregorianCalendar summer = new GregorianCalendar(2023, 6, 31);
    GregorianCalendar invalidDate = new GregorianCalendar(10, 12, 300);
    GregorianCalendar rainy = new GregorianCalendar(2020, 3, 15);
    GregorianCalendar winter = new GregorianCalendar(2020, 2, 17);

    Reading coldDryMidnight = new Reading(midnight, 0, 0);
    Reading warmDryMidnight = new Reading(midnight, 50, 0);
    Reading rainMidnight = new Reading(midnight, 50, 5);
    Reading coldDryMorning = new Reading(morning, -50, 0);
    Reading warmRainMorning = new Reading(morning, 50, 30);
    Reading rainMorning = new Reading(morning, 100, 5);
    Reading negColdNoon = new Reading(noon, 15, -2);
    Reading coldRainNoon = new Reading(noon, -3, 2);
    Reading warmRainNoon = new Reading(noon, 75, 30);
    Reading rainNoon = new Reading(noon, 100, 5);
    Reading coldDryAfternoon = new Reading(afternoon, 32, 0);
    Reading warmRainAfternoon = new Reading(afternoon, 85, 30);
    Reading rainAfternoon = new Reading(afternoon, 15, 5);
    Reading coldDryEvening = new Reading(evening, -10, 0);
    Reading warmDryEvening = new Reading(evening, 97, 0);
    Reading rainEvening = new Reading(evening, 15, 300);
    Reading coldDryNight = new Reading(night, 20, 0);
    Reading warmDryNight = new Reading(night, 97, 0);
    Reading rainNight = new Reading(night, 15, 300);
    Reading negRainNight = new Reading(night, 15, -10);


    LinkedList<Reading> cold = new LinkedList<Reading>();
    LinkedList<Reading> warm = new LinkedList<Reading>();
    LinkedList<Reading> dry = new LinkedList<Reading>();
    LinkedList<Reading> wet = new LinkedList<Reading>();
    LinkedList<Reading> tempZero = new LinkedList<Reading>();
    LinkedList<Reading> negRain = new LinkedList<Reading>();

    WeatherMonitor coldDay = new WeatherMonitor();
    WeatherMonitor warmDay = new WeatherMonitor();
    WeatherMonitor wetDay = new WeatherMonitor();
    WeatherMonitor dryDay = new WeatherMonitor();
    WeatherMonitor complicated = new WeatherMonitor();
    WeatherMonitor multipleValidDays = new WeatherMonitor();
    WeatherMonitor onlyOneValidDay = new WeatherMonitor();
    WeatherMonitor negativeRain = new WeatherMonitor();
    WeatherMonitor twoTemps = new WeatherMonitor();
    WeatherMonitor oneTemp = new WeatherMonitor();

    public Examples() {

        cold.add(coldDryMidnight);
        cold.add(coldDryMorning);
        cold.add(coldRainNoon);
        cold.add(coldDryAfternoon);
        cold.add(coldDryEvening);
        cold.add(coldDryNight);

        warm.add(warmDryMidnight);
        warm.add(warmRainMorning);
        warm.add(warmRainNoon);
        warm.add(warmRainAfternoon);
        warm.add(warmDryEvening);
        warm.add(warmDryNight);

        dry.add(coldDryMidnight);
        dry.add(warmDryMidnight);
        dry.add(coldDryMorning);
        dry.add(coldDryAfternoon);
        dry.add(coldDryEvening);
        dry.add(warmDryEvening);
        dry.add(coldDryNight);
        dry.add(warmDryNight);

        wet.add(rainMidnight);
        wet.add(rainMorning);
        wet.add(rainNoon);
        wet.add(rainAfternoon);
        wet.add(rainEvening);
        wet.add(rainNight);

        negRain.add(negColdNoon);
        negRain.add(negRainNight);
        negRain.add(rainAfternoon);
        negRain.add(rainEvening);

        tempZero.add(coldDryMidnight);
        tempZero.add(coldDryMorning);

        coldDay.addDailyReport(winter, cold);

        warmDay.addDailyReport(summer, warm);

        wetDay.addDailyReport(rainy, wet);

        dryDay.addDailyReport(jan1, dry);

        complicated.addDailyReport(winter, cold);

        warmDay.addDailyReport(summer, warm);

        dryDay.addDailyReport(jan1, dry);

        multipleValidDays.addDailyReport(jan1, wet);
        multipleValidDays.addDailyReport(jan2, warm);

        onlyOneValidDay.addDailyReport(spring, wet);
        onlyOneValidDay.addDailyReport(summer, warm);

        negativeRain.addDailyReport(winter, negRain);

        oneTemp.addDailyReport(spring, warm);
        oneTemp.addDailyReport(summer, cold);

        twoTemps.addDailyReport(jan1, warm);
        twoTemps.addDailyReport(jan2, cold);
    }

    @Test
    public void testNoRainfall(){
        assertEquals(0,dryDay.totalRainfallForMonth(0, 2000), .001);
    }

    @Test
    public void testWrongDateRainfall(){
        assertEquals(0,wetDay.totalRainfallForMonth(0, 2000), .001);
    }

    @Test
    public void testInvalidDateRainfall(){
        assertEquals(0,wetDay.totalRainfallForMonth(13, 3000), .001);
    }

    @Test
    public void testWrongYearRainfall(){
        assertEquals(0,wetDay.totalRainfallForMonth(3, 3000), .001);
    }

    @Test
    public void testLotsOfRainfall(){
        assertEquals(620,wetDay.totalRainfallForMonth(3, 2020), .001);
    }

    @Test
    public void testMultipleValidDaysRainfall(){
        double rain = 5+30+5+30+5+30+5+300+300;
        assertEquals(rain,multipleValidDays.totalRainfallForMonth(0, 2000), .001);
    }

    @Test
    public void testOnlyOneValidDayRainfall(){
        double rain = 5+5+5+5+600;
        assertEquals(rain,onlyOneValidDay.totalRainfallForMonth(4, 1998), .001);
    }

    @Test
    public void testNegativeRainfall(){
        assertEquals(305,negativeRain.totalRainfallForMonth(2, 2020), .001);
    }

    @Test
    public void testWrongDayTemperature(){
        assertEquals(0,negativeRain.averageTempForMonth(4, 2020), .001);
    }

    @Test
    public void testWrongYearTemperature(){
        assertEquals(0,negativeRain.averageTempForMonth(2, 2021), .001);
    }

    @Test
    public void testNegativeTemperatures(){
        double total = 0+-50+-3+32+-10+20;
        total = total/6;
        assertEquals(total,coldDay.averageTempForMonth(2, 2020), .001);
    }

    @Test
    public void testNormalTemperatures(){
        double avg = (50+50+75+85+97+97);
        avg = avg/6;
        assertEquals(avg,warmDay.averageTempForMonth(6, 2023), .001);
    }

    @Test
    public void testTwoValidDaysTemperatures(){
        double avg = (50+50+75+85+97+97)+0+-50+-3+32+-10+20;
        avg = avg/12;
        assertEquals(avg,twoTemps.averageTempForMonth(0, 2000), .001);
    }

    @Test
    public void testOneValidDaysTemperatures(){
        assertEquals(0,oneTemp.averageTempForMonth(2, 2021), .001);
    }

}
