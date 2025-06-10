/**
 * MeetingFacility implements a meeting facility facility and associated methods
 *
 * @author Sean Yang
 * @since June 9, 2025
 */

package facility;

import time.TimeBlock;

public class MeetingFacility extends Facility {
    private double size;

    public static final double COST_PER_SQFT = 0.05;

    /**
     * MeetingFacility constructor
     * 
     * @param roomNum
     * @param maxCapacity
     * @param size
     */
    public MeetingFacility(int roomNum, int maxCapacity, double size) {
        super(roomNum, maxCapacity);
        this.size = size;
    }

    /**
     * calculates the cost associated with booking a meeting facility for a
     * specified time
     * 
     * @param timeBlock
     * @return a double, the cost
     */
    public double calcCost(TimeBlock timeBlock) {
        return Facility.BASE_COST + timeBlock.duration() * Facility.HOURLY_COST + size * COST_PER_SQFT;
    }

    // converts object to string
    public String toString() {
        return "Meeting Facility" + ((Facility) this).toString() + String.format(" size: %.0fsqft", size);
    }

    // accessor for size
    public double getSize() {
        return size;
    }
}
