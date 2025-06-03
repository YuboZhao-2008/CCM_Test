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

    /**
     * constructor
     */
    public Schedule() {
        eventSchedule = new ArrayList<Event>();
    }

    /**
     * whether this block is free within the schedule
     * 
     * @param timeBlock
     * @return whether this block does not conflict with any other time blocks
     */
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
     * returns the index of the time block with the same start as the target, -1
     * otherwise
     * 
     * @param target
     * @return the index
     */
    private int binarySearchStrict(TimeBlock target) {
        return binarySearchStrict(0, eventSchedule.size() - 1, target);
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

        double comp = eventSchedule.get(mid).getTimeBlock().compareToStart(target);

        if (comp == 0) {
            return mid;
        } else if (comp > 0) {
            lo = mid + 1;
        } else {
            hi = mid;
        }

        return binarySearch(lo, hi, target);
    }

    /**
     * recursive method to serach for the index of a TimeBlock based on the start
     * time
     * 
     * @param hi     the upper bound
     * @param lo     the lower bound
     * @param target
     * @return the index
     */
    private int binarySearchStrict(int hi, int lo, TimeBlock target) {
        if (lo >= hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;

        double comp = eventSchedule.get(mid).getTimeBlock().compareToStart(target);

        if (comp == 0) {
            return mid;
        } else if (comp > 0) {
            lo = mid + 1;
        } else {
            hi = mid - 1;
        }

        return binarySearchStrict(lo, hi, target);
    }

    /**
     * finds all free blocks within a time range
     * 
     * @param range
     * @return an arraylist of free blocks
     */
    public ArrayList<TimeBlock> freeBlocksWithin(TimeBlock range) {
        ArrayList<TimeBlock> blocks = new ArrayList<TimeBlock>();

        int loIdx = binarySearch(range);
        int hiIdx = binarySearch(range.getEndBlock());

        double hoursUntilLo = range.getStartBlock().hoursUntil(eventSchedule.get(loIdx).getTimeBlock());

        if (hoursUntilLo > 0) {
            blocks.add(new TimeBlock(range, range.getStartHour(), hoursUntilLo));
        }

        for (int i = loIdx; i < hiIdx; i++) {
            TimeBlock curr = eventSchedule.get(i).getTimeBlock();
            TimeBlock next = eventSchedule.get(i + 1).getTimeBlock();

            double hoursBetween = curr.hoursUntil(next);

            if (hoursBetween > 0) {
                blocks.add(new TimeBlock(curr, curr.getEndHour(), hoursBetween));
            }
        }

        double hoursAfterHi = eventSchedule.get(hiIdx).getTimeBlock().hoursUntil(range.getEndBlock());

        if (hoursAfterHi > 0) {
            blocks.add(new TimeBlock(range, eventSchedule.get(hiIdx).getTimeBlock().getEndHour(), hoursAfterHi));
        }

        return blocks;
    }

    /**
     * adds an event to the schedule if it is free
     * 
     * @param event
     * @return whether the addition succeeded
     */
    public boolean add(Event event) {
        if (eventSchedule.isEmpty()) {
            eventSchedule.add(event);
            return true;
        }

        if (isBlockFree(event.getTimeBlock())) {
            int idx = binarySearch(event.getTimeBlock());
            eventSchedule.add(idx, event);
            return true;
        }

        return false;
    }
}
