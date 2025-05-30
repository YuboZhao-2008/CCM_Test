/**
 * Schedule implements a chronologically ordered schedule preventing conflicts
 * 
 * @author Sean Yang
 * @since May 30, 2025
 */

package time;

import java.util.ArrayList;

import event.Event;

public class Schedule {
    private ArrayList<Event> eventSchedule;

    public boolean isBlockFree(TimeBlock timeBlock) {
        for (Event event : eventSchedule) {
            if (event.getTimeBlock().isConflicting(timeBlock)) {
                return false;
            }
        }

        return true;
    }

    /**
     * returns the correct position to insert a target time block using binary
     * search
     * 
     * @param target
     * @return the index to insert at
     */
    private int binarySearch(TimeBlock target) {
        return binarySearch(0, eventSchedule.size() - 1, target);
    }

    /**
     * recursive method to serach for the correct position to insert
     * 
     * @param hi     the upper bound
     * @param lo     the lower bound
     * @param target
     * @return the index
     */
    private int binarySearch(int hi, int lo, TimeBlock target) {
        if (lo >= hi) {
            return (eventSchedule.get(lo).getTimeBlock().compareToStart(target) > 0) ? lo + 1 : lo;
        }

        int mid = lo + (hi - lo) / 2;

        if (eventSchedule.get(mid).getTimeBlock().compareToStart(target) > 0) {
            lo = mid + 1;
        } else {
            hi = mid;
        }

        return binarySearch(lo, hi, target);
    }

    public boolean add(Event event) {
        if (isBlockFree(event.getTimeBlock())) {
            int idx = binarySearch(event.getTimeBlock());
            eventSchedule.add(idx, event);
            return true;
        } else {
            return false;
        }
    }
}
