<<<<<<< HEAD
package staff;
import java.util.*;

import static java.util.Collections.sort;
import java.io.*;
import time.*;
import staff.*;

public class StaffManager {
    public ArrayList<Staff> staffs;

    public StaffManager() {
        staffs = new ArrayList<>();
    }

    public StaffManager(String filename) {
        staffs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int numStaff = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < numStaff; i++) {
                String line;
                while ((line = br.readLine()) != null
                        && (line.trim().isEmpty() || line.trim().equals("##"))) {
                }
                int id = Integer.parseInt(line.trim());
                String type = br.readLine().trim().toLowerCase();
                String name = br.readLine().trim();
                if (type.equals("fulltime")) {
                    int yearsWorked = Integer.parseInt(br.readLine().trim());
                    FullTimeStaff full = new FullTimeStaff(name, yearsWorked);
                    full.setId(id);
                    staffs.add(full);
                } else if (type.equals("parttime")) {
                    int hoursWorked    = Integer.parseInt(br.readLine().trim());
                    double hourlyRate  = Double.parseDouble(br.readLine().trim());
                    int maxWeeklyHours = Integer.parseInt(br.readLine().trim());
                    PartTimeStaff part = new PartTimeStaff(name, hoursWorked, hourlyRate, maxWeeklyHours);
                    part.setId(id);
                    staffs.add(part);
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void addStaff(Staff staff)   {
        staff.setId(generateId());
        staffs.add(staff);
    }

    public int generateId() {
        return staffs.getLast().getId() + 1;
    }

    public Staff searchById(int id) {
        return searchByIdRecursive(id, 0, staffs.size() - 1);
    }

    private Staff searchByIdRecursive(int id, int low, int high) {
        if (low > high)    {
            return null;
        }
        int mid = (low + high) / 2;
        int midId = staffs.get(mid).getId();
        if (midId == id)  {
            return staffs.get(mid);
        } else {
            if (midId > id)  {
                return searchByIdRecursive(id, low, mid - 1);
            } else {
                return searchByIdRecursive(id, mid + 1, high);
            }
        }
    }

    public void printAllPayrolls () {
        for (int i = 0; i < staffs.size(); i++) {
            staffs.get(i).printPayroll();
        }
    }

    public ArrayList<Staff> availableStaff(TimeBlock block) {
        ArrayList<Staff> available = new ArrayList<>();

        for (Staff staff : staffs) {
            if (staff.isAvailable(block)) {
                available.add(staff);
            }
        }

        return available;
    }

    public void printAlphabetical ()    {
        ArrayList<String> sorted = new ArrayList<>();
        for (int i = 0; i < staffs.size(); i++) {
            sorted.add(staffs.get(i).getName());
        }
        sort(sorted);
        for (String str : sorted)   {
            System.out.println(str);
        }
    }
}
=======
package staff;

import java.util.*;
import java.io.*;

import static java.util.Collections.sort;

/**
 * Manages a collection of Staff members: loading from file, adding new staff,
 * searching by ID, printing payrolls, finding available staff for a timeslot,
 * and printing names alphabetically.
 *
 * @author Yubo-Zhao
 * @version 1.0
 * @since 2025-06-09
 */
public class StaffManager {
    /**
     * The list of all staff members managed by this class.
     */
    public ArrayList<Staff> staffs;

    /**
     * Constructs a StaffManager and loads staff data from a text file.
     * The file format is:
     * <num staff>
     * <id>
     * <type>          // "fulltime" or "parttime"
     * <name>
     * [fulltime only] <yearsWorked>
     * [parttime only] <hoursWorked>
     *                  <hourlySalary>
     *                  <maxWeeklyHours>
     *
     * @param filename the path to the staff data file
     */
    public StaffManager(String filename) {
        staffs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int numStaff = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < numStaff; i++) {
                String line;
                // skip any blank lines or "##" separators
                while ((line = br.readLine()) != null
                        && (line.trim().isEmpty() || line.trim().equals("##"))) {
                }
                int id         = Integer.parseInt(line.trim());
                String type    = br.readLine().trim().toLowerCase();
                String name    = br.readLine().trim();

                if (type.equals("fulltime")) {
                    int yearsWorked = Integer.parseInt(br.readLine().trim());
                    FullTimeStaff full = new FullTimeStaff(name, yearsWorked);
                    full.setId(id);
                    staffs.add(full);

                } else if (type.equals("parttime")) {
                    int   hoursWorked    = Integer.parseInt(br.readLine().trim());
                    double hourlyRate    = Double.parseDouble(br.readLine().trim());
                    int   maxWeeklyHours = Integer.parseInt(br.readLine().trim());
                    PartTimeStaff part = new PartTimeStaff(
                            name, hoursWorked, hourlyRate, maxWeeklyHours);
                    part.setId(id);
                    staffs.add(part);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading staff file: " + e.getMessage());
        }
    }

    /**
     * Adds a new staff member, assigning them a unique ID.
     *
     * @param staff the Staff object to add
     */
    public void addStaff(Staff staff) {
        staff.setId(generateId());
        staffs.add(staff);
    }

    /**
     * Generates the next unique staff ID by taking the last assigned ID + 1.
     *
     * @return a new unique ID
     */
    public int generateId() {
        return staffs.get(staffs.size() - 1).getId() + 1;
    }

    /**
     * Searches for a staff member by their ID using binary search.
     * Assumes the list is sorted by ID.
     *
     * @param id the staff ID to search for
     * @return the Staff with the matching ID, or null if not found
     */
    public Staff searchById(int id) {
        return searchByIdRecursive(id, 0, staffs.size() - 1);
    }

    /**
     * Recursive helper for binary search by ID.
     *
     * @param id   the target ID
     * @param low  the lower index
     * @param high the upper index
     * @return the matching Staff, or null if not present
     */
    private Staff searchByIdRecursive(int id, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid   = (low + high) / 2;
        int midId = staffs.get(mid).getId();

        if (midId == id) {
            return staffs.get(mid);
        } else if (midId > id) {
            return searchByIdRecursive(id, low, mid - 1);
        } else {
            return searchByIdRecursive(id, mid + 1, high);
        }
    }

    /**
     * Prints the payroll information for all staff to standard output.
     */
    public void printAllPayrolls() {
        for (Staff s : staffs) {
            s.printPayroll();
        }
    }

    /**
     * Finds all staff members available during the given time block.
     *
     * @param block the time block to check availability against
     * @return a list of Staff who are available
     */
    public ArrayList<Staff> availableStaff(TimeBlock block) {
        ArrayList<Staff> available = new ArrayList<>();
        for (Staff s : staffs) {
            if (s.isAvailable(block)) {
                available.add(s);
            }
        }
        return available;
    }

    /**
     * Prints all staff names in alphabetical order.
     */
    public void printAlphabetical() {
        ArrayList<String> sortedNames = new ArrayList<>();
        for (Staff s : staffs) {
            sortedNames.add(s.getName());
        }
        sort(sortedNames);
        for (String name : sortedNames) {
            System.out.println(name);
        }
    }
}
>>>>>>> 15fd0a25629d36c9e0a62385dc2b3c62acfdd1e2
