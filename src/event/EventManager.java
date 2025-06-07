/**
 * This class manages and interacts with all events in the community centre's system, past present or future
 *
 * @author Mansour Abdelsalam
 * @version 1.0
 * @since 2025-06-06
 */

package event;

import java.util.ArrayList;

import facility.Facility;
import member.Member;
import staff.Staff;
import time.TimeBlock;
import main.CommunityCentreRunner;

public class EventManager {
    // fields
    private ArrayList<Event> events = new ArrayList<Event>();

    /**
     * Constructor for EventManager;
     * creates an EventManager.
     * Schedule events using this class.
     */
    public EventManager() {

    }

    // accessors
    //

    // mutators
    //

    /**
     * book
     * adds a new event to the EventManager.
     */
    public void book(Event event) {
        event.setId(generateId());
        events.add(event);
    }

    /**
     * generateId()
     * generates a unique integer ID to be assigned to an event.
     * Only called in the book method.
     * 
     * @return the unique ID
     */
    private int generateId() {
        int maxId = 0;

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() > maxId) {
                maxId = events.get(i).getId();
            }
        }
        return maxId + 1;
    }

    /**
     * printAllEvents
     * prints all events contained in the EventManager.
     */
    public void printAllEvents() {
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i));
            System.out.println(); // blank line
        }
    }

    /**
     * printPastEvents
     * prints all events that have completed.
     */
    public void printPastEvents() {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).hasCompleted()) {
                System.out.println(events.get(i));
                System.out.println(); // blank line
            }
        }
    }

    /**
     * printFutureEvents
     * prints all events that have not passed or are ongoing.
     */
    public void printFutureEvents() {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);

            if (!event.hasCompleted()) {
                if (!main.CommunityCentreRunner.getTimeManager().isOngoing(event.getTimeBlock())) {
                    System.out.println(event);
                    System.out.println(); // blank line
                }
            }
        }
    }

    /**
     * printOngoingEvents
     * prints all events that are currently ongoing.
     */
    public void printOngoingEvents() {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            
            if (main.CommunityCentreRunner.getTimeManager().isOngoing(event.getTimeBlock())) {
                System.out.println(event);
                System.out.println(); // blank line
            }
        }
    }

    /**
     * advanceTime
     * advances the time of all events within EventManager to a certain timeblock.
     * Is called within TimeManager.
     * 
     * @param newTime
     */
    public void advanceTime(TimeBlock newTime) {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);

            if (!event.hasCompleted()) {
                if (event.getTimeBlock().compareToEnd(newTime) < 0) {
                    event.setCompleted();
                }
            }
        }
    }

    /**
     * searchById
     * searches for an event by ID
     * 
     * @param id
     * @return the event with the ID, null if not found
     */
    public Event searchById(int id) {
        return searchByIdRecursive(id, 0, events.size() - 1);
    }

    /**
     * searchByIdRecursive
     * helper event for searchById recursive binary search
     * 
     * @param id
     * @param low
     * @param high
     * @return the event with the ID, null if not found
     */
    private Event searchByIdRecursive(int id, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        int midId = events.get(mid).getId();
        if (midId == id) {
            return events.get(mid);
        } else {
            if (midId > id) {
                return searchByIdRecursive(id, low, mid - 1);
            } else {
                return searchByIdRecursive(id, mid + 1, high);
            }
        }
    }

    /**
     * cancelEvent
     * cancels an event given its ID
     * 
     * @param id
     * @return whether the cancellation was successful
     */
    public boolean cancelEvent(int id) {
        Event event = searchById(id);

        if (event == null) {
            return false;
        }

        events.remove(event);
        ArrayList<Member> participants = event.getParticipants();
        ArrayList<Staff> staffSupervising = event.getStaffSupervising();

        for (int i = 0; i < participants.size(); i++) {
            Member member = participants.get(i);
            member.getRegistrations().cancelEvent(event);
        }
        for (int i = 0; i < staffSupervising.size(); i++) {
            Staff staff = staffSupervising.get(i);
            staff.getShifts().cancelEvent(event);
        }
        return true;
    }
}
