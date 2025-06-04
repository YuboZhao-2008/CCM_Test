package staff;
import java.util.*;

import static java.util.Collections.sort;

public class StaffManager {
    public ArrayList<Staff> staffs;

    public void addStaff(Staff staff)   {
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
