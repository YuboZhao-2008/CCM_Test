/**
 * Facility implements an abstract facility and associated methods
 *
 * @author Sean Yang
 * @since June 9, 2025
 */

package facility;

import event.Event;
import time.Schedule;
import time.TimeBlock;

public abstract class Facility {
    protected int id;
    protected int roomNum;
    protected int maxCapacity;
    protected Schedule bookings;
    public static final double BASE_COST = 100;
    public static final double HOURLY_COST = 25;

    // constructor for a new facility
    Facility(int roomNum, int maxCapacity) {
        id = main.CommunityCentreRunner.getFacilityManager().generateId();
        this.roomNum = roomNum;
        this.maxCapacity = maxCapacity;
        bookings = new Schedule();
    }

    /**
     * adds an event to the facility bookings
     * 
     * @param event
     * @return whether the addition succeeded
     */
    public boolean book(Event event) {
        return bookings.add(event);
    }

    /**
     * abstract method to calculate cost based on a time block
     * 
     * @param timeBlock
     * @return the cost
     */
    public abstract double calcCost(TimeBlock timeBlock);

    /**
     * abstract method to calculate cost for a one hour booking
     * 
     * @return the cost
     */
    public abstract double calcCostOneHour();

    /**
     * equals method compares two facilities
     * 
     * @param other
     * @return whether the two facilities are equal
     */
    public boolean equals(Facility other) {
        return other != null && id == other.id && roomNum == other.roomNum && maxCapacity == other.maxCapacity;
    }

    // accessor for max capacity
    public int getMaxCapacity() {
        return maxCapacity;
    }

    // accessor for room num
    public int getRoomNum() {
        return roomNum;
    }

    // accessor for id
    public int getId() {
        return id;
    }

    // accessor for schedule
    public Schedule getBookings() {
        return bookings;
    }

    // mutator for id
    public void setId(int id) {
        this.id = id;
    }

    // #<id>, rm<rm#>, capacity: <capacity>
    public String toString() {
        return String.format(" #%d | room: %d | capacity: %d", id, roomNum, maxCapacity);
    }
}
