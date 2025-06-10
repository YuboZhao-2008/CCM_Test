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

import time.TimeBlock;

public class FacilityManager {
    private ArrayList<Facility> facilities;

    // constructor for blank facility manager
    public FacilityManager() {
        facilities = new ArrayList<Facility>();
    }

    /**
     * creates a facility manager from a data file
     * 
     * @param fileName
     */
    public FacilityManager(String fileName) {
        facilities = new ArrayList<Facility>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            int numFacilities = Integer.parseInt(br.readLine());

            for (int i = 0; i < numFacilities; i++) {
                int id = Integer.parseInt(br.readLine());
                String type = br.readLine();
                int roomNum = Integer.parseInt(br.readLine());
                int maxCapacity = Integer.parseInt(br.readLine());
                double ratingOrSize = Double.parseDouble(br.readLine());

                if (type.equals("meeting")) {
                    facilities.add(new MeetingFacility(roomNum, maxCapacity, ratingOrSize));
                    facilities.getLast().setId(id);
                }

                if (type.equals("sports")) {
                    facilities.add(new SportsFacility(roomNum, maxCapacity, ratingOrSize));
                    facilities.getLast().setId(id);
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

    // prints all facilities to standard output
    public void printAllFacilities() {
        for (Facility facility : facilities) {
            System.out.println(facility);
        }
    }

    /**
     * finds all facilities that are available during a given time block
     * 
     * @param timeBlock the time block to search
     * @return an ArrayList of available facilities
     */
    public ArrayList<Facility> availableFacilities(TimeBlock timeBlock) {
        ArrayList<Facility> avail = new ArrayList<Facility>();

        for (Facility facility : facilities) {
            if (facility.getBookings().isBlockFree(timeBlock)) {
                avail.add(facility);
            }
        }

        return avail;
    }
}