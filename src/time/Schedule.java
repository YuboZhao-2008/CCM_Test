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

    private int binarySearch(TimeBlock target) {
        return binarySearch(0, eventSchedule.size() - 1, target);
    }

    private int binarySearch(int hi, int lo, TimeBlock target) {
        int mid = (hi + lo) / 2;

        if (eventSchedule.get(mid).getTimeBlock().compareToStart(target) > 0) {
            hi = lo - 1;
        } else {
            lo = hi - 1;
        }

        return 0;
    }

    public boolean add(Event event) {
        return false;
    }
}
