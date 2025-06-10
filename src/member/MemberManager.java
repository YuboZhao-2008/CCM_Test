package member;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import facility.Facility;

/**
 * Manages a collection of Member objects: loading from file,
 * adding new members, searching by ID, printing bills,
 * and listing names alphabetically.
 * 
 * @author Yubo-Zhao
 * @version 1.0
 * @since 2025-06-06
 */
public class MemberManager {
    /**
     * The list of all members managed by this class.
     */
    public ArrayList<Member> members = new ArrayList<>();

    
    public MemberManager() {}
    /**
     * Constructs a MemberManager and immediately loads member data
     * from the specified file, wiring up parent/child relationships.
     *
     * @param filename the path to the member data file
     */
    public MemberManager(String filename) {
        members = new ArrayList<>();
        Map<Integer, Member> idToMember      = new HashMap<>();
        Map<Integer, Integer> youthGuardian  = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int numMembers = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < numMembers; i++) {
                int id           = Integer.parseInt(br.readLine().trim());
                String type      = br.readLine().trim().toLowerCase();
                int age          = Integer.parseInt(br.readLine().trim());
                String name      = br.readLine().trim();
                Member.PlanType pType   = Member.PlanType.valueOf(br.readLine().trim().toUpperCase());

                if (type.equals("adult")) {
                    String phone = br.readLine().trim();
                    String address = br.readLine().trim();
                    double totalbillAmount = Double.parseDouble(br.readLine().trim());
                    double totalbillPaid = Double.parseDouble(br.readLine().trim());
                    int numChildren = Integer.parseInt(br.readLine().trim());

                    List<Integer> childIds = new ArrayList<>();
                    for (int j = 0; j < numChildren; j++) {
                        childIds.add(Integer.parseInt(br.readLine().trim()));
                    }

                    AdultMember adult = new AdultMember(
                            age, name, pType,
                            phone, address,
                            totalbillAmount, totalbillPaid);
                    adult.setId(id);
                    members.add(adult);
                    idToMember.put(id, adult);
                    // you can still wire children if you stored their IDs elsewhere
                }
                else {  // youth
                    int guardianId = Integer.parseInt(br.readLine().trim());
                    YouthMember youth = new YouthMember(age, name, pType, null);
                    youth.setId(id);
                    members.add(youth);
                    idToMember.put(id, youth);
                    youthGuardian.put(id, guardianId);
                }
            }

            // now hook up youth to guardian and guardian to children
            for (var e : youthGuardian.entrySet()) {
                YouthMember  y = (YouthMember) idToMember.get(e.getKey());
                AdultMember  a = (AdultMember) idToMember.get(e.getValue());
                y.setGuardian(a);
                a.addChild(y);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /**
     * Generates the next unique member ID (greatest ID + 1).
     *
     * @return the new unique ID
     */
    public int generateId() {
        int maxId = -1;

        for (Member member : members) {
            maxId = Math.max(maxId, member.getId());
        }

        return ++maxId;
    }

    /**
     * Searches for a member by ID using binary search.
     * Assumes the members list is sorted by ID.
     *
     * @param id the ID to search for
     * @return the Member with matching ID, or null if not found
     */
    public Member searchById(int id) {
        return searchByIdRecursive(id, 0, members.size() - 1);
    }

    /**
     * Recursive helper for binary search.
     */
    private Member searchByIdRecursive(int id, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid   = (low + high) / 2;
        int midId = members.get(mid).getId();

        if (midId == id) {
            return members.get(mid);
        } else if (midId > id) {
            return searchByIdRecursive(id, low, mid - 1);
        } else {
            return searchByIdRecursive(id, mid + 1, high);
        }
    }

    /**
     * Prints all members' bills to standard output.
     */
    public void printAllBills() {
        for (Member m : members) {
            m.printBill();
        }
    }

    /**
     * Prints all member names in alphabetical order.
     */
    public void printAlphabetical() {
        ArrayList<String> sorted = new ArrayList<>();
        for (Member m : members) {
            sorted.add(m.getName());
        }
        Collections.sort(sorted);
        for (String name : sorted) {
            System.out.println(name);
        }
    }

    /**
     * Adds a new member, assigning a unique ID automatically.
     *
     * @param member the Member to add
     */
    public void addMember(Member member) {
        member.setId(generateId());
        members.add(member);
    }
}
