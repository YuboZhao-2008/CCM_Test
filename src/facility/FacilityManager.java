/**
 * Facility implements an abstract facility and associated methods
 *
 * @author Sean Yang
 * @since June 9, 2025
 */

package facility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import time.TimeBlock;

public class FacilityManager {
    private ArrayList<Facility> facilities;

    // constructor for blank facility manager
    public FacilityManager() {
        facilities = new ArrayList<>();
    }

    /**
     * creates a facility manager from a data file
     * 
     * @param fileName
     */
    public FacilityManager(String fileName) {
        facilities = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            int numFacilities = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < numFacilities; i++) {
                int id = Integer.parseInt(br.readLine().trim());
                String type = br.readLine().trim();
                int roomNum = Integer.parseInt(br.readLine().trim());
                int maxCapacity = Integer.parseInt(br.readLine().trim());
                double ratingOrSize = Double.parseDouble(br.readLine().trim());

                if (type.equals("meeting")) {
                    facilities.add(new MeetingFacility(roomNum, maxCapacity, ratingOrSize));
                    facilities.get(facilities.size() - 1).setId(id);
                }

                if (type.equals("sports")) {
                    facilities.add(new SportsFacility(roomNum, maxCapacity, ratingOrSize));
                    facilities.get(facilities.size() - 1).setId(id);
                }
            }

            br.close();
        } catch (IOException iox) {
            System.out.println("Error reading from file");
        }
    }

    /**
     * saves the facilities to a file
     * 
     * @param fileName the file to save to
     */
    public void save(String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            bw.write(facilities.size() + "\n");

            for (Facility facility : facilities) {
                bw.write(facility.getId() + "\n");
                double ratingOrSize;
                if (facility instanceof MeetingFacility meetingFacility) {
                    bw.write("meeting\n");
                    ratingOrSize = meetingFacility.getSize();
                } else {
                    bw.write("sports\n");
                    ratingOrSize = ((SportsFacility) facility).getRating();
                }
                bw.write(facility.getRoomNum() + "\n");
                bw.write(facility.getMaxCapacity() + "\n");
                bw.write(ratingOrSize + "\n");
            }

            bw.close();
        } catch (IOException iox) {
            System.out.println("Error writing to file");
        }
    }

    /**
     * generates a new facility ID
     * 
     * @return a free ID
     */
    public int generateId() {
        int maxId = -1;

        for (Facility facility : facilities) {
            maxId = Math.max(maxId, facility.getId());
        }

        return ++maxId;
    }

    /**
     * searches for a facility matching the ID
     * 
     * @param id
     * @return a facility object
     */
    public Facility searchById(int id) {
        for (Facility facility : facilities) {
            if (facility.getId() == id) {
                return facility;
            }
        }

        return null;
    }

    /**
     * prints all facilities
     * 
     * @return whether anything was printed
     */
    public boolean printAllFacilities() {
        if (facilities.isEmpty()) {
            return true;
        }

        for (Facility facility : facilities) {
            System.out.println(facility);
        }

        return false;
    }

    /**
     * finds all facilities that are available during a given time block
     * 
     * @param timeBlock the time block to search
     * @return an ArrayList of available facilities
     */
    public ArrayList<Facility> availableFacilities(TimeBlock timeBlock) {
        ArrayList<Facility> avail = new ArrayList<>();

        for (Facility facility : facilities) {
            if (facility.getBookings().isBlockFree(timeBlock)) {
                avail.add(facility);
            }
        }

        return avail;
    }

    /**
     * prints sports facilities by rating (highest first)
     * 
     * @return whether anything was printed
     */
    public boolean printSportsFacilitiesByRating() {
        ArrayList<SportsFacility> sportsFacilities = new ArrayList<>();

        for (Facility facility : facilities) {
            if (facility instanceof SportsFacility sportsFacility) {
                sportsFacilities.add(sportsFacility);
            }
        }

        if (sportsFacilities.isEmpty()) {
            return false;
        }

        sportsFacilities.sort(Comparator.comparingDouble(SportsFacility::getRating).reversed());

        for (SportsFacility sportsFacility : sportsFacilities) {
            System.out.println(sportsFacility);
        }

        return true;
    }

    /**
     * prints facilities by cost (cheapest first)
     * 
     * @return whether anything was printed
     */
    public boolean printFacilitiesByCost() {
        if (facilities.isEmpty()) {
            return false;
        }

        ArrayList<Facility> sorted = new ArrayList<>(facilities);
        sorted.sort(Comparator.comparingDouble(Facility::calcCostOneHour));
        for (Facility facility : sorted) {
            System.out.println(facility);
        }

        return true;
    }

    /**
     * prints meeting facilities by size (largest first)
     * 
     * @return whether anything was printed
     */
    public boolean printMeetingFacilitiesBySize() {
        ArrayList<MeetingFacility> meetingFacilities = new ArrayList<>();

        for (Facility facility : facilities) {
            if (facility instanceof MeetingFacility meetingFacility) {
                meetingFacilities.add(meetingFacility);
            }
        }

        if (meetingFacilities.isEmpty()) {
            return false;
        }

        meetingFacilities.sort(Comparator.comparingDouble(MeetingFacility::getSize).reversed());

        for (MeetingFacility meetingFacility : meetingFacilities) {
            System.out.println(meetingFacility);
        }

        return true;
    }

    /**
     * adds a facility to the facilities arraylist
     */
    public void addFacility(Facility facility) {
        facilities.add(facility);
    }

    /**
     * Removes a facility with the given ID from the list.
     */
    public boolean removeFacility(int id) {
        for (int i = 0; i < facilities.size(); i++) {
            if (facilities.get(i).getId() == id) {
                facilities.remove(i);
                return true;
            }
        }
        return false;
    }
}
