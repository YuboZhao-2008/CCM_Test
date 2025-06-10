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
