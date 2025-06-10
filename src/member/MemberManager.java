package member;

import java.util.*;
import java.io.*;
public class MemberManager {
    public ArrayList<Member> members = new ArrayList<>();

    public MemberManager(String filename) {
        members = new ArrayList<>();
        Map<Integer, Member>        idToMember        = new HashMap<>();
        Map<Integer, List<Integer>> adultChildrenIds  = new HashMap<>();
        Map<Integer, Integer>       youthGuardianIds  = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int numMembers = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < numMembers; i++) {
                int    id       = Integer.parseInt(br.readLine().trim());
                String type     = br.readLine().trim().toLowerCase();
                int    age      = Integer.parseInt(br.readLine().trim());
                String name     = br.readLine().trim();
                Member.PlanType planType = Member.PlanType.valueOf(br.readLine().trim().toUpperCase());
                if (type.equals("adult")) {
                    String phone       = br.readLine().trim();
                    String address     = br.readLine().trim();
                    double billAmount  = Double.parseDouble(br.readLine().trim());
                    boolean billPaid   = br.readLine().trim().equalsIgnoreCase("paid");
                    int numChildren    = Integer.parseInt(br.readLine().trim());
                    List<Integer> childIds = new ArrayList<>();
                    for (int j = 0; j < numChildren; j++) {
                        childIds.add(Integer.parseInt(br.readLine().trim()));
                    }
                    AdultMember adult = new AdultMember(age, name, planType, phone, address, billAmount, billPaid);
                    adult.setId(id);
                    members.add(adult);
                    idToMember.put(id, adult);
                    adultChildrenIds.put(id, childIds);
                } else {
                    int guardianId = Integer.parseInt(br.readLine().trim());
                    YouthMember youth = new YouthMember(age, name, planType, null);
                    youth.setId(id);
                    members.add(youth);
                    idToMember.put(id, youth);
                    youthGuardianIds.put(id, guardianId);
                }
            }
            for (var e : youthGuardianIds.entrySet()) {
                YouthMember youth = (YouthMember) idToMember.get(e.getKey());
                AdultMember guardian = (AdultMember) idToMember.get(e.getValue());
                youth.setGuardian(guardian);
                guardian.addChild(youth);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void addMember(Member member) {
        member.setId(generateId());
        members.add(member);
    }

    public int generateId() {
        if (members.isEmpty()) {
            return 1;
        }
        return members.getLast().getId() + 1;
    }

    public Member searchById(int id) {
        return searchByIdRecursive(id, 0, members.size() - 1);
    }

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

    public void printAllBills() {
        for (Member m : members) {
            m.printBill();
        }
    }

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
}
