/**
 * This class manages and interacts with all events in the community centre's system, past present or future
 *
 * @author Mansour Abdelsalam
 * @version 1.0
 * @since 2025-06-10 
 */

package event;

import java.util.ArrayList;
import java.io.*;
import java.nio.Buffer;

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
    public EventManager() {}

    /**
     * Constructor for EventManager;
     * creates an EventManager with information from a text file.
     * Schedule events using this class.
     * 
     * @param filePath
     */
    public EventManager(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // read event information
            int numEvents = Integer.parseInt(reader.readLine());
            for (int i = 0; i < numEvents; i++) {
                String eventType = reader.readLine();

                double prize;
                double participationCost;
                double goal;
                if (eventType.equals("competition")) {
                    prize = Double.parseDouble(reader.readLine());
                    participationCost = Double.parseDouble(reader.readLine());
                } else if (eventType.equals("fundraiser")) {
                    goal = Double.parseDouble(reader.readLine());
                }

                int facility_id = Integer.parseInt(reader.readLine());
                int day = Integer.parseInt(reader.readLine());
                Month month = TimeBlock.ABBR_TO_MONTH.get(reader.readLine());
                int year = Integer.parseInt(reader.readLine());
                double start_hour = Double.parseDouble(reader.readLine());
                double end_hour = Double.parseDouble(reader.readLine());
                int host_id = Integer.parseInt(reader.readLine());
                
                Facility facility = main.CommunityCentreRunner.getFacilityManager().searchById(facility_id);
                TimeBlock timeBlock = new TimeBlock(year, month, day);
                Member host = main.CommunityCentreRunner.getMemberManager().searchById(host_id);
                Event event;
                if (eventType.equals("competition")) {
                    event = new Competition(facility, timeBlock, host, prize, participationCost);
                } else if (eventType.equals("fundraiser")) {
                    event = new Fundraiser(facility, timeBlock, host, goal);
                }

                // read staff and member information registered to the event
                int numStaffSupervising = Integer.parseInt(reader.readLine());
                for (int j = 0; j < numStaffSupervising; j++) {
                    Staff staff = main.CommunityCentreRunner.getStaffManager().searchById(Integer.parseInt(reader.readLine()));
                    event.assignStaff(staff);
                }

                int numParticipants = Integer.parseInt(reader.readLine());
                for (int k = 0; k < numParticipants; k++) {
                    Member member = main.CommunityCentreRunner.getMemberManager().searchById(Integer.parseInt(reader.readLine()));
                    event.registerParticipant(member);
                }

                facility.book(event);

                events.add(event);
            }

            reader.close();
        } catch(IOException ioe) {
            System.out.println("Error accessing file "+filePath);
        }
    }

    // accessors
    //

    // mutators
    //

    /**
     * save
     * saves all of EventManager's information to a text file.
     * 
     * @param filePath
     * @return if the save was successful, usually determined by if the file path is correct
     */
    public boolean save(String file_path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file_path));

            writer.write(""+events.size());
            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);
                if (event instanceof Competition c) {
                    writer.write("competition\n");
                    writer.write(""+c.getPrize()+"\n");
                    writer.write(""+c.getParticipationCost()+"\n");
                } else if (event instanceof Fundraiser f) {
                    writer.write("fundraiser\n"+"\n");
                    writer.write(""+f.getGoal()+"\n");
                }

                writer.write(""+event.getFacility().getId()+"\n");
                writer.write(""+event.getTimeBlock().get+"\n") // day
                //month
                //year
                writer.write(""+event.getTimeBlock().getStartHour()+"\n");
                writer.write(""+event.getTimeBlock().getEndHour()+"\n");
                if (event.getHost() != null) {
                    writer.write(""+event.getHost().getId()+"\n");
                } else {
                    writer.write(""+(-1)+"\n"); // no host
                }
                
                // write staff and member ids
                writer.write(""+event.getStaffSupervising().size()+"\n");
                for (int j = 0; j < event.getStaffSupervising().size(); j++) {
                    writer.write(event.getStaffSupervising().get(j)+"\n");
                }
                writer.write(""+event.getParticipants().size()+"\n");
                for (int j = 0; j < event.getParticipants().size(); j++) {
                    writer.write(event.getParticipants().get(j)+"\n");
                }
            }

            writer.close();
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

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
        int maxId = -1;

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
