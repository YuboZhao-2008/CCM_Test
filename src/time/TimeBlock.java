package time;

public class TimeBlock {
    public enum Month {
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
    }

    private int year;
    private Month month;
    private int day;
    private double startHour;
    private double endHour;

    /**
     * TimeBlock constructor for a whole-day event
     * 
     * @param year
     * @param month
     * @param day
     */
    public TimeBlock(int year, Month month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        startHour = 0;
        endHour = 24;
    }

    /**
     * TimeBlock constructor for an event without an end time
     * 
     * @param year
     * @param month
     * @param day
     * @param startHour
     */
    public TimeBlock(int year, Month month, int day, double startHour) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        endHour = startHour;
    }

    /**
     * TimeBlock constructor for an event with a duration
     * 
     * @param year
     * @param month
     * @param day
     * @param startHour
     * @param duration
     */
    public TimeBlock(int year, Month month, int day, double startHour, double duration) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        endHour = startHour + duration;
    }

    /**
     * compares the start time of this event and another
     * 
     * @param other
     * @return a positive number if this event starts first, negative if the other
     *         event starts first
     */
    public double compareToStart(TimeBlock other) {
        return other.startHour - this.startHour;
    }

    /**
     * compares the end time of this event and another
     * 
     * @param other
     * @return a positive number if this event ends first, negative if the other
     *         event end first
     */
    public double compareToEnd(TimeBlock other) {
        return other.endHour - this.endHour;
    }

    /**
     * checks whether two time blocks conflict each other
     * 
     * @param other the other TimeBlock
     * @return whether the two time blocks are conflicting
     */
    public boolean isConflicting(TimeBlock other) {
        if (year == other.year && month == other.month && day == other.day) {
            return (startHour < other.endHour && other.startHour < endHour) || (startHour > other.endHour
                    && other.startHour > endHour);
        }

        return false;
    }

    /**
     * accessor for duration
     * 
     * @return a double, the duration
     */
    public double duration() {
        return endHour - startHour;
    }

    /**
     * converts time block to a readable string
     * 
     * @return the string
     */
    public String toString() {
        int startMinute = (int) Math.round(startHour % 1 * 60);
        int endMinute = (int) Math.round(endHour % 1 * 60);
        return String.format("%s %d, %d %02d:%02d - %02d:%02d", month, day, year, (int) startHour, startMinute,
                (int) endHour,
                endMinute);
    }

    public static void main(String[] args) {
        TimeBlock timeBlock = new TimeBlock(2015, Month.JAN, 3, 12, 4);
        TimeBlock timeBlock2 = new TimeBlock(2015, Month.JAN, 3, 15.9, 5);
        System.out.println(timeBlock.isConflicting(timeBlock2));
    }
}
