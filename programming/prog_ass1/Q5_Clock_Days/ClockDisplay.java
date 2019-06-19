
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class ClockDisplay
{
    private NumberDisplay days;
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private NumberDisplay seconds;
    private String displayTime;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 000:00:00:00.
     */
    public ClockDisplay()
    {
        days = new NumberDisplay(365);
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        days = new NumberDisplay(365);
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        setTime(hour, minute);
    }
    
    /**
     * Set seconds value 
     * @param secondsValue New seconds value
     */
    public void resetSeconds(int secondsValue){
        seconds.setValue(secondsValue);
        updateDisplay();
    }
    
    /**
     * Set minutes value 
     * @param minutesValue New minutes value
     */
    public void resetMinutes(int minutesValue){
        minutes.setValue(minutesValue);
        updateDisplay();
    }
    
    /**
     * Set hours value 
     * @param hoursValue New hours value
     */
    public void resetHours(int hoursValue){
        hours.setValue(hoursValue);
        updateDisplay();
    }
    
    /**
     * Set days value 
     * @param daysValue New days value
     */
    public void resetDays(int daysValue){
        days.setValue(daysValue);
        updateDisplay();
    }
    
    /**
     * This method should get called once every second - it makes
     * the clock display go one second forward.
     */
    public void timeTick(){
        seconds.incrementValue();
        if(seconds.getValue() == 0){
            // it just rolled over!
            minutes.incrementValue();
            if(minutes.getValue() == 0){
                // it just rolled over!
                hours.incrementValue();
                if(hours.getValue() == 0){
                    days.incrementValue();
                }
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format DDD:HH:MM:SS.
     */
    public String getTime()
    {
        return displayTime;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayTime = days.display() + ":" + hours.display() + ":" + 
                        minutes.display() + ":" + seconds.display();
    }
}
