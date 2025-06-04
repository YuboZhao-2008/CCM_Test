package member;

public class AdultMember extends Member {
    private String contactPhone;
    private String address;
    private double billAmount;
    private boolean billPaid;

    public AdultMember (int id, String name, PlanType, String contactPhone, String address, double billAmount, boolean billPaid)    {
        super(id, name, PlanType);
        this.contactPhone = contactPhone;
        this.address = address;
        this.billAmount = billAmount;
        this.billPaid = billPaid;
    }
}
